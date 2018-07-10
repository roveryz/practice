package online.xiaohei.project.practice.concurrent;

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5
 * 然后是线程2打印6,7,8,9,10 然后是线程3打印11,12,13,14,15.
 * 接着再由线程1打印16,17,18,19,20....
 * 以此类推, 直到打印到75。
 */

public class MultithreadPractices2 {
    public static void main(String[] args) {
        final Handler handler = new Handler();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        handler.print(Thread.currentThread().getName());
                    }
                }
            }, i + "").start();
        }
    }


}

class Handler {
    private int no = 1;
    private int status = 0;

    public synchronized void print(String threadName) {
        int threadIndex = Integer.parseInt(threadName);
        while (threadIndex != status) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Thread-" + threadName + " : ");
        for (int count = 0; count < 5; count++, no++) {
            if (count > 0) {
                System.out.print(",");
            }
            System.out.print(no);
        }
        System.out.println();
        status = (status + 1) % 3;
        this.notifyAll();

    }
}