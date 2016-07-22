package com.czw.spring.web.bean;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Zevi Chan
 * @Date 2016年6月29日
 */
public class Spittle {
	private final Long id;
	private final String message;
	private final Date time;
	private Double latitude;
	private Double longitude;
	
	
	public Spittle(String message, Date time) {
		this(message,time,null,null);
	}
	public Spittle(String message, Date time, Double latitude, Double longitude) {
		this.id = null;
		this.message = message;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Long getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public Date getTime() {
		return time;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj,
                new String[]{"message","latitude", "longitude"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,
                new String[]{"message", "latitude", "longitude"});
    }
	
}
