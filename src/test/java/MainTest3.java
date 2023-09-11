public class MainTest3 {

    public static void main(String[] args) {
        // 两个升序的int数组
        //找出第K大的元素

        int[] arr1 = new int[]{1, 2, 3, 8};

        int[] arr2 = new int[]{3, 4, 5, 6, 7};

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = 8;

        int total = 0;
        while (k > 0) {
            boolean flag = true;

            if (i >= 0 && j >= 0) {
                flag = arr1[i] > arr2[j];
            } else {
                if (i == -1) {
                    flag = false;
                }
                if (j == -1) {
                    flag = true;
                }
            }

            if (flag) {
                total = arr1[i];
                i--;
            } else {
                total = arr2[j];
                j--;
            }
            k--;
        }
        System.out.println(total);
    }
}
