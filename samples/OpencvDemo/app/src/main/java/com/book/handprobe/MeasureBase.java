package com.book.handprobe;

/* loaded from: classes2.dex */
public class MeasureBase {
//    public static IResultUpdate HRResultUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.1
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            rRsetByName.setVal(120.0d / (d * measureItem.getTimeDistScal()));
//            return true;
//        }
//    };
//    public static IResultUpdate TimeResultUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.2
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            rRsetByName.setVal(d * measureItem.getTimeDistScal());
//            rRsetByName.setUnit(4);
//            return true;
//        }
//    };
//    public static IResultUpdate DistRSetGener = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.3
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            rRsetByName.setVal(d);
//            rRsetByName.setUnit(1);
//            return true;
//        }
//    };
//    public static IResultUpdate EllRSetUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.4
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            char c = 65535;
//            int hashCode = str.hashCode();
//            if (hashCode != 2049197) {
//                if (hashCode == 2100535 && str.equals("Circ")) {
//                    c = 0;
//                }
//            } else if (str.equals("Area")) {
//                c = 1;
//            }
//            switch (c) {
//                case 0:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(1);
//                    return true;
//                case 1:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(2);
//                    return true;
//                default:
//                    return false;
//            }
//        }
//    };
//    public static IResultUpdate AngelRSetUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.5
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            rRsetByName.setVal(d);
//            rRsetByName.setUnit(9);
//            return true;
//        }
//    };
//    public static IResultUpdate PWSpeedRSetUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.6
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            rRsetByName.setUnit(3);
//            rRsetByName.setVal((-d) * measureItem.getPWSpeedScal());
//            return true;
//        }
//    };
//    public static IResultUpdate TraceRSetUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.7
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            char c = 65535;
//            int hashCode = str.hashCode();
//            if (hashCode != 2049197) {
//                if (hashCode == 2100535 && str.equals("Circ")) {
//                    c = 0;
//                }
//            } else if (str.equals("Area")) {
//                c = 1;
//            }
//            switch (c) {
//                case 0:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(1);
//                    return true;
//                case 1:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(2);
//                    return true;
//                default:
//                    return false;
//            }
//        }
//    };
//    public static IResultUpdate ThreeDistVolSetUpdate = new IResultUpdate() { // from class: handprobe.application.measurement.measurebase.MeasureBase.8
//        @Override // handprobe.application.measurement.measurebase.MeasureBase.IResultUpdate
//        public boolean update(MeasureItem measureItem, String str, double d) {
//            if (str == null) {
//                return false;
//            }
//            MeasResultSet rRsetByName = measureItem.getRRsetByName(str);
//            if (rRsetByName == null) {
//                rRsetByName = new MeasResultSet(str);
//                measureItem.addRSet(rRsetByName);
//            }
//            char c = 65535;
//            int hashCode = str.hashCode();
//            if (hashCode != 86195) {
//                switch (hashCode) {
//                    case 66041643:
//                        if (str.equals("Dist1")) {
//                            c = 0;
//                            break;
//                        }
//                        break;
//                    case 66041644:
//                        if (str.equals("Dist2")) {
//                            c = 1;
//                            break;
//                        }
//                        break;
//                    case 66041645:
//                        if (str.equals("Dist3")) {
//                            c = 2;
//                            break;
//                        }
//                        break;
//                }
//            } else if (str.equals("Vol")) {
//                c = 3;
//            }
//            switch (c) {
//                case 0:
//                case 1:
//                case 2:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(1);
//                    return true;
//                case 3:
//                    rRsetByName.setVal(d);
//                    rRsetByName.setUnit(10);
//                    return true;
//                default:
//                    return false;
//            }
//        }
//    };
//
//    /* loaded from: classes2.dex */
//    public interface IResultUpdate {
//        boolean update(MeasureItem measureItem, String str, double d);
//    }

    /* loaded from: classes2.dex */
    public enum TYPE {
        DISTANCE,
        ELLIPSE,
        ELLIPSE_AREA,
        ELLIPSE_DIST,
        TRACE,
        TRACE_AREA,
        RATIO_D,
        THREE_DIST_V,
        ANGLE,
        VLINE,
        HLINE,
        MTIME,
        POINT,
        HumanOB_Distance_GA,
        HumanOB_Ellipse_GA
    }

    /* loaded from: classes2.dex */
    public static class MeasPosition {
        public float mx;
        public float my;

        public MeasPosition(float f, float f2) {
            this.mx = f;
            this.my = f2;
        }

        public MeasPosition() {
            this.mx = 0.0f;
            this.my = 0.0f;
        }
    }
}
