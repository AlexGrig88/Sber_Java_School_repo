package com.alexgrig.cachetools;

import java.io.Serializable;

public class SerializationDataUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    private String key;
    private Object result;

    public SerializationDataUnit(String key, Object result) {
        this.key = key;
        this.result = result;
    }

    public SerializationDataUnit() {
    }

    public String getKey() {
        return key;
    }

    public Object getResult() {
        return result;
    }

}
