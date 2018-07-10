package online.xiaohei.project.practice.concurrent;

/**
 * 三个售票窗口同时出售20张票。
 */
public class MultithreadPractices3 {
    public static void main(String[] args) {
        TicketOffice ticketOffice = new TicketOffice(new Object(), 20);
        new Thread(ticketOffice, "ticket-1").start();
        new Thread(ticketOffice, "ticket-2").start();
        new Thread(ticketOffice, "ticket-3").start();
    }
}

class TicketOffice implements Runnable {
    private Object object;
    private int ticketNum;

    public TicketOffice(Object object, int ticketNum) {
        this.object = object;
        this.ticketNum = ticketNum;
    }

    @Override
    public void run() {
        while (ticketNum > 0) {
            synchronized (object) {
                if (ticketNum <= 0) {
                    System.out.println(Thread.currentThread().getName() + " : 没票啦");
                } else {
                    System.out.println(Thread.currentThread().getName() + " : 卖出了第" + ticketNum-- + "张票");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}