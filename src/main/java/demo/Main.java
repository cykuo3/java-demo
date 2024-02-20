package demo;

public class Main {
    public static int totalNum = 0;

    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        System.out.println("hello world");

        //   0
        // 1   3
        //   2

        int[][] grid = new int[][]{{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
        System.out.println(grid.length);
        System.out.println(grid[0].length);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, -1);
                    totalNum++;
                }
            }
        }
        System.out.println("岛屿数量=" + totalNum);
    }

    public static void dfs(int[][] arr, int i, int j, int f) {
        if (i < 0 || j < 0 || i > arr.length - 1 || j > arr[i].length - 1 || arr[i][j] == 0) {
            return;
        }
        System.out.println("i=" + i + "," + "j=" + j);
        if (arr[i][j] == 1) {
            arr[i][j] = 0;
        }

        if (f != 1) {
            dfs(arr, i + 1, j, 0);
        }
        if (f != 0) {
            dfs(arr, i - 1, j, 1);
        }
        if (f != 3) {
            dfs(arr, i, j + 1, 2);
        }
        if (f != 2) {
            dfs(arr, i, j - 1, 3);
        }
    }
}