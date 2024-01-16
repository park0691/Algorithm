package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 하노이의탑_11729 {

    static StringBuilder sb = new StringBuilder();
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        hanoi(1, 2, 3, n);

        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    static void hanoi(int start, int mid, int to, int n) {
        if (n == 1) {
            sb.append(start).append(" ").append(to).append("\n");
            cnt++;
            return;
        }

        // 1 -> 2, n - 1개 이동
        hanoi(start, to, mid, n - 1);

        // 1 -> 3, 가장 큰 원판 이동
        sb.append(start).append(" ").append(to).append("\n");
        cnt++;

        // 2 -> 3, n - 1개 이동
        hanoi(mid, start, to, n - 1);
    }
}
