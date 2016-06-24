package com.czw.aop.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import com.czw.aop.act.CompactDisc;
import com.czw.aop.act.Performance;
import com.czw.aop.act.impl.BlankDisc;
import com.czw.aop.act.impl.DancePerformance;
import com.czw.aop.aspectj.AudienceImprove;
import com.czw.aop.aspectj.EncoreableIntroducer;
import com.czw.aop.aspectj.TrackCounter;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月21日 下午5:14:28 
 * 
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@ImportResource("classpath:applicationContext.xml")
public class SystemConfig {
	@Bean
	public AudienceImprove audienceImprove(){
		return new AudienceImprove();
	}
	@Bean
	public Performance dancePerformance(){
		return new DancePerformance();
	}
	
	@Bean
	public TrackCounter trackCounter(){
		return new TrackCounter();
	}
	
	@Bean
	public CompactDisc blankDisc(){
		BlankDisc bd = new BlankDisc();
		bd.setTitle("BlankDisc Title");
		bd.setArtist("BlankDisc Artist");
		List<String> tracks = new ArrayList<String>();
		tracks.add("disc step1");
		tracks.add("disc step2");
		tracks.add("disc step3");
		tracks.add("disc step4");
		tracks.add("disc step5");
		tracks.add("disc step6");
		bd.setTrack(tracks);
		return bd;
	}
	/*@Bean
	public EncoreableIntroducer encoreableIntroducer(){
		return new EncoreableIntroducer();
	}*/
	
	
	
}
