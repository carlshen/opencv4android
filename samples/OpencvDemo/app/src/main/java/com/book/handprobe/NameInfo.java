package com.book.handprobe;

/* loaded from: classes2.dex */
public class NameInfo {
    public static String createTail() {
        try {
            if (getDispModeName() == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer("_");
            stringBuffer.append(getProbeName());
            stringBuffer.append("_");
            stringBuffer.append(getDispModeName());
            stringBuffer.append("_");
            stringBuffer.append(getExamName());
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getProbeName() {
        return ""; //Ultrasys.Instance().mPidInerface.mCurProbePID.mProbeName;
    }

    public static String getDispModeName() {
        int GetDispMode = Ultrasys.Instance().GetDispMode();
        if (GetDispMode != 0) {
            if (GetDispMode == 5) {
                return "PW";
            }
            if (GetDispMode == 55) {
                return "P";
            }
            switch (GetDispMode) {
                case 8:
                    return FileDefinition.PIC2_PREFFIX;
                case 9:
                    return "M";
                default:
                    switch (GetDispMode) {
                        case 57:
                            return "BPW";
                        case 58:
                            return "CPW";
                        case 59:
                            return "PPW";
                        default:
                            return null;
                    }
            }
        }
        return FileDefinition.PIC1_PREFFIX;
    }

    public static String getExamName() {
        return ""; //LanguageUtil._NLS(ExamTypesId.EXAM_MAP[Ultrasys.Instance().FindExamIndex(Ultrasys.Instance().mCurExamMode)][0]);
    }
}
