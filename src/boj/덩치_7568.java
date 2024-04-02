package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 덩치_7568 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Person[] person = new Person[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            person[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if (j == i) continue;
                if (person[i].weight < person[j].weight && person[i].height < person[j].height) {
                    rank++;
                }
            }
            System.out.print(rank + " ");
        }
        System.out.println();
    }

    static class Person {
        int height; int weight;

        public Person(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }
}
