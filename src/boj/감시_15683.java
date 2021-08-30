package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * Array, DFS
 */
public class 감시_15683 {
	static int N, M, wallCnt, ans;
	static int[][] map;
	static List<CCTV> tvs = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n >= 1 && n <= 5) tvs.add(new CCTV(i, j, n));
				else if (n == 6) wallCnt++;
			}
		}
		DFS(0, map, 0);
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void DFS(int n, int[][] map, int cnt) {
		
		if (n == tvs.size()) {
			int zeroCnt = (N * M) - wallCnt - tvs.size() - cnt;
			ans = Math.min(ans, zeroCnt);
			return;
		}
		
		CCTV curTV = tvs.get(n);
		
		switch (curTV.n) {
		case 1:
			for (int d = 0; d < 4; d++) {
				int[][] curMap = cloneMap(map);
				int curCnt = watch(curMap, curTV.y, curTV.x, d);
				DFS(n + 1, curMap, cnt + curCnt);
			}
			break;
		case 2:
			for (int d = 0; d < 2; d++) {
				int[][] curMap = cloneMap(map);
				int curCnt = watch(curMap, curTV.y, curTV.x, d);
				curCnt += watch(curMap, curTV.y, curTV.x, d + 2);
				DFS(n + 1, curMap, cnt + curCnt);
			}
			break;
		case 3:
			for (int d = 0; d < 4; d++) {
				int[][] curMap = cloneMap(map);
				int curCnt = watch(curMap, curTV.y, curTV.x, d);
				curCnt += watch(curMap, curTV.y, curTV.x, (d + 1) % 4);
				DFS(n + 1, curMap, cnt + curCnt);
			}
			break;
		case 4:
			for (int d = 0; d < 4; d++) {
				int[][] curMap = cloneMap(map);
				int curCnt = watch(curMap, curTV.y, curTV.x, d);
				curCnt += watch(curMap, curTV.y, curTV.x, (d + 1) % 4);
				curCnt += watch(curMap, curTV.y, curTV.x, (d + 2) % 4);
				DFS(n + 1, curMap, cnt + curCnt);
			}
			break;
		case 5:
			int[][] curMap = cloneMap(map);
			int curCnt = watch(curMap, curTV.y, curTV.x, 0);
			curCnt += watch(curMap, curTV.y, curTV.x, 1);
			curCnt += watch(curMap, curTV.y, curTV.x, 2);
			curCnt += watch(curMap, curTV.y, curTV.x, 3);
			DFS(n + 1, curMap, cnt + curCnt);
			break;
		}
	}
	
	static int watch(int[][] curMap, int y, int x, int d) {
		/*
		 * d (1~4) : 우, 하, 좌, 상
		 * 감시 영역은 7로 마킹
		 */
		int cnt = 0;
		switch(d) {
		case 0:
			for (int i = x + 1; i < M; i++) {
				if (curMap[y][i] == 6) break;
				if (curMap[y][i] >= 1 && curMap[y][i] <= 5) continue;
				if (curMap[y][i] == 7) continue;
				curMap[y][i] = 7;
				cnt++;
			}
			break;
		case 1:
			for (int i = y + 1; i < N; i++) {
				if (curMap[i][x] == 6) break;
				if (curMap[i][x] >= 1 && curMap[i][x] <= 5) continue;
				if (curMap[i][x] == 7) continue;
				curMap[i][x] = 7;
				cnt++;
			}
			break;
		case 2:
			for (int i = x - 1; i >= 0; i--) {
				if (curMap[y][i] == 6) break;
				if (curMap[y][i] >= 1 && curMap[y][i] <= 5) continue;
				if (curMap[y][i] == 7) continue;
				curMap[y][i] = 7;
				cnt++;
			}
			break;
		case 3:
			for (int i = y - 1; i >= 0; i--) {
				if (curMap[i][x] == 6) break;
				if (curMap[i][x] >= 1 && curMap[i][x] <= 5) continue;
				if (curMap[i][x] == 7) continue;
				curMap[i][x] = 7;
				cnt++;
			}
			break;
		}
		
		return cnt;
	}

	static int[][] cloneMap(int[][] map) {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++)
			result[i] = Arrays.copyOf(map[i], M);
		return result;
	}
	
	static class CCTV {
		int y, x, n;

		public CCTV(int y, int x, int n) {
			super();
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}
}
