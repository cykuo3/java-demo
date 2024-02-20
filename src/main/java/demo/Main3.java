package demo;

public class Main3 {
    public static void main(String[] args) {
        // 字符串数字 转整型
        String str = "-456782";
        int num = 0;
        char[] charArray = str.toCharArray();
        int p = 1;
        for (int code = charArray.length - 1; code >= 0; code--) {
            if(charArray[code] == '-'){
                num = -num;
                continue;
            }
            int n = charArray[code] - 48;
            System.out.println(n);
            num += n * p;
            p *=10;
        }
        System.out.println(num);
    }
}
