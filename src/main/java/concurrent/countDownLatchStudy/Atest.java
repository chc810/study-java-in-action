package concurrent.countDownLatchStudy;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class Atest {
	
	//通常用法
	@Test
	public void commonUse() throws InterruptedException {
		final CountDownLatch countDown = new CountDownLatch(10);
		
		for (int i=0;i<10;i++) {
			new Thread(){
				public void run() {
					System.out.println("第" + Thread.currentThread().getName() + "号任务开始执行");
					try {
						//随机睡眠
						Thread.sleep((long) (Math.random() * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第" + Thread.currentThread().getName() + "号完成任务！！！");
					countDown.countDown();
				}
			}.start();
		}
		
		System.out.println(Thread.currentThread().getName() + "等待其他任务完成");
		countDown.await();
		System.out.println(Thread.currentThread().getName() + "结束等待");
	}
	
	//使用wait/notify模拟
	@Test
	public void notifyUse() {
		final MyCountDownLatch countDown = new MyCountDownLatch(100);
		
		for (int i=0;i<100;i++) {
			new Thread(){
				public void run() {
					System.out.println("第" + Thread.currentThread().getName() + "号任务开始执行");
					try {
						//随机睡眠
						Thread.sleep((long) (Math.random() * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第" + Thread.currentThread().getName() + "号完成任务！！！");
					countDown.countDown();
				}
			}.start();
		}
		
		System.out.println(Thread.currentThread().getName() + "等待其他任务完成");
		countDown.await();
		System.out.println(Thread.currentThread().getName() + "结束等待");
	}

}
