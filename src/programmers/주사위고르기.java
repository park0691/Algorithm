package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 주사위고르기 {

    static int size;
    static int[] tgt;
    static int[][] dice;
    static List<int[]> combinations = new ArrayList<>();        // 주사위 A 조합

    static public int[] solution(int[][] d) {
        size = d.length;
        tgt = new int[size / 2];
        dice = d;

        // 주사위 A 조합 구하기
        comb(0, 0);

        int ansIdx = 0;
        int ansWinCnt = -1;

        for (int a = 0; a < combinations.size(); a++) {
            int[] combA = combinations.get(a);
            int[] combB = new int[size / 2];
            boolean[] select = new boolean[size];
            for (int i = 0; i < size / 2; i++) {
                select[combA[i]] = true;
            }
            for (int i = 0, idx = 0; i < size; i++) {
                if (select[i]) continue;
                combB[idx++] = i;
            }

            List<Integer> scoreA = new ArrayList<>();
            List<Integer> scoreB = new ArrayList<>();

            combScore(0, 0, combA, scoreA);
            combScore(0, 0, combB, scoreB);

            Collections.sort(scoreA);
            Collections.sort(scoreB);

            int winCnt = 0;

            // lower_bound binary search
            for (int n : scoreA) {
                int left = 0, right = scoreB.size();

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (n <= scoreB.get(mid)) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                winCnt += left;
            }

            if (ansWinCnt < winCnt) {
                ansIdx = a;
                ansWinCnt = winCnt;
            }
        }

        int[] answer = new int[size / 2];
        int[] combA = combinations.get(ansIdx);
        for (int i = 0; i < answer.length; i++) {
            answer[i] = combA[i] + 1;
        }

        return answer;
    }

    static void combScore(int n, int depth, int[] comb, List<Integer> score) {
        if (depth == size / 2) {
            score.add(n);
            return;
        }

        for (int i = 0; i < 6; i++) {
            combScore(n + dice[comb[depth]][i], depth + 1, comb, score);
        }
    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == size / 2) {
            combinations.add(tgt.clone());
            return;
        }

        for (int i = srcIdx; i < size; i++) {
            tgt[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};

        System.out.println(Arrays.toString(solution(input)));
    }
}