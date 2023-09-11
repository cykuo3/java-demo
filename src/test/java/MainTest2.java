import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainTest2 {

    @Test
    public void test(){

        System.out.println("start");


        int[] arr = new int[]{7,9,1,2,3,5,6};

        int target = 5;

        int leftIndex = 0;
        int rightIndex = arr.length-1;
        int middleIndex = 0;
        while (leftIndex < rightIndex){
            middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (arr[middleIndex] == target){
                System.out.println("target=" + middleIndex);
                return;
            }
            if (arr[leftIndex] < arr[middleIndex]){
                //左侧升序
                if (arr[middleIndex] > target && arr[leftIndex] < target){
                    rightIndex = middleIndex - 1;
                }else {
                    leftIndex = middleIndex + 1;
                }
            }else {
                //右侧升序
                if (arr[middleIndex] < target && arr[rightIndex] > target){
                    leftIndex = middleIndex + 1;
                }else {
                    rightIndex = middleIndex - 1;
                }
            }
        }
    }

    @Test
    public void test2(){
        Map<Integer,String> map = new HashMap<>();
        map.put(null,"123");
        System.out.println(map.get(null));


        Map<Integer,String> map2 = new ConcurrentHashMap<>();
        map2.put(null,"123");
        System.out.println(map2.get(null));

    }
}
