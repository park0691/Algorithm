package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class 로마숫자_2608 {
    static Map<Character, Integer> map = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int sum = getSum(a) + getSum(b);
        System.out.println(sum);
        System.out.println(getString(sum));
    }

    static int getSum(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        int i = 0;
        for ( ; i < chars.length - 1; i++) {
            if (map.get(chars[i]) < map.get(chars[i + 1])) {
                sum -= map.get(chars[i]);
            } else {
                sum += map.get(chars[i]);
            }
        }
        sum += map.get(chars[i]);
        return sum;
    }

    static String getString(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            if (n >= 1000) {
                sb.append('M');
                n -= 1000;
            } else if (n >= 900) {
                sb.append("CM");
                n -= 900;
            } else if (n >= 500) {
                sb.append('D');
                n -= 500;
            } else if (n >= 400) {
                sb.append("CD");
                n -= 400;
            } else if (n >= 100) {
                sb.append('C');
                n -= 100;
            } else if (n >= 90) {
                sb.append("XC");
                n -= 90;
            } else if (n >= 50) {
                sb.append('L');
                n -= 50;
            } else if (n >= 40) {
                sb.append("XL");
                n -= 40;
            } else if (n >= 10) {
                sb.append('X');
                n -= 10;
            } else if (n >= 9) {
                sb.append("IX");
                n -= 9;
            } else if (n >= 5) {
                sb.append('V');
                n -= 5;
            } else if (n >= 4) {
                sb.append("IV");
                n -= 4;
            } else {
                sb.append('I');
                n -= 1;
            }
        }
        return sb.toString();
    }
}
