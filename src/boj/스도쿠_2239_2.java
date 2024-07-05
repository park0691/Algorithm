package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 스도쿠_2239_2 {

    static int[][] map;
    static int zeroCnt = 0;
    static int[] rowCheckArr = new int[10];
    static int[] colCheckArr = new int[10];
    static int[] groupCheckArr = new int[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];

        // 각 칸의 그룹 번호 저장
        for (int i = 1; i <= 9; i++) {
            String curInputs = br.readLine();
            for (int j = 1; j <= 9; j++) {
                int nc = (j - 1) / 3;
                int nr = (i - 1) / 3;
                int group = (nc + 1) + 3 * nr;

                int value = curInputs.charAt(j - 1) - '0';
                if (value == 0)  {
                    zeroCnt++;
                    continue;
                }

                map[i][j] = value;
                rowCheckArr[i] |= (1 << value);
                colCheckArr[j] |= (1 << value);
                groupCheckArr[group] |= (1 << value);
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (map[i][j] != 0) continue;
                dfs(i, j, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    // 현재 위치에 숫자 구하고 다음 칸으로 넘어가기
    static boolean dfs(int y, int x, int depth) {
        int group = (((x - 1) / 3) + 1) + 3 * ((y - 1) / 3);
        // 넣을 n 탐색
        for (int n = 1; n <= 9; n++) {
            if ((rowCheckArr[y] & (1 << n)) != 0 || (colCheckArr[x] & (1 << n)) != 0 || (groupCheckArr[group] & (1 << n)) != 0) continue;
            map[y][x] = n;
            rowCheckArr[y] |= (1 << n);
            colCheckArr[x] |= (1 << n);
            groupCheckArr[group] |= (1 << n);

            if (depth == zeroCnt) return true;

            // 현재 행 탐색 후, 다음 행으로 이동
            boolean notFound = false;
            int j;
            for (int i = y; i <= 9; i++) {
                if (i > y) j = 1;
                else j = x + 1;
                for (; j <= 9; j++) {
                    if (map[i][j] != 0) continue;
                    if (dfs(i, j, depth + 1)) return true;
                    else {
                        notFound = true;
                        break;
                    }
                }
                if (notFound) break;
            }

            if (notFound) {
                map[y][x] = 0;
                rowCheckArr[y] &= ~(1 << n);
                colCheckArr[x] &= ~(1 << n);
                groupCheckArr[group] &= ~(1 << n);
            }
        }
        // 지금까지 숫자 9개 다 넣을 수 없으면 false -> 직전 호출 메소드 n 선택도 잘못 된거야
        return false;
    }
}
