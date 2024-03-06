package programmers;

import java.util.*;

public class 실패율 {
    class Stage {
        int n;
        float failRate;
        public Stage(int n, float failRate) {
            this.n = n;
            this.failRate = failRate;
        }
    }

    public int[] solution(int N, int[] stages) {
        // 각 스테이지별 도전 중 유저 카운트
        Map<Integer, Integer> userCount = new HashMap<>();
        // 각 스테이지별 도전한 유저 카운트 (현재 카운트보다 크거나 같다)
        Map<Integer, Integer> stageCount = new HashMap<>();

        for (int i = 0; i < stages.length; i++) {
            int n = stages[i];

            userCount.put(n, userCount.getOrDefault(n, 0) + 1);
            for (int j = 1; j <= n; j++) {
                stageCount.put(j, stageCount.getOrDefault(j, 0) + 1);
            }
        }

        Queue<Stage> pq = new PriorityQueue<>(
                (s1, s2) -> Float.compare(s1.failRate, s2.failRate) == 0 ?
                        s1.n - s2.n : Float.compare(s2.failRate, s1.failRate)
        );

        for (int i = 1; i <= N; i++) {
            int user = userCount.getOrDefault(i, 0);
            int stage = stageCount.getOrDefault(i, 0);
            float failRate;
            if (user == 0 || stage == 0) failRate = 0F;
            else failRate = (float) user / stage;
            pq.offer(new Stage(i, failRate));
        }

        int[] answer = new int[N];
        int i = 0;
        while (!pq.isEmpty()) {
            Stage s = pq.poll();
            answer[i++] = s.n;
        }

        return answer;
    }
}
