package demo;

/**
 * @author chengyuankuo
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start...");
        Object obj = new Object();
        Object obj2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try{

                        System.out.println("thread1 start");
                        try {
                            obj.wait(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("thread1 awake");
                    }catch (Throwable ex){
                        ex.printStackTrace();

                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    System.out.println("thread2 sleep");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("thread2 awakebiz");

                }
            }
        }).start();

        Thread.sleep(100000000L);
    }
}
