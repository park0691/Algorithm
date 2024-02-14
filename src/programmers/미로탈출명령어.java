package programmers;

public class 미로탈출명령어 {

    // Greedy Solution
    // 이동한 위치에서 목적지까지 최단 경로(d, temp_d)가 남은 이동 횟수(k)보다 크면 이동 불가능
    static public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int[] dy = {0, -1, 1, 0};
        int[] dx = {1, 0, 0, -1};
        char[] dir = {'d', 'l', 'r', 'u'};      // 사전 순으로 빠른 탈출 경로
        int d = Math.abs(x - r) + Math.abs(y - c);

        if (d > k || (k - d) % 2 != 0) return "impossible";

        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
                int temp_d = Math.abs(nx - r) + Math.abs(ny - c);
                if (temp_d > k) continue;

                x = nx; y = ny;
                sb.append(dir[i]);
                break;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(3,4,2,3,3,1,5) + " == dllrl");
        System.out.println(solution(2,2,1,1,2,2,2) + " == dr");
        System.out.println(solution(3,3,1,2,3,3,4) + " == impossible");
    }
}
