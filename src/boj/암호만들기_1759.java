package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기_1759 {
    static int L, C;
    static char[] arr;
    static char[] tgt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        tgt = new char[L];
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        // 사전식으로 가능성 있는 암호를 모두 출력
        comb(0, 0, 0);
        System.out.print(sb.toString());
    }

    static void comb(int srcIdx, int tgtIdx, int moCnt) {
        if (tgtIdx == tgt.length) {
            // 최소 한 개의 모음 (최소 두 개의 자음이 아님 / 반례 : 모두 모음)
            if (moCnt >= 1 && (tgt.length - moCnt) >= 2) {
                for (int i = 0; i < tgt.length; i++) {
                    sb.append(tgt[i]);
                }
                sb.append('\n');
            }
            return;
        }

        for (int i = srcIdx; i < arr.length; i++) {
            tgt[tgtIdx] = arr[i];
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                comb(i + 1, tgtIdx + 1, moCnt + 1);
            } else {
                comb(i + 1, tgtIdx + 1, moCnt);
            }
        }
    }
}
