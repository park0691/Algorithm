package code;

import java.util.Arrays;

public class 조합_재귀 {
    static int COUNT = 0;
    static int[] src = {1, 2, 3, 4, 5};
    static int[] tgt = new int[3];

    public static void main(String[] args) {
        comb(0, 0);
        System.out.println(COUNT);
    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }

        if (srcIdx == src.length) return;

        tgt[tgtIdx] = src[srcIdx];
        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);
    }
}
