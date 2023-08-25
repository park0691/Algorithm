package code;

import java.util.Arrays;

public class 순열 {
    static int COUNT = 0;
    static int src[] = {1, 2, 3, 4, 5};
    static int tgt[] = new int[3];
    static boolean[] select = new boolean[src.length];  // 중복 순열 X

    public static void main(String[] args) {
        perm(0);
        System.out.println(COUNT);
    }

    static void perm(int tgtIdx) {
        if (tgtIdx == tgt.length) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }

        for (int i = 0; i < src.length; i++) {
            if (select[i]) continue;
            // 현재 자리에 해당 숫자 선택
            tgt[tgtIdx] = src[i];

            select[i] = true;
            perm(tgtIdx + 1);
            select[i] = false;
        }
    }
}
