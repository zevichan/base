package com.czw.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZeviChen
 * @Date 2016-08-25 13:01:36
 */
public class Dataset {
	private String album_id;
	private String album_title;
	private Map<String, Object> otherProperties = new HashMap<String, Object>();

	public String getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}

	public String getAlbum_title() {
		return album_title;
	}

	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}

	public Object get(String name) {
		return otherProperties.get(name);
	}
}
