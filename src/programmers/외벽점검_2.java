package programmers;

class 외벽점검_2 {

    static int answer;
    static int[] src, tgt, point, weak;
    static boolean[] select;

    /**
     * 친구 투입 순서, dist 순열
     * 모든 K개의 외벽 검사 = weak 배열에서 임의 연속된 K개 정점을 방문하는 것과 동일
     * dist 배열의 모든 순열에 대해 weak 배열의 모든 구간을 탐색한다.
     */

    static public int solution(int n, int[] weak, int[] dist) {
        point = new int[weak.length * 2];
        System.arraycopy(weak, 0, point, 0, weak.length);
        for (int i = weak.length; i < point.length; i++)
            point[i] = point[i - weak.length] + n;

        answer = Integer.MAX_VALUE;
        src = dist;
        tgt = new int[src.length];
        select = new boolean[src.length];
        외벽점검_2.weak = weak;

        perm(0);

        if (answer == Integer.MAX_VALUE) return -1;

        return answer;
    }

    static void find() {
        for (int i = 0; i < weak.length; i++) {
            int beginValue = point[i];
            int endValue = point[i + weak.length - 1];
            int nextIdx = 0;
            // 친구 투입 시도
            for (int j = 0; j < tgt.length; j++) {
                beginValue += tgt[j];
                // 모든 지점 방문 시
                if (beginValue >= endValue) {
                    answer = Math.min(answer, j + 1);
                    break;
                }
                // 다음 검사 취약 지점 구하기
                while (++nextIdx < point.length - 1 && point[nextIdx] <= beginValue) ;
                beginValue = point[nextIdx];
            }
        }
    }

    static void perm(int tgtIdx) {
        if (tgtIdx == tgt.length) {
            find();
            return;
        }

        for (int i = 0; i < src.length; i++) {
            if (select[i]) continue;
            tgt[tgtIdx] = src[i];
            select[i] = true;
            perm(tgtIdx + 1);
            select[i] = false;
        }
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] dist = {3, 5, 7};
        System.out.println(solution(n, weak, dist));
    }
}