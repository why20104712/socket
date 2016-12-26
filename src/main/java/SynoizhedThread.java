/**
 * 线程同步问题
 * Created by why on 2016/12/26.
 */
public class SynoizhedThread {


    public static void main(String[] args) {

        TestThread testThread = new TestThread();

        Thread thread1 = new Thread(testThread, "t1");
        Thread thread2 = new Thread(testThread, "t2");
        thread1.start();
        thread2.start();
    }

}


class TestThread implements Runnable{

    int num = 10;
    public void run() {
        while (num > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {//使用synchronized解决线程同步问题
                System.out.println("当前线程名称：" + Thread.currentThread().getName() + " num:" + num);
                num --;
            }

        }
    }
}
