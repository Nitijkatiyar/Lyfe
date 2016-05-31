package com.bst.Lyfe.models;

import java.io.Serializable;

/**
 * Created by nitij on 27-05-2016.
 */

public class CountryStateCityModel implements Serializable {

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
