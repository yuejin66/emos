package top.liyuejin.emoswxapi.threadcoreknowledge.createthreads;

/**
 * 用 Thread 实现线程
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    public void run() {
        System.out.println("用Thread类实现线程");
    }
}
