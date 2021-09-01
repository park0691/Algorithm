package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 종점_22867 {
	static int N, cnt, max;
	static Queue<Time> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] temp = st.nextToken().split(":");
			Time t = new Time(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2].substring(0, 2)), Integer.parseInt(temp[2].substring(3, 6)), true);
			pq.offer(t);
			temp = st.nextToken().split(":");
			Time t2 = new Time(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2].substring(0, 2)), Integer.parseInt(temp[2].substring(3, 6)), false);
			pq.offer(t2);
		}
		while (!pq.isEmpty()) {
			if (pq.poll().start) cnt++;
			else cnt--;
			max = Math.max(max, cnt);
		}
		bw.write(Integer.toString(max));
		bw.flush();
	}
	
	static class Time implements Comparable<Time>{
		int h, m, s, ss;
		boolean start;
		public Time(int h, int m, int s, int ss, boolean start) {
			super();
			this.h = h;
			this.m = m;
			this.s = s;
			this.ss = ss;
			this.start = start;
		}
		
		@Override
		public int compareTo(Time o) {

			if (this.h != o.h) return this.h - o.h;
			else if (this.m != o.m) return this.m - o.m;
			else if (this.s != o.s) return this.s - o.s;
			else if (this.ss != o.ss) return this.ss - o.ss;
			else if (this.start != o.start) return Boolean.compare(this.start, o.start);
			return 0;
		}
	}
}
