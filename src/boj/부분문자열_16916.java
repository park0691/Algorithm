package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 부분문자열_16916 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] parent = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int[] pi = new int[pattern.length];

        for (int i = 1, j = 0; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
            if (pattern[i] == pattern[j]) pi[i] = ++j;
        }

        int ans = 0;
        for (int i = 0, j = 0; i < parent.length; i++) {
            while (j > 0 && parent[i] != pattern[j]) j = pi[j - 1];
            if (parent[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    ans = 1;
                    break;
                }
                else j++;
            }
        }

        System.out.println(ans);
    }
}
