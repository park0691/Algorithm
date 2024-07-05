package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 스도쿠_2239 {

    static int[][] map, group;
    static int zeroCnt = 0;
    static Map<Integer, Set<Integer>> rowMap = new HashMap<>();
    static Map<Integer, Set<Integer>> colMap = new HashMap<>();
    static Map<Integer, Set<Integer>> groupMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        group = new int[10][10];

        for (int i = 1; i <= 9; i++) {
            rowMap.put(i, new HashSet<>());
            colMap.put(i, new HashSet<>());
            groupMap.put(i, new HashSet<>());
        }

        // 각 칸의 그룹 번호 저장
        for (int i = 1; i <= 9; i++) {
            Set<Integer> curRowSet = rowMap.get(i);
            char[] chars = br.readLine().toCharArray();
            for (int j = 1; j <= 9; j++) {
                // j - 1  : 0 1 2 3 4 5 6 7 8
                // nc     : 0 0 0 1 1 1 2 2 2
                // nc + 1 : 1 1 1 2 2 2 3 3 3
                int nc = (j - 1) / 3;
                int nr = (i - 1) / 3;
                group[i][j] = (nc + 1) + 3 * nr;

                int value = chars[j - 1] - '0';
                if (value == 0)  {
                    zeroCnt++;
                    continue;
                }

                map[i][j] = value;
                Set<Integer> curColSet = colMap.get(j);
                Set<Integer> curGroupSet = groupMap.get(group[i][j]);
                curRowSet.add(value);
                curColSet.add(value);
                curGroupSet.add(value);
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
        Set<Integer> curRowSet = rowMap.get(y);
        Set<Integer> curColSet = colMap.get(x);
        Set<Integer> curGroupSet = groupMap.get(group[y][x]);

        // 넣을 n 탐색
        for (int n = 1; n <= 9; n++) {
            if (curRowSet.contains(n) || curColSet.contains(n) || curGroupSet.contains(n)) continue;
            map[y][x] = n;
            curRowSet.add(n);
            curColSet.add(n);
            curGroupSet.add(n);
            if (depth == zeroCnt) return true;

            // 현재 행 탐색 후, 다음 행으로 이동
            boolean notFound = false;
            for (int j = x; j <= 9; j++) {
                if (map[y][j] != 0) continue;
                if (dfs(y, j, depth + 1)) return true;
                else {
                    // 못 찾았으면 현재 n 선택도 잘못됬으므로 백트래킹 -> 다음 후보 n 시도
                    notFound = true;
                    break;
                }
            }

            if (notFound) {
                map[y][x] = 0;
                curRowSet.remove(n);
                curColSet.remove(n);
                curGroupSet.remove(n);
                continue;
            }

            // 다음행은 맨 첫 열부터 탐색
            for (int i = y + 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
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
                curRowSet.remove(n);
                curColSet.remove(n);
                curGroupSet.remove(n);
            }
        }
        // 지금까지 숫자 9개 다 넣을 수 없으면 false -> 백트래킹 (직전 호출 메소드 n 선택도 잘못됨, 다른 n 시도)
        return false;
    }
}
