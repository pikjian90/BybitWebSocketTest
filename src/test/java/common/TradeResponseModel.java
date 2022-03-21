package common;

import java.util.List;

public class TradeResponseModel {
    private String symbol;
    private String symbolName;
    private String topic;
    private Params params;
    private List<Data> data;

    public TradeResponseModel(String symbol, String symbolName, String topic, Params params, List<Data> data) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.topic = topic;
        this.params = params;
        this.data = data;
    }

    public class Params{
        private String realtimeInterval;
        private String binary;

        public Params(String realtimeInterval, String binary) {
            this.realtimeInterval = realtimeInterval;
            this.binary = binary;
        }

        public String getRealtimeInterval() {
            return realtimeInterval;
        }

        public String getBinary() {
            return binary;
        }
    }

    public class Data{
        private String v;
        private String t;
        private String p;
        private String q;
        private String m;

        public Data(String v, String t, String p, String q, String m) {
            this.v = v;
            this.t = t;
            this.p = p;
            this.q = q;
            this.m = m;
        }

        public String getV() {
            return v;
        }

        public String getT() {
            return t;
        }

        public String getP() {
            return p;
        }

        public String getQ() {
            return q;
        }

        public String getM() {
            return m;
        }
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

    public Params getParams() {
        return params;
    }

    public List<Data> getData() {
        return data;
    }
}
