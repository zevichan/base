package com.czw.base.colls.tcollection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZeviChen , 2017/6/26 13:11
 */
public class Car implements Serializable {

    private static final long serialVersionUID = -8235678838691054137L;

    private String name;
    private String company;
    private String serialNum;
    private Date createDate;

    public Car() {
    }

    public Car(String name) {
        this(name, "co");
    }

    public Car(String name, String company) {
        this(name, company, "1");
    }

    public Car(String name, String company, String serialNum) {
        this(name, company, serialNum, new Date());
    }

    public Car(String name, String company, String serialNum, Date createDate) {
        this.name = name;
        this.company = company;
        this.serialNum = serialNum;
        this.createDate = createDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
//        return true;

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return new EqualsBuilder()
                .append(name, car.name)
                .append(company, car.company)
                .append(serialNum, car.serialNum)
                .isEquals();
    }

    @Override
    public int hashCode() {
//        return 1;
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(company)
                .append(serialNum)
                .toHashCode();
    }
}
