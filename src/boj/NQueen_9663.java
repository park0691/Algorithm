package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * Backtracking
 */
public class NQueen_9663 {
	static int N, cnt;
	static int[] col;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		col = new int[N + 1];
		
		DFS(1);
		
		System.out.println(cnt);
	}
	
	static void DFS(int row) {
		// 현재 퀸 놓을 행 : row
		// pruning >> 직전 (row - 1) 행에 놓은 퀸이 유망해야 row에 퀸을 놓을 수 있다. 
		if (!isAvailable(row - 1)) return;
		
		if (row > N) {
			cnt++;
			return;
		}
		// 현재 행의 1~N열에 퀸 놓기
		for (int i = 1; i <= N; i++) {
			col[row] = i;
			DFS(row + 1);
		}
	}
	static boolean isAvailable(int row) {
		// row 행에 놓은 퀸이 유망한지 체크
		// i : 이전 퀸
		// 이전 퀸과 같은 열 or 이전 퀸과 대각선 위치에 있는 경우 유망하지 않다.
		for (int i = 1; i < row; i++) {
			if (col[i] == col[row] || Math.abs(col[row] - col[i]) == row - i)
				return false;
		}
		
		return true;
	}
}
