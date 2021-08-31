package swea;

import java.util.Scanner;

public class 오셀로게임_4615 {
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N;
	// 1 B, 2 W
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			int m = sc.nextInt();
			int[][] map = new int[N + 1][N + 1];
			
			map[N / 2][N / 2] = 2;
			map[N / 2][(N / 2) + 1] = 1;
			map[(N / 2) + 1][N / 2] = 1;
			map[(N / 2) + 1][(N / 2) + 1] = 2;
			
			for (int i = 0; i < m; i++) {
				int c = sc.nextInt();
				int r = sc.nextInt();
				int color = sc.nextInt();
				
				check(map, r, c, color);
			}
			
			int blackCount = 0, whiteCount = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1) blackCount++;
					else if (map[i][j] == 2) whiteCount++;
				}
			}
			System.out.println("#" + t + " " + blackCount + " " + whiteCount);
		}
	}
	
	static void check(int[][] map, int y, int x, int color) {
		map[y][x] = color;
		
		// 8방 탐색 (동일한 색 돌 체크)
		for (int i = 0; i < 8; i++) {
			int ny = y;
			int nx = x;
			
			while (true) {
				ny += dy[i];
				nx += dx[i];
				
				if (nx < 1 || nx > N || ny < 1 || ny > N || map[ny][nx] == 0) break;
				// 현재 색깔과 동일한 영역이라면
				if (map[ny][nx] == color) {
					int cx = x;
					int cy = y;
					// 그 내부 영역을 현재 색깔로 모두 변경
					while (!(cx == nx && cy == ny)) {
						map[cy][cx] = color;
						cx += dx[i];
						cy += dy[i];
					}
					break;
				}
			}
		}
	}
}
