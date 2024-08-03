package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 휴게소세우기_1477 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < N + 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 시작점을 0으로 잡으면 -> (0 + 1) / 2 연산 -> mid 값 0
        System.out.println(lowerBound(1, L, arr, M));
    }

    static int lowerBound(int left, int right, int[] arr, int M) {
        while (left < right) {
            int mid = left + (right - left) / 2;

            int count = getCount(mid, arr);
            // count 더 늘려야 -> 거리 mid 줄여 -> M과 더 근접
            // count 같아도  -> 거리 mid 줄여 (더 적은 mid 찾아야)
            if (count <= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static int getCount(int val, int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            count += diff / val;
            if (diff % val == 0) count--;
        }
        return count;
    }
}
