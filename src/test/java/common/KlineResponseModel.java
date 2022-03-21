package common;

import java.util.List;

public class KlineResponseModel {
    private String symbol;
    private String symbolName;
    private String topic;
    private Params params;
    private List<Data> data;

    public class Params {
        private String realtimeInterval;
        private String klineType;
        private String binary;

        public Params(String realtimeInterval, String klineType, String binary) {
            this.realtimeInterval = realtimeInterval;
            this.klineType = klineType;
            this.binary = binary;
        }

        public String getRealtimeInterval() {
            return realtimeInterval;
        }

        public String getKlineType() {
            return klineType;
        }

        public String getBinary() {
            return binary;
        }
    }

    public class Data {
        private String t;
        private String s;
        private String sn;
        private String c;
        private String h;
        private String l;
        private String o;
        private String v;
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
        public String getF() {
            return f;
        }
    }

    public KlineResponseModel(String symbol, String symbolName, String topic, Params params,List<Data> data) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.topic = topic;
        this.params = params;
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
    public Params getParams() { return params; }
    public List<Data> getData() { return data; }
}
