package online.xiaohei.project.practice.concurrent;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * test1:创建两个线程，其中一个输出1-52，另外一个输出A-Z。输出格式要求：12A 34B 56C 78D
 */
public class MultithreadPractices1 {
    public static void main(String[] args) {
        Object object = new Object();
        new Thread(new Number(object)).start();
        new Thread(new Character(object)).start();
    }

}

class Number implements Runnable {

    //    用于被加锁
    private Object object;

    public Number(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            for (int i = 1; i < 53; i++) {
                if (i > 1 && i % 2 == 1) {
                    System.out.print(" ");// 每个单数先打印出来一个空格
                }
                System.out.print(i);
                if (i % 2 == 0) {
                    // 每个双数，先释放锁，唤醒其他线程，再让自己线程阻塞
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}

class Character implements Runnable {

    private Object object;

    public Character(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            for (char i = 'A'; i <= 'Z'; i++) {
                System.out.print(i);
                // 先释放锁，唤醒其他线程，在阻塞自己
                object.notifyAll();
                if (i < 'Z') {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}