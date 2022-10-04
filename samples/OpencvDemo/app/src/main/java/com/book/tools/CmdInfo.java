package com.book.tools;

/* loaded from: classes2.dex */
public class CmdInfo {

    /* loaded from: classes2.dex */
    public class CmdDir {
        public static final int W_DAT = 2;
        public static final int W_GET = 1;
        public static final int W_PACK = 3;
        public static final int W_SET = 0;

        public CmdDir() {
        }
    }

    /* loaded from: classes2.dex */
    public static class CmdElem {
        public byte[] Data;
        public byte cmdid;
        public byte dir;
        public short len;

        public CmdElem(int i) {
            this.Data = new byte[i];
        }
    }

    /* loaded from: classes2.dex */
    public class CmdId {
        public static final int D_4DPOS_ID = 196;
        public static final int D_ADC_PROBE_ID = 194;
        public static final int D_AP_CONNECT_ID = 199;
        public static final int D_BATTERY_LEVEL = 193;
        public static final int D_BOARD_SN = 156;
        public static final int D_BOARD_TEMPERATURE = 192;
        public static final int D_BOARD_VERSION = 155;
        public static final int D_BUTTON_ID = 195;
        public static final int D_COLOR_AUTH = 160;
        public static final int D_DICOM_AUTH = 158;
        public static final int D_DUAL_ACTIVE = 169;
        public static final int D_DUAL_PROBES_ID = 200;
        public static final int D_ENABLE_SECOND_HOST = 153;
        public static final int D_GET_MCUIMG_CONTENT = 165;
        public static final int D_GET_MCUIMG_CONTENT_END = 166;
        public static final int D_GET_MCUIMG_SIZE = 167;
        public static final int D_HOST_CHECK = 143;
        public static final int D_HUMAN_VET_AUTH = 168;
        public static final int D_IP_ADDR = 154;
        public static final int D_LIANYIN_DATA = 162;
        public static final int D_PRJ_OPTION = 161;
        public static final int D_PROBE_FIRMWARE_VERSION = 141;
        public static final int D_PROBE_FPGA_VERSION = 146;
        public static final int D_PROBE_ID = 197;
        public static final int D_PROBE_PID_VERSION = 145;
        public static final int D_PROBE_SN = 149;
        public static final int D_PW_AUTH = 159;
        public static final int D_READ_FLASH_FILE = 164;
        public static final int D_SET_AP_SSID_NAME = 128;
        public static final int D_SET_AP_SSID_PWD = 129;
        public static final int D_SET_BULE_LED = 130;
        public static final int D_SET_CHARGE = 152;
        public static final int D_SET_IMX51_IMG_CONTENT = 135;
        public static final int D_SET_IMX51_IMG_CONTENT_END = 136;
        public static final int D_SET_IMX51_IMG_SIZE = 137;
        public static final int D_SET_MCUIMG_CONTENT = 132;
        public static final int D_SET_MCUIMG_CONTENT_END = 133;
        public static final int D_SET_MCUIMG_SIZE = 134;
        public static final int D_SET_PID_CONTENT = 138;
        public static final int D_SET_PID_RETURN_CONTENT = 139;
        public static final int D_SET_PID_RETURN_FINISH = 140;
        public static final int D_SET_WHITE_LED = 131;
        public static final int D_UPROBE_BOARDID = 157;
        public static final int D_UPROBE_FPGA_ADDR = 147;
        public static final int D_UPROBE_FPGA_DAT = 148;
        public static final int D_UPROBE_PROBEID = 151;
        public static final int D_UPROBE_PRODUCTID = 150;
        public static final int D_USB_INSERT_ID = 198;
        public static final int D_WIFI_CHANNEL = 144;
        public static final int D_WIFI_FIRMWARE_VERSION = 142;
        public static final int D_WRITE_FLASH_FILE = 163;
        public static final int W_ALL_PARAM_CMD = 83;
        public static final int W_BFGATHER_CMD = 102;
        public static final int W_B_CMD = 77;
        public static final int W_B_COLORMAP_CMD = 20;
        public static final int W_B_DYNAMIC_CMD = 10;
        public static final int W_B_ENHANCE_CMD = 13;
        public static final int W_B_EXPAND_CMD = 11;
        public static final int W_B_FOCUSPOS_CMD = 12;
        public static final int W_B_FRAMECORR_CMD = 16;
        public static final int W_B_FREQ_CMD = 7;
        public static final int W_B_GAIN_CMD = 6;
        public static final int W_B_LR_CMD = 18;
        public static final int W_B_POSTEFFECT_CMD = 17;
        public static final int W_B_POWER_CMD = 9;
        public static final int W_B_SPACECOMP_CMD = 14;
        public static final int W_B_STEER_CMD = 15;
        public static final int W_B_TSI_CMD = 8;
        public static final int W_B_UD_CMD = 19;
        public static final int W_CONNECT_HOST_CMD = 104;
        public static final int W_CONNECT_TYPE_CMD = 97;
        public static final int W_C_APOWER_CMD = 105;
        public static final int W_C_BASELINE_CMD = 30;
        public static final int W_C_CMD = 78;
        public static final int W_C_DISP_LINEANDDOT_CMD = 88;
        public static final int W_C_DISP_LINE_RANGE_CMD = 35;
        public static final int W_C_DISP_POINT_RANGE_CMD = 36;
        public static final int W_C_FRAMECORR_CMD = 31;
        public static final int W_C_FREQ_CMD = 28;
        public static final int W_C_GAIN_CMD = 25;
        public static final int W_C_PRIORY_CMD = 32;
        public static final int W_C_PSEUDOCOLOR_CMD = 33;
        public static final int W_C_SCALE_CMD = 26;
        public static final int W_C_SPEED_REVERSE_CMD = 27;
        public static final int W_C_STEER_CMD = 34;
        public static final int W_C_WALLFILTER_CMD = 29;
        public static final int W_DEPTH_CMD = 4;
        public static final int W_DICOM_SWITCH_CMD = 98;
        public static final int W_ENCMODE_CMD = 96;
        public static final int W_EXAMIMGPARA_CMD = 86;
        public static final int W_EXAMMODE_CMD = 76;
        public static final int W_EXAMMODE_ID_CMD = 101;
        public static final int W_FRAMECORR_CMD = 24;
        public static final int W_FREEZE_CMD = 2;
        public static final int W_IMGQUALITY_CMD = 95;
        public static final int W_MITI_CMD = 94;
        public static final int W_M_APOWER_CMD = 106;
        public static final int W_M_CMD = 79;
        public static final int W_M_COLORMAP_CMD = 48;
        public static final int W_M_DYNAMIC_CMD = 47;
        public static final int W_M_GAIN_CMD = 45;
        public static final int W_M_LINECORR_CMD = 50;
        public static final int W_M_LINENUM_CMD = 52;
        public static final int W_M_POSTEFFECT_CMD = 49;
        public static final int W_M_SPEED_CMD = 46;
        public static final int W_M_TIME_MARK_CMD = 51;
        public static final int W_NOFPGADSC_CMD = 84;
        public static final int W_POLLING_CMD = 3;
        public static final int W_POWER_CMD = 80;
        public static final int W_POWER_DYNAMIC_CMD = 44;
        public static final int W_POWER_FRAMECORR_CMD = 41;
        public static final int W_POWER_GAIN_CMD = 37;
        public static final int W_POWER_PRIORY_CMD = 42;
        public static final int W_POWER_PSEUDOCOLOR_CMD = 43;
        public static final int W_POWER_SCALE_CMD = 38;
        public static final int W_POWER_SPEED_REVERSE_CMD = 39;
        public static final int W_POWER_WALLFILTER_CMD = 40;
        public static final int W_PROBEDID_MAPFLAG_CMD = 99;
        public static final int W_PROBEID_BEGIN = 0;
        public static final int W_PROBEID_CMD = 1;
        public static final int W_PWBC_PW_CMD = 92;
        public static final int W_PWB_B_CMD = 89;
        public static final int W_PWC_C_CMD = 90;
        public static final int W_PWC_POWER_CMD = 91;
        public static final int W_PW_ACSPWR_CMD = 75;
        public static final int W_PW_ANGLE_CMD = 56;
        public static final int W_PW_AUTOCALC_CMD = 63;
        public static final int W_PW_BASELINE_CMD = 53;
        public static final int W_PW_CMD = 81;
        public static final int W_PW_COLORMAP_CMD = 60;
        public static final int W_PW_COLORMAP_SWITCH_CMD = 57;
        public static final int W_PW_DYNAMIC_CMD = 62;
        public static final int W_PW_FILTER_CMD = 69;
        public static final int W_PW_FREQ_CMD = 66;
        public static final int W_PW_GAIN_CMD = 68;
        public static final int W_PW_GATEPOS_CMD = 71;
        public static final int W_PW_GATESIZE_CMD = 70;
        public static final int W_PW_LINENUM_CMD = 72;
        public static final int W_PW_POSTEFFECT_CMD = 74;
        public static final int W_PW_QOPT_CMD = 73;
        public static final int W_PW_QUICKANGLE_CMD = 55;
        public static final int W_PW_REVERSE_CMD = 59;
        public static final int W_PW_SCALE_CMD = 67;
        public static final int W_PW_SPEED_CMD = 54;
        public static final int W_PW_STEER_CMD = 65;
        public static final int W_PW_TIMEMARK_CMD = 58;
        public static final int W_PW_TRACERANGE_CMD = 64;
        public static final int W_PW_VOLUME_CMD = 61;
        public static final int W_SAVE_PARAM_CMD = 85;
        public static final int W_SEARCH_HOST_CMD = 103;
        public static final int W_TGC_CMD = 5;
        public static final int W_THI_DYNAMIC_CMD = 22;
        public static final int W_THI_GAIN_CMD = 21;
        public static final int W_THI_POSTEFFECT_CMD = 23;
        public static final int W_UIMSG_CMD = 87;
        public static final int W_UPDATE_CMD = 82;
        public static final int W_UPDATE_IMGMODE_CMD = 93;
        public static final int W_VET_CMD = 100;

        public CmdId() {
        }
    }

    /* loaded from: classes2.dex */
    public class BtnId {
        public static final int W_BTN_10_ID = 10;
        public static final int W_BTN_1_ID = 1;
        public static final int W_BTN_2_ID = 2;
        public static final int W_BTN_3_ID = 3;
        public static final int W_BTN_4_ID = 4;
        public static final int W_BTN_5_ID = 5;
        public static final int W_BTN_6_ID = 6;
        public static final int W_BTN_7_ID = 7;
        public static final int W_BTN_8_ID = 8;
        public static final int W_BTN_9_ID = 9;

        public BtnId() {
        }
    }

    /* loaded from: classes2.dex */
    public class BtnAction {
        public static final int W_BTN_DOWN = 1;
        public static final int W_BTN_HOLD = 2;
        public static final int W_BTN_LONG_PRESS = 3;
        public static final int W_BTN_UP = 4;

        public BtnAction() {
        }
    }

    /* loaded from: classes2.dex */
    public class PollingID {
        public static final int IMAGE_SYNC_CMD = 134;
        public static final int PROBE_CONTINUE_TEST_CMD = 131;
        public static final int P_IMAGE_RUN_CMD = 128;
        public static final int USB_CONNECTED_CMD = 129;
        public static final int USB_DISCONNECTED_CMD = 130;
        public static final int WLAN_CONNECTED_CMD = 132;
        public static final int WLAN_DISCONNECT_CMD = 133;

        public PollingID() {
        }
    }

    /* loaded from: classes2.dex */
    public class AppMsgId {
        public static final int WUSB_SET_PROBE_ID = 240;

        public AppMsgId() {
        }
    }
}
