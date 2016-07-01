package com.czw.web.dao;

import java.util.List;

import com.czw.web.bean.Spittle;

/**
 * @author Zevi Chan
 * @Date 2016年6月29日
 */
public interface SpittleRepository {
	
	List<Spittle> findSpittles(long max,int count);
}
