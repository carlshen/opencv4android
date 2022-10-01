package com.book.chapter.seven;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import gloomyfish.opencvdemo.R;

public class DisplayModeActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{
    private JavaCameraView mOpenCvCameraView;
    private String TAG = "DisplayModeActivity";
    private CascadeClassifier faceDetector;
    private int option = 0;
    private EditText editText;
    private Button processButton;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
//                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_mode_activity);

        //for 6.0 and 6.0 above, apply permission
        if(Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mOpenCvCameraView = findViewById(R.id.full_screen_camera_id);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.enableFpsMeter();
        editText = (EditText) findViewById(R.id.mode_text);
        processButton = (Button) findViewById(R.id.feature_btn);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = editText.getText().toString();
                if (!TextUtils.isEmpty(result)) {
                    try {
                        option = Integer.parseInt(result);
                    } catch (Exception e) {
                    }
                }
            }
        });

        // 后置摄像头开启预览
        mOpenCvCameraView.setCameraIndex(1);
        mOpenCvCameraView.enableView();

        try {
            initFaceDetector();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    private void initFaceDetector() throws IOException {
        System.loadLibrary("face_detection");
        InputStream input = getResources().openRawResource(R.raw.lbpcascade_frontalface);
        File cascadeDir = this.getDir("cascade", Context.MODE_PRIVATE);
        File file = new File(cascadeDir.getAbsoluteFile(), "lbpcascade_frontalface.xml");
        FileOutputStream output = new FileOutputStream(file);
        byte[] buff = new byte[1024];
        int len = 0;
        while((len = input.read(buff)) != -1) {
            output.write(buff, 0, len);
        }
        input.close();
        output.close();
        initLoad(file.getAbsolutePath());
        //faceDetector = new CascadeClassifier(file.getAbsolutePath());
        file.delete();
        cascadeDir.delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.invert:
                option = 1;
                break;
            case R.id.edge:
                option = 2;
                break;
            case R.id.sobel:
                option = 3;
                break;
            case R.id.boxBlur:
                option = 4;
                break;
            case R.id.faceDetection:
                option = 5;
                break;
            default:
                option = 0;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat frame = inputFrame.rgba();
        Core.flip(frame, frame, 1);
        if(this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            Core.rotate(frame, frame, Core.ROTATE_90_CLOCKWISE);
        }
        process(frame);
        return frame;
    }

    private void process(Mat frame) {
        switch (option){
            case 1:
                Core.bitwise_not(frame, frame);
                break;
            case 2:
                Mat edges = new Mat();
                Imgproc.Canny(frame, edges, 100, 200, 3, false);
                Mat result = Mat.zeros(frame.size(), frame.type());
                frame.copyTo(result, edges);
                result.copyTo(frame);
                edges.release();
                result.release();
                break;
            case 3:
                Mat gradx = new Mat();
                Imgproc.Sobel(frame, gradx, CvType.CV_32F, 1, 0);
                Core.convertScaleAbs(gradx, gradx);
                gradx.copyTo(frame);
                gradx.release();
                break;
            case 4:
                Mat temp = new Mat();
                Imgproc.blur(frame, temp, new Size(15, 15));
                temp.copyTo(frame);
                temp.release();
                break;
            case 5:
                //haarFaceDetection(frame);
                faceDetection(frame.getNativeObjAddr());
                break;
            default:
                break;
        }
    }

    private void haarFaceDetection(Mat frame) {

        Mat gray = new Mat();
        MatOfRect faces = new MatOfRect();
        Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGRA2GRAY);
        faceDetector.detectMultiScale(gray, faces, 1.1, 1, 0, new Size(30, 30), new Size(300, 300));
        List<Rect> faceList = faces.toList();
        if(faceList.size() > 0) {
            for(Rect rect : faceList) {
                Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0,0,255), 2, 8, 0);
            }
        }
        gray.release();
        faces.release();
    }

    public native void faceDetection(long frameAddress);

    public native void initLoad(String haarFilePath);
}
