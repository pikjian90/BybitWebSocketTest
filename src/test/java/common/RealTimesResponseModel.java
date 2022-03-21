package common;

import java.util.List;

public class RealTimesResponseModel {
    private String symbol;
    private String symbolName;
    private String topic;
    private List<Data> data;

    public class Data{
        private String t;
        private String s;
        private String sn;
        private String c;
        private String h;
        private String l;
        private String o;
        private String v;
        private String qv;
        private String m;
        private String e;
        private String f;

        public Data(String t, String s, String sn, String c, String h, String l, String o, String v, String qv, String m, String e, String f) {
            this.t = t;
            this.s = s;
            this.sn = sn;
            this.c = c;
            this.h = h;
            this.l = l;
            this.o = o;
            this.v = v;
            this.qv = qv;
            this.m = m;
            this.e = e;
            this.f = f;
        }

        public String getT() {
            return t;
        }

        public String getS() {
            return s;
        }

        public String getSn() {
            return sn;
        }

        public String getC() {
            return c;
        }

        public String getH() {
            return h;
        }

        public String getL() {
            return l;
        }

        public String getO() {
            return o;
        }

        public String getV() {
            return v;
        }

        public String getQv() {
            return qv;
        }

        public String getM() {
            return m;
        }

        public String getE() {
            return e;
        }

        public String getF() {
            return f;
        }
    }

    public RealTimesResponseModel(String symbol, String symbolName, String topic, List<Data> data) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.topic = topic;
        this.data = data;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public String getTopic() {
        return topic;
    }

    public List<Data> getData() {
        return data;
    }
}
