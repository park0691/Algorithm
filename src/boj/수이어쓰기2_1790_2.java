package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기2_1790_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        if (getOrder(N) < k) {
            System.out.println(-1);
            System.exit(0);
        }

        long left = 0;
        long right = N;
        long mid = 0;

        // 문자열의 길이 > k : 더 작은 mid
        // 문자열의 길이 < k : 더 큰 mid
        while (left < right) {
            mid = left + (right - left) / 2;
            if (getOrder(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long order = getOrder(left);
        String numStr = String.valueOf(left);
        int n = numStr.length();
        System.out.println(numStr.charAt((int) (k - order + n  - 1)));
    }

    static long getOrder(long num) {
        long order = 0;
        String numStr = String.valueOf(num);
        for (int i = 1; i <= numStr.length(); i++) {
            order += (long)(Math.pow(10, i - 1)) * i * 9;
        }
        long tmp = ((long)(Math.pow(10, numStr.length())) - num - 1);
        order -= tmp * numStr.length();
        return order;
    }
}
