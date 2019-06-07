package com.lambdaschool.javawebsc.model;

public class Country {
    private String name;
    private long gdp;

    public Country(String name, long gdp) {
        this.name = name;
        this.gdp = gdp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGdp() {
        return gdp;
    }

    public void setGdp(long gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", gdp=" + gdp +
                '}';
    }
}
