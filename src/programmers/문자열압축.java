package programmers;

public class 문자열압축 {
    public static int solution(String s) {
        int answer = s.length();
        int size = s.length() / 2;

        for (int i = 2; i <= size; i++) {
            String subStr = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            int cnt = 1;
            for (int j = i; j <= s.length(); j += i) {
                String tmp = s.substring(j, j + i <= s.length() ? j + i : s.length());
                if (subStr.equals(tmp)) {
                    cnt++;
                } else {
                    if (cnt <= 1) {
                        sb.append(subStr);
                    } else {
                        sb.append(cnt);
                        sb.append(subStr);
                    }
                    subStr = tmp;
                    cnt = 1;
                }
            }
            // 마지막 tmp로 대체된 문자열 추가
            sb.append(subStr);
            answer = Math.min(answer, sb.toString().length());
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println(solution(s));
    }
}
