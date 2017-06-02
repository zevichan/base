package com.czw.jichu.threadeducation;
/*??????????????*/
public class TraditionalThread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//??????????Thread??run????
		Thread thread = new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("thread:" + Thread.currentThread());
				}
			}
		};
		
		//?????????Runnable????run????
		Thread thread1 = new Thread(
				new Runnable(){
					public void run(){
						while(true){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Runnable:" + Thread.currentThread());
						}
					}
				}){};
		
		//????????????????????????????????run??????????????run???????
		new Thread(
				new Runnable(){
					public void run(){
						while(true){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Runnable:" + Thread.currentThread());
						}
					}
				}
				){
			public void run(){
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Thread:" + Thread.currentThread());
				}
			}
		}.start();
	}

}
