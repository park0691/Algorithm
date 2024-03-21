package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 도넛과막대그래프 {

    static int maxV = 0;
    static List<List<Integer>> adjList = new ArrayList<>();
    static int[] inDegrees;
    static int startV = 0;

    public static int[] solution(int[][] edges) {
        initGraph(edges);
        // 막대 (outDegree = 0, inDegree = 1) / 8자 (outDegree = 2, inDegree = 2)
        // 도넛 : 생성 정점의 outDegree - 막대 개수 - 8자 개수
        // 생성 정점 : inDegree = 0, outDegree >= 2

        int stickCount = 0, eightCount = 0;
        int totalCount = 0;

        // 생성 정점 및 막대 / 8자 그래프 갯수 카운트
        for (int i = 1; i <= maxV; i++) {
            if (inDegrees[i] == 0 && adjList.get(i).size() >= 2) {
                startV = i;
                totalCount = adjList.get(i).size();
            } else if (inDegrees[i] >= 1 && adjList.get(i).isEmpty()) {
                stickCount++;
            } else if (inDegrees[i] >= 2 && adjList.get(i).size() == 2) {
                eightCount++;
            }
        }

        int[] answer = {startV, totalCount - stickCount - eightCount, stickCount, eightCount};
        return answer;
    }

    public static void initGraph(int[][] edges) {
        for (int[] e : edges) {
            maxV = Math.max(maxV, Math.max(e[0], e[1]));
        }

        inDegrees = new int[maxV + 1];
        // 인접 리스트 초기화
        for (int i = 0; i <= maxV; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adjList.get(e[0]).add(e[1]);
            inDegrees[e[1]]++;
        }
    }

    public static void main(String[] args) {
        int[][] e1 = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        int[][] e2 = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};

//        System.out.println(Arrays.toString(solution(e1)));
        System.out.println(Arrays.toString(solution(e2)));
    }
}
