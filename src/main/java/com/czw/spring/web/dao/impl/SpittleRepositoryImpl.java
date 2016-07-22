package com.czw.spring.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.czw.spring.web.bean.Spittle;
import com.czw.spring.web.dao.SpittleRepository;

/**
 * @author Zevi Chan
 * @Date 2016年6月29日
 */
@Service
public class SpittleRepositoryImpl implements SpittleRepository {

	public List<Spittle> findSpittles(long max, int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for(int i=0; i< count; i++){
			spittles.add(new Spittle("Spittle"+i,new Date()));
		}
		return spittles;
	}

}
