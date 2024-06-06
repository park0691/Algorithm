package programmers;

import java.util.*;

public class 디스크컨트롤러 {

    public static int solution(int[][] jobs) {
        // jobs : 요청 시간 순 정렬
        Arrays.sort(jobs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // pq : 소요 시간 오름차순 정렬
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int answer = 0;
        int jIdx = 0;
        int curSec = 0;
        int count = 0;

        while (count < jobs.length) {
            // curSec까지 요청 들어온 작업을 pq에 넣기
            while (jIdx < jobs.length && jobs[jIdx][0] <= curSec) {
                pq.add(jobs[jIdx++]);
            }

            // 큐가 안 비어있다면 -> 작업 끝나는 시점에 수행해야 될 작업 남아 있어
            if (!pq.isEmpty()) {
                // 수행 시간이 가장 짧은거 꺼내서 수행하기
                int[] cur = pq.poll();
                curSec += cur[1];
                answer += (curSec - cur[0]);
                count++;
            } else {
                // 큐가 비어 있다면 -> 작업 끝나는 시점(curSec)에 수행해야 될 작업 없어
                // jIdx 앞 시점까지 탐색한 작업 전부 완료됨 의미
                // jIdx 해당 작업 요청 시점과 일치시켜 -> curSec를 jobs[jIdx][0] 요청 시점과 일치시켜
                curSec = jobs[jIdx][0];
            }
        }

        return answer / jobs.length;
    }

    public static void main(String[] args) {
        int[][] e1 = {{0, 3}, {1, 9}, {2, 6}};

        System.out.println(solution(e1));
    }
}
