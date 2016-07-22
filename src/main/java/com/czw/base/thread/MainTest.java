package com.czw.base.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 1.除了long和double的基本类型读写都是原子性的，long和double因为在64系统上是以高32位和低32位存储
 * 		所以存在两个线程分别获得高位和低位读写的情况，出现错误的结果.未来的64位处理器,64位系统可避免这个问题
 * 
 * 2.使用AtomicLong来替代long的安全性
 * 
 * 
 * @author Zevi Chan
 * @Date 2016-07-06 09:26:18
 * 
 */
public class MainTest {
	private static Logger log = LoggerFactory.getLogger(MainTest.class);

	
	
	//保证每个线程都会获取最新的写入数据,但是不要滥用这个关键字
	private static volatile boolean stop = false;			
	/**
	 * 通过同步boolean域来停止一个线程的运行
	 * @throws InterruptedException 
	 */
	@Test
	public void stopThread() throws InterruptedException{
		Thread t = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while(!stop){
					i++;
					log.info("loop thread {}",i);
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				log.info("stopThread[Thread:{}]: count i is {}",Thread.currentThread().getName(),i);
			}
		});
		t.start();
		
		Thread.sleep(7000);
		stop = true;
		log.info("stopThread[Thread:{}]: stopped!",t.getName());
	
	}
}
