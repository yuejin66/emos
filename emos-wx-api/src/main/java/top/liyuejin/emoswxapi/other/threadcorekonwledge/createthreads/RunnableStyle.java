package top.liyuejin.emoswxapi.other.threadcorekonwledge.createthreads;

/**
 * 用 Runnable 来实现线程
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
