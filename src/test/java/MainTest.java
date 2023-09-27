import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void test() {

        Byte b = (byte) 1;
        System.out.println(b == 1);

    }

    @Test
    public void test2() {
        HashSet set = new HashSet();
        set.add(null);
    }

    @Test
    public void test3() {
        String str = "1234567891";
        System.out.println(str.substring(0, 11));
    }

    @Test
    public void test4() {
        String str = "%s市助老订单不收取平台服务费，本次行程的基础服务费和信息服务费平台已全部为你免除。";
        System.out.println(String.format(str, "烟台"));
    }

    @Test
    public void test5() {
        String str = "driver_id=2000204734&latitude=40.02481&longitude=116.47455&nick_name=尾号0002&order_id=1327540718649999361&partner_order_id=TXBLbmQVRfamhApWEP&sex=0&taxi_car_no=京ADC990&timestamp=1684920495533&sign_key=a4Lm/DqQ8AQ";

        System.out.println(MD5.create().digestHex(str));
    }

    @Test
    public void test6() {
        ReentrantLock lock = new ReentrantLock();

        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    c2.signal();

                    System.out.println("t1 run");
                    c1.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    c3.signal();

                    System.out.println("t2 run");
                    c2.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    c1.signal();
                    System.out.println("t3 run");
                    c3.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();;
                }
            }
        }).start();
    }

    @Test
    public void test7(){
        double a = 9;
        double b = 5;

        double l = 0;
        double r = a;
        for (int i = 0; i < 100; i++) {
            double mid = (r - l) / 2 + l;
            if (Math.pow(mid,b) > a){
                r = mid;
            }else{
                l = mid;
            }
        }
        System.out.println(l);
    }

    //最长不重复子串
    @Test
    public void test8(){
        String str = "abcabcdabc";
        char[] charArray = str.toCharArray();

        int[] lastIndex = new int[charArray.length];

        Map<Character,Integer> lastIndexMap = new HashMap<>();

        for (int index = 0; index < lastIndex.length; index++) {
            lastIndex[index] = lastIndexMap.getOrDefault(charArray[index], -1);
            lastIndexMap.put(charArray[index],index);
        }

        int[] dp = new int[charArray.length];
        dp[0] = 1;

        for(int index = 1;index < charArray.length;index++){
            if (lastIndex[index] == -1) {
                //没出现过
                dp[index] = dp[index-1] + 1;
            }else{
                //出现过
                dp[index] = index - lastIndex[index];
            }
        }

        int total = 0;
        for (int i : dp) {
            total = Math.max(total, i);
        }
        System.out.println(total);
    }

    @Test
    public void test9(){
        int i = 1;
        switch (i){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
        }

    }


    @Test
    public void test10() throws InterruptedException {
        ThreadFactory threadFactory = r -> new Thread(r, "myWorker");
        ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);
        executorService.submit(() -> {
            int i = 1 + 1;
            System.out.println("----" + i);
        });
        Thread.sleep(100000000);
    }

    @Test
    public void test11(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10,0.75f,true);
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        System.out.println(map);
        String val = map.get("5");
        System.out.println(val);
        System.out.println(map);

    }

    @Test
    public void test12(){

        ArrayBlockingQueue<Runnable> array = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor es = new ThreadPoolExecutor(1,5,10,TimeUnit.SECONDS,array,new ThreadPoolExecutor.CallerRunsPolicy());

        try{
            for (AtomicInteger ai = new AtomicInteger(0); ai.get() < 20; ai.addAndGet(1)) {
//                System.out.println("提交第" + ai.get() + "个任务");
                es.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        if (!Thread.currentThread().getName().equals("main")) {
                            Thread.sleep(1000000L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println(es);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Test
    public void test13(){

        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        list.offer("4");
        list.offer("5");

        while (!list.isEmpty()){
            System.out.println(list.pop());
        }
    }

    @Test
    public void test14(){
        Map<String, String> map = new HashMap<>();
        map.put("a",null);
        System.out.println(map.size());
        System.out.println(map);

    }

    @Test
    public void test15(){
        Stack<String> stack = new Stack<>();
        for (int i = 1; i < 6; i++) {
            stack.push("node-" + i);
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    @Test
    public void test16(){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a).reversed());

        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    @Test
    public void test17(){

        LinkedList<String> ll = new LinkedList<>();

        Object lock = new Object();

        new Thread(() -> {
            System.out.println("thread a start....");
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String task = "task" + i;
                try {
                    //固定500ms生产一个
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ll.add(task);
                lock.notifyAll();
                System.out.printf("%s produced%n", task);
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread b start....");
            while (true){
                String task = ll.pop();
                try {
                    //随机休眠 1-1000 ms
                    Thread.sleep(new Random(1000).nextInt());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("%s consumed...", task);
                if (ll.isEmpty()){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }


    @Test
    public void test18(){
        Calendar calendar = Calendar.getInstance();
        //开始：一个月之前的日期
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println(sdf.format(calendar.getTime()));
    }

    @Test
    public void test19() throws ExecutionException {
        LoadingCache<String,String> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                System.out.println("加载数据");
                return "value";
            }
        });

        System.out.println(cache.get("key"));
        cache.refresh("key");
        System.out.println(cache.get("key"));
        System.out.println(cache.get("key"));
    }

    @Test
    public void test20(){
        String str = "1";

        List<Integer> blackCityIdList = Splitter.on(",").splitToList(str)
                .stream().map(Integer::parseInt).collect(Collectors.toList());

        System.out.println(blackCityIdList.contains(1));

        int[] arr = new int[]{1,2,3};
        Arrays.sort(arr);
        System.out.println(arr);

        HashMap<Integer,Integer> preNum = new HashMap<>();
        preNum.getOrDefault(1,0);

    }

    @Test
    public void test21() {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;

        //前缀和以及出现的次数
        HashMap<Integer, Integer> preNum = new HashMap<>();
        preNum.put(0,1);
        int pre = 0;
        int total = 0;
        for (int num : nums) {
            pre += num;
            total += preNum.getOrDefault( pre - k, 0);
            preNum.put(pre, preNum.getOrDefault(pre, 0) + 1);
        }
        System.out.println(total);
    }

    @Test
    public void test22() {
        int[] arr = new int[]{2, 3, 4, 1, 2, 3, 4, 5, 2, 6, 7, 3};

        Map<Integer, Integer> leftNumIndex = new HashMap<>();

        Integer max = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (!leftNumIndex.containsKey(num)) {
                leftNumIndex.put(num, i);
            } else {
                Integer leftIndex = leftNumIndex.get(num);
                max = Math.max(max, i - leftIndex + 1);
            }
        }

        System.out.println(max);
    }
}
