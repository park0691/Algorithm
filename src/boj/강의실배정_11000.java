package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 강의실배정_11000 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        Time[] times = new Time[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(times, (t1, t2) -> t1.start == t2.start ? t1.end - t2.end : t1.start - t2.start);

        Queue<Integer> pq = new PriorityQueue<>();
        for (Time t : times) {
            if (pq.isEmpty() || pq.peek() > t.start) {
                pq.add(t.end);
            } else {
                pq.poll();
                pq.add(t.end);
            }
            ans = Math.max(ans, pq.size());
        }
        System.out.println(ans);
    }
    
    static class Time {
        int start, end;
        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
