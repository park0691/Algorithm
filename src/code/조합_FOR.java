package code;

import java.util.Arrays;

public class 조합_FOR {
    static int COUNT = 0;
    static int[] src = {1, 2, 3, 4, 5};
    static int[] tgt = new int[3];

    public static void main(String[] args) {
        comb(0, 0);
        System.out.println(COUNT);
    }

    static void comb(int srcIdx, int tgtIdx) {
        // 기저 조건
        if (tgtIdx == tgt.length) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }

        for (int i = srcIdx; i < src.length; i++) {
            tgt[tgtIdx] = src[i];
            comb(i + 1, tgtIdx + 1);
        }
    }
}
