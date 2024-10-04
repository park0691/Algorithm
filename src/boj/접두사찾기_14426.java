package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEnd;

    TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isEnd = false;
    }
}

public class 접두사찾기_14426 {

    static TrieNode root = new TrieNode();

    static void insert(String word) {
        TrieNode curNode = root;
        for (char c : word.toCharArray()) {
            curNode.children.putIfAbsent(c, new TrieNode());
            curNode = curNode.children.get(c);
        }
        curNode.isEnd = true;
    }

    static boolean searchPrefix(String prefix) {
        TrieNode curNode = root;
        for (char c : prefix.toCharArray()) {
            if (!curNode.children.containsKey(c)) {
                return false;
            }
            curNode = curNode.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (searchPrefix(s)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
