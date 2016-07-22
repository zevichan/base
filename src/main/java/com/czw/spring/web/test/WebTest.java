package com.czw.spring.web.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import com.czw.spring.web.bean.Spittle;
import com.czw.spring.web.controller.IndexController;
import com.czw.spring.web.controller.SpittleController;
import com.czw.spring.web.dao.SpittleRepository;

/**
 * 
 * @author Zevi Chan
 * @Date 2016年6月29日
 */
public class WebTest {
	
	@Test
	@Ignore
	public void testIndex()throws Exception{
		IndexController ia = new IndexController();
		Assert.assertEquals("index:", ia.index());
	}
	
	/**
	 * 对控制器的测试
	 * Spring3.2开始可以不运行web服务器来测试控制器
	 * 此处发起一个get("/")请求调用index()方法
	 * @throws Exception
	 */
	@Test
	public void testIndexPage() throws Exception{
		IndexController ia = new IndexController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ia).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("index"));
		
	}
	
	@Test
	public void shouldShowRecentSpittles() throws Exception{
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/jsp/spittles.jsp")).build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=10&count=10"))
			.andExpect(MockMvcResultMatchers.view().name("spittles"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
			.andExpect(MockMvcResultMatchers.model().attribute("spittleList",
					Matchers.hasItems(expectedSpittles.toArray())));
		
		
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for(int i=0; i< count; i++){
			spittles.add(new Spittle("Spittle"+i,new Date()));
		}
		return spittles;
	}
	
}
