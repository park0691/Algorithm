package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Next Permutation (NP)
public class 치킨배달_15686_2 {
    static int N, M, min;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> shops = new ArrayList<>();
    static int index[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 2) shops.add(new int[] { i, j });
                else if (n == 1) houses.add(new int[] { i, j });
            }
        }
        min = Integer.MAX_VALUE;

        // 111 뒤에서 index 채운다.
        index = new int[shops.size()];

        for (int i = 0; i < M; i++) {
            index[index.length - 1 - i] = 1;
        }

        while (true) {
            // 위 index 배열은 순열이 완성 => 조합에 사용될 0과 1이 만들어졌다.
            // => 1인 것만 선택된다.
            int sum = 0;
            // 집 각각에 대해서
            for (int i = 0; i < houses.size(); i++) {
                // 해당 집의 치킨거리 구하기
                int dist = Integer.MAX_VALUE;
                for (int j = 0; j < index.length; j++) {
                    if (index[j] == 0) continue;
                    dist = Math.min(dist,
                            Math.abs(houses.get(i)[0] - shops.get(j)[0]) +
                                    Math.abs(houses.get(i)[1] - shops.get(j)[1])
                    );
                }
                sum += dist;
            }
            min = Math.min(min, sum);
            if ( !np() ) break;
        }

        System.out.println(min);
    }

    private static boolean np() {
        int[] src = index;

        int i = src.length - 1;
        while (i > 0 && src[i - 1] >= src[i]) --i;

        // 기저 조건 : 현재 배열의 구성이 가장 큰 수 (마지막)
        if (i == 0) return false;

        int j = src.length - 1;
        while (src[i - 1] >= src[j]) --j;
        swap(src, i - 1, j);

        int k = src.length - 1;
        while (i < k) swap(src, i++, k--);

        return true;
    }

    private static void swap(int[] src, int i, int j) {
        int temp = src[i];
        src[i] = src[j];
        src[j] = temp;
    }
}