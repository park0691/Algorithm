package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집_3109 {
	static int R, C, cnt;
	static int[] dy = { -1, 0, 1 };
	static char[][] map;
	static boolean found;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		
		for (int i = 0; i < R; i ++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			found = false;
			DFS(i, 0);
		}
		System.out.println(cnt);
	}
	
	static void DFS(int y, int x) {	
		if (x == C - 1) {
			found = true;
			map[y][x] = 'x';
			cnt++;
			return;
		}
		
		int nx = x + 1;
		
		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			
			if (ny < 0 || ny >= R || nx >= C || map[ny][nx] == 'x') continue;
			
			// 갔던 경로는 파이프라인 가능 유뮤와 상관없이 무조건 마킹한다.
			// 아래 행에서 다시 갔던 경로를 반복하게 되므로 비효율적임 (시간 초과 발생)
			map[ny][nx] = 'x';
			
			DFS(ny, nx);
			
			if (found) return;
		}
	}
}
