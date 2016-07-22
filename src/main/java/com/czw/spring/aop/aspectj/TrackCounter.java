package com.czw.spring.aop.aspectj;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月22日 上午10:17:50 
 * 
 */
@Aspect
public class TrackCounter {
	private static Logger log = LoggerFactory.getLogger(TrackCounter.class);
	private Map<Integer,Integer> trackCounts = new HashMap<Integer, Integer>();
	
	@Pointcut("execution(* com.czw.aop.act.CompactDisc.playTrack(int)) && args(trackNumber)")
	public void trackPlayed(int trackNumber){}
	
	@Before("trackPlayed(trackNumber)")
	public void countTrack(int trackNumber){
		log.info("before active");
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount);
	}
	
	public int getPlayCount(int trackNumber){
		return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber)+1 : 0;
	}
	
}
