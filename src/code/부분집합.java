package code;
// SUBSET
public class 부분집합 {
    static int COUNT = 0;
    static int[] src = {1, 2, 3, 4, 5};

    // 순열처럼 현재 구성의 이전 사용 여부를 체크 X
    // 부분집합을 표현하는 자료구조
    static boolean[] select = new boolean[src.length];

    public static void main(String[] args) {
        subset(0);
        System.out.println(COUNT);
    }

    static void subset(int srcIdx) {
        if (srcIdx == src.length) {
            printSubset();
            COUNT++;
            return;
        }

        select[srcIdx] = true;
        subset(srcIdx + 1);
        select[srcIdx] = false;
        subset(srcIdx + 1);
    }

    static void printSubset() {
        for (int i = 0; i < select.length; i++) {
            if (select[i]) System.out.print(src[i] + " ");
        }
        System.out.println();
    }
}
