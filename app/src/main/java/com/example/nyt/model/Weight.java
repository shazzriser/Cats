package com.example.nyt.model;

public class Weight {
    private String imperial;
    private String metric;


    public Weight() {
        imperial = null;
        metric = null;
    }

    public Weight(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    public String getImperial() {
        return imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
