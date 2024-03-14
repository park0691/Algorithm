package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 전화번호목록_5052 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> str = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int T = Integer.parseInt(br.readLine());

        loop: for (int tc = 1; tc <= T; tc++) {
            str.clear();
            set.clear();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                str.add(br.readLine());
            }
            // 길이가 긴 것부터 정렬
            Collections.sort(str, (s1, s2) -> s2.length() - s1.length());

            for (String s : str) {
                if (!set.isEmpty()) {
                    if (set.contains(s)) {
                        System.out.println("NO");
                        continue loop;
                    }
                }
                // 접두사만 체크. 맨 마지막 글자는 체크할 필요 없어
                for (int i = 1; i < s.length(); i++) {
                    set.add(s.substring(0, i));
                }
            }
            System.out.println("YES");
        }
    }
}