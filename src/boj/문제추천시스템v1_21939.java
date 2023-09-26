package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
/*
 * TreeSet 이용
 */
public class 문제추천시스템v1_21939 {
	static int N, P, L, M;
	static TreeSet<Integer[]> ts = new TreeSet<>((a, b) -> a[1].equals(b[1]) ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
	static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ts.add(new Integer[] { P, L });
			map.put(P, L);
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) {
				P = Integer.parseInt(st.nextToken());
				L = Integer.parseInt(st.nextToken());
				ts.add(new Integer[] { P, L });
				map.put(P, L);	
			} else if (command.equals("solved")) {
				int problem = Integer.parseInt(st.nextToken());
				ts.remove(new Integer[] { problem, map.get(problem) });
				map.remove(problem);
			} else {
				int n = Integer.parseInt(st.nextToken());
				sb.append(n == 1 ? ts.last()[0] : ts.first()[0]).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
