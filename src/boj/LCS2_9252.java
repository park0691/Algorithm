package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCS2_9252 {
    static int[][] s;
    static char[] sArray1, sArray2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        sArray1 = s1.toCharArray();
        sArray2 = s2.toCharArray();
        s = new int[1001][1001];

        for (int i = 0; i < sArray1.length; i++) {
            for (int j = 0; j < sArray2.length; j++) {
                if (sArray1[i] == sArray2[j]) s[i + 1][j + 1] = s[i][j] + 1;
                else s[i + 1][j + 1] = Math.max(s[i][j + 1], s[i + 1][j]);
            }
        }

        System.out.println(s[sArray1.length][sArray2.length]);

        if (s[sArray1.length][sArray2.length] == 0) {
            return;
        }

        print(sArray1.length, sArray2.length);
    }

    static void print(int i, int j) {
        if (s[i][j] == 0) return;
        if (sArray1[i - 1] == sArray2[j - 1]) {
            print(i - 1, j - 1);
            System.out.print(sArray1[i - 1]);
        } else {
            if (s[i - 1][j] > s[i][j - 1]) print(i - 1, j);
            else print(i, j - 1);
        }
    }
}
