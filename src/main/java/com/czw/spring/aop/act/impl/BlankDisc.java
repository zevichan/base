package com.czw.spring.aop.act.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.czw.spring.aop.act.CompactDisc;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月22日 上午10:25:22 
 * 
 */
@Component
public class BlankDisc implements CompactDisc {
	private String title,artist;
	private List<String> track = new ArrayList<String>();
	
	public void play() {

	}

	public String playTrack(int num) {
		return track.get(num);
	}

	public List<String> getTrack() {
		return track;
	}

	public void setTrack(List<String> track) {
		this.track = track;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
