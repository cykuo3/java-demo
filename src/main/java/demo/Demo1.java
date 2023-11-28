package demo;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("我要挂了。。。");
        }));
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            System.out.println("我还在运行");
        }
    }
}
