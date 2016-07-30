package com.czw.jichu.prodemo1;

/*synchronized�������������ʾ��
 * �ó����п��ܲ������������ʱ�����������������Ĳ���
 * 
 * */
class Test implements Runnable{
	private boolean flag;
	Test(boolean flag){
		this.flag=flag;
	}
	public void run(){
		if(flag){
			synchronized(MyLock.locka){
				System.out.println("if locka");
				synchronized(MyLock.lockb){
					System.out.println("if lockb");
				}
			}
		}
		else{
			synchronized(MyLock.lockb){
				System.out.println("else lockb");
				synchronized(MyLock.locka){
					System.out.println("else locka");
				}
			}
			
		}
	}
}
class MyLock{
	static Object locka=new Object();
	static Object lockb=new Object();
}
public class DeadLockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stubT
		Thread t1=new Thread(new Test(true));
		Thread t2=new Thread(new Test(false));
		t1.start();
		t2.start();

	}

}
