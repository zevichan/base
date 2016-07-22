package com.czw.base.enu;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月12日 下午2:07:45 
 * 
 */
public class EqualsAndHashCode {
	static long start,end;
	static int key = 1215649434;
	static long rtn;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		rtn = hashCalculate1(key);
		printRtn("rtn1",rtn, System.currentTimeMillis()-start);
		
		
		start = System.currentTimeMillis();
		rtn = hashCalculate2(key);
		printRtn("rtn2",rtn, System.currentTimeMillis()-start);
		
		
	}
	
	public static long hashCalculate1(int key){
		return 31*key;
	}
	public static int hashCalculate2(int key){
		return (key<<5) -key;
	}
	public static void printRtn(String rtn1 ,long rtn,long time){
		System.out.println(rtn1+" , result:"+rtn+" , time :"+time);
	}
}
