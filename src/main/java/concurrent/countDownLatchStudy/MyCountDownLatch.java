package concurrent.countDownLatchStudy;

public class MyCountDownLatch {
	
	private Integer i;
	
	private Object o = new Object();
	
	public MyCountDownLatch(int i) {
		this.i = i;
	}

	public void countDown() {
		synchronized (this) {
			i--;
			if (i <= 0) {
				notify();
			}
		}
	}
	
	public void await() {
		synchronized (this) {
			while (true) {
				if (i > 0) {
					try {
						wait();
						break;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

