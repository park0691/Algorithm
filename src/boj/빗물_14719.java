package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물_14719 {

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] map = new int[W];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        // 가로 기준 i = 1 -> W - 2 까지 탐색
        for (int i = 1; i < W - 1; i++) {
            int left = 0;
            // 왼쪽 기준 가장 큰 값 찾기
            for (int j = 0; j < i; j++) left = Math.max(left, map[j]);
            int right = 0;
            // 오른쪽 기준 가장 큰 값 찾기
            for (int j = W - 1; j > i; j--) right = Math.max(right, map[j]);
            // 왼쪽, 오른쪽 중 작은 값 기준으로 현재 빗물 높이 빼기
            sum += Math.max(0, Math.min(left, right) - map[i]);
        }

        System.out.println(sum);
    }
}
