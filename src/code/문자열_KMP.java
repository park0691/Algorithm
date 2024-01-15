package code;

import java.util.Arrays;

public class 문자열_KMP {
    public static int[] getPI(String patternString) {
        char[] pattern = patternString.toCharArray();
        int[] pi = new int[pattern.length];  // 패턴 포인터를 어디로 움직여야 하는지 인덱스 저장
        // 접두사 인덱스 j
        // 접미사 인덱스 i <- for 문 안에서 계속 증가
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            // 일치하지 않으면 -> 일치했던 부분까지 되돌아가서 다시 검사 (접두사 인덱수 조정)
            while (j > 0 && pattern[j] != pattern[i]) j = pi[j - 1];
            if (pattern[i] == pattern[j]) pi[i] = ++j;
        }
        System.out.println(Arrays.toString(pi));
        return pi;
    }

    public static int kmp(String parentString, String patternString) {
        int[] pi = getPI(patternString);
        char[] parent = parentString.toCharArray();
        char[] pattern = patternString.toCharArray();
        int parentSize = parentString.length();
        int patternSize = patternString.length();
        int cnt = 0;
        // 텍스트 인덱스 i, 패턴 인덱스 j
        int j = 0;
        for (int i = 0; i < parentSize; i++) {
            while (j > 0 && parent[i] != pattern[j]) j = pi[j - 1];

            if (parent[i] == pattern[j]) {
                if (j == patternSize - 1) {
                    cnt++;
                    System.out.printf("%d번째에서 찾았습니다.\n", i - patternSize + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";
        System.out.println(kmp(parent, pattern));
    }

}
/*

pi[0] = 0
pi[1] = 0
...

 i=1				pi[1] = 0
A B A A B A B
  A B A A B A B
  j=0


    i=2				pi[2] = 1
A B A A B A B
    A B A A B A B
    j=0 >> j=1

    *
A B A
*

      i=3			while : j=pi[j-1]=pi[0]=0
A B A A B A B
    A B A A B A B
      j=1 >> j=0

      i=3			pi[3] = 1 (++j)
A B A A B A B
      A B A A B A B
      j=0 >> j=1

      *
A B A A
*

        i=4			pi[4] = 2 (++j)
A B A A B A B
      A B A A B A B
        j=1 >> j=2

      * *
A B A A B
* *

          i=5		pi[5] = 3 (++j)
A B A A B A B
      A B A A B A B
          j=2 >> j=3

      * * *
A B A A B A
* * *

            i=6		while : j=pi[j-1]=pi[2]=1
A B A A B A B
      A B A A B A B
            j=3 >> j=1

            i=6		pi[6] = 2 (++j)
A B A A B A B
          A B A A B A B
            j=1

*/