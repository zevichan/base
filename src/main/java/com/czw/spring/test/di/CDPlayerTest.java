package com.czw.spring.test.di;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.czw.spring.test.bean.CDPlayer;
import com.czw.spring.test.bean.MagicBean;
import com.czw.spring.test.config.CDPlayerConfig;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月17日 下午3:19:51 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CDPlayer.class,CDPlayerConfig.class})
@ActiveProfiles({"magic"})
public class CDPlayerTest {
	/*@Autowired
	private CompactDisc cd;*/
	@Autowired
	private CDPlayer cdPlayer;
	
	@Autowired(required=false)
	private MagicBean magicBean;
	
	@Test
	@Ignore
	public void cdShouldNotBeNull(){
		//cd.play();
		cdPlayer.compactDisc.play();
	}
	
	@Test
	public void magicShow(){
		if(magicBean == null)
			System.out.println("magicBean isn't created");
		else
			System.out.println(magicBean.show());
	}
}
