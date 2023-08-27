package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    N보다 작은 M에 대해서 M = a * b 이면, a 와 b 중 적어도 하나는 N의 제곱근보다 같거나 작아야 한다.
    따라서, N보다 작은 소수를 찾을 때, N의 제곱근보다 작은 수의 배수만 검사해도 그 구간의 소수를 판별할 수 있다.
 */
public class 소수구하기_1929 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean prime[] = new boolean[N + 1];
        for (int i = 2; i <= N; i++) prime[i] = true;

        int temp = (int) Math.ceil(Math.sqrt(N));
        int j;
        for (int i = 2; i <= temp; i++) {
            if (!prime[i]) continue;
            j = i;
            while (j + i <= N) {
                j += i;
                prime[j] = false;
            }
        }

        for (int i = M; i <= N; i++)
            if (prime[i])
                System.out.println(i);

        br.close();
    }
}
