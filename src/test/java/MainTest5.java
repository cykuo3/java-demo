import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MainTest5 {

    @Test
    public void test1() {
        System.out.println("start");

    }

    @Test
    public void test2() {
        int[][] flowers = new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] people = new int[]{2, 3, 7, 11};
        System.out.println(Arrays.toString(fullBloomFlowers(flowers, people)));
//
//
//        int[][] flowers2 = new int[][]{{1, 10}, {3, 3}};
//        int[] people2 = new int[]{3, 3, 2};
//        System.out.println(Arrays.toString(fullBloomFlowers(flowers2, people2)));

//        int[][] flowers3 = new int[][]{{19, 37}, {19, 38}, {19, 35}};
//        int[] people3 = new int[]{6, 7, 21, 1, 13, 37, 5, 37, 46, 43};
//        System.out.println(Arrays.toString(fullBloomFlowers(flowers3, people3)));
    }


    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        //维护n天时在花期的数量
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        //初始化默认值
        for (int day : people) {
            hashMap.put(day, 0);
        }

        Set<Integer> daySet = hashMap.keySet();

        //所有天数的数组
        Integer[] dayArr = daySet.toArray(new Integer[0]);
        Arrays.sort(dayArr);

        int[] startArr = new int[flowers.length];
        int[] endArr = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            startArr[i] = flowers[i][0];
            endArr[i] = flowers[i][1];
        }
        //对结束节点进行处理
        for (int i = 0; i < endArr.length; i++) {
            endArr[i] = endArr[i] + 1;
        }

        Arrays.sort(startArr);
        Arrays.sort(endArr);

        int index1 = 0;
        int index2 = 0;

        int currentNum = 0;
        //每一天
        int i = 0;
        while(i < dayArr.length) {
            if(index2 == endArr.length){
                break;
            }
            int minIndex = 0;
            int sum = 0;
            if (index1 == startArr.length) {
                //数组一已经越界
                //败了一朵
                minIndex = endArr[index2];
                sum = -1;
                index2++;
            } else {
                if (startArr[index1] < endArr[index2]) {
                    //开了一朵
                    minIndex = startArr[index1];
                    sum = 1;
                    index1++;
                } else if (startArr[index1] > endArr[index2]) {
                    //败了一朵
                    minIndex = endArr[index2];
                    sum = -1;
                    index2++;
                } else {
                    minIndex = startArr[index1];
                    index1++;
                    index2++;
                }
            }
            while (i < dayArr.length && minIndex > dayArr[i]) {
                hashMap.put(dayArr[i], currentNum);
                i++;
            }
            currentNum += sum;
        }

        int[] answer = new int[people.length];
        for (int code = 0; code < answer.length; code++) {
            answer[code] = hashMap.get(people[code]);
        }
        return answer;
    }
}
