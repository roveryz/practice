package online.xiaohei.project.practice.concurrent;

import java.util.concurrent.*;

/*
 * @author zy
 * @date 2018/5/24 9:49
 * @desc 查看executor源码
 */
public class MyThreadPool{
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new MyClass());
        exec.shutdown();
    }
}

class MyClass implements Runnable {
    static int i;

    public void run() {
        System.out.println(i++);
    }
}
