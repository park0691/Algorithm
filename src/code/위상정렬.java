package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 위상정렬 {
    static int v, e;
    static int[] indegree;
    static List<List<Integer>> adjList;

    public static void main(String[] args) {
        v = 7;
        e = 9;
        indegree = new int[v + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        // edges (v1 -> v2)
        int[] v1 = {1, 1, 2, 4, 3, 3, 5, 2, 5};
        int[] v2 = {2, 3, 5, 6, 4, 7, 6, 4, 4};

        /**
         * v1 -> v2 adjacent list
         * increment in_degree of v2
         */
        for (int i = 0; i < e; i++) {
            adjList.get(v1[i]).add(v2[i]);
            indegree[v2[i]]++;
        }

        topological_sort();
    }

    static void topological_sort() {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();

        // add node that's indegree is 0 to queue
        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.offer(node);

            for (Integer v : adjList.get(node)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        System.out.println(result);
    }
}
