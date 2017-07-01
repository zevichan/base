package com.czw.base.colls.collections;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by zevi on 2017/6/26.
 */
public class Car implements Serializable {

    private static final long serialVersionUID = -6268195127611307950L;

    private String name;
    private String code;
    private String area;

    public Car() {
    }

    public Car(String name) {
        this(name, "111");
    }

    public Car(String name, String code) {
        this(name, code, "hangzhou");
    }

    public Car(String name, String code, String area) {
        this.name = name;
        this.code = code;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return new EqualsBuilder()
                .append(name, car.name)
                .append(code, car.code)
                .append(area, car.area)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(code)
                .append(area)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
