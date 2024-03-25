package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2_12015 {
    static int[] LIS;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        LIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = arr[0];
        int length = 1;

        for (int i = 1; i < N; i++) {
            if (LIS[length - 1] < arr[i]) {
                length++;
                LIS[length - 1] = arr[i];
            } else {
                int idx = lowerBound(0, length, arr[i]);
                LIS[idx] = arr[i];
            }
        }
        System.out.println(length);
    }

    private static int lowerBound(int start, int end, int key) {
        int lo = start;
        int hi = end;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2; // 중간위치를 구한다.
            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= LIS[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
