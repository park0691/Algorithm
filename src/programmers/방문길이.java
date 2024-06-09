package programmers;

import java.util.*;

public class 방문길이 {
    public int solution(String dirs) {
        Map<Character, int[]> map = Map.of(
                'U', new int[] {1, 0}, 'D', new int[] {-1, 0},
                'R', new int[] {0, 1}, 'L', new int[] {0, -1});
        Set<Pos> set = new HashSet<>();
        int cy = 0, cx = 0;
        char[] dirChars = dirs.toCharArray();
        for (char d : dirChars) {
            int[] dir = map.get(d);

            int ny = cy + dir[0];
            int nx = cx + dir[1];

            if (ny < -5 || nx < -5 || ny > 5 || nx > 5) continue;

            set.add(new Pos(cy, cx, ny, nx));
            cy = ny;
            cx = nx;
        }

        return set.size();
    }

    static class Pos {
        int y, x, ny, nx;
        Pos(int y, int x, int ny, int nx) {
            this.y = y;
            this.x = x;
            this.ny = ny;
            this.nx = nx;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pos)) return false;
            Pos tmp = (Pos) other;
            return (tmp.y == this.y && tmp.x == this.x && tmp.ny == this.ny && tmp.nx == this.nx) || (tmp.y == this.ny && tmp.x == this.nx && tmp.ny == this.y && tmp.nx == this.x);
        }
        @Override
        public int hashCode() {
            // equals() 값이 true일 경우 hashCode() 값도 true로 반환되어야 같은 인스턴스로 취급
            // equals() 값이 false일 경우 hashCode() 실행 X -> true일 경우에 같은 hash 반환되도록 해시 생성
            // return y + x + ny + nx;
            return Objects.hash(y, x) + Objects.hash(ny, nx);
        }
    }
}
