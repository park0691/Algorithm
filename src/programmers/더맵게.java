package programmers;

import java.util.*;

public class 더맵게 {
    public int solution(int[] scoville, int K) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < scoville.length; i++)
            pq.offer(scoville[i]);
        int curK = pq.peek();
        int answer = 0;
        while (curK < K) {
            int a = pq.poll();
            int b = pq.poll();
            int newK = a + 2 * b;
            answer++;
            pq.offer(newK);
            curK = pq.peek();
            if (pq.size() == 1) {
                if (curK < K) return -1;
                else break;
            }
        }

        return answer;
    }
}
