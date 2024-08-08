package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_2042 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());   // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken());   // 구간합 계산 횟수
        long[] arr = new long[N];
        SegmentTree t = new SegmentTree();
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        // 세그먼트 트리 생성
        t.build(arr, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == 1) {
                // 수의 변경
                long value = Long.parseLong(st.nextToken());
                t.update(b, value - arr[b]);
                arr[b] = value;
            } else {
                // 구간합
                int c = Integer.parseInt(st.nextToken()) - 1;
                sb.append(t.query(b, c)).append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    static class SegmentTree {
        private static final int DEFAULT_VALUE = 0;
        private long[] tree;
        private long[] arr;

        public void build(long[] arr, int size) {
            tree = new long[size * 4];
            this.arr = arr;

            build(1, 0, arr.length - 1);
        }

        public long merge(long left, long right) {
            return left + right;
        }

        public long query(int start, int end) {
            return query(start, end, 1, 0, arr.length - 1);
        }

        public void update(int index, long newValue) {
            update(index, newValue, 1, 0, arr.length - 1);
        }

        private long build(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) {
                return tree[node] = arr[nodeLeft];
            }

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            long leftVal = build(node * 2, nodeLeft, mid);
            long rightVal = build(node * 2 + 1, mid + 1, nodeRight);
            return tree[node] = merge(leftVal, rightVal);
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight) {
            // 범위 밖
            if (right < nodeLeft || left > nodeRight) {
                return DEFAULT_VALUE;
            }

            if (left <= nodeLeft && nodeRight <= right) {
                return tree[node];
            }

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            return merge(
                    query(left, right, node * 2, nodeLeft, mid),
                    query(left, right, node * 2 + 1, mid + 1, nodeRight)
            );
        }

        private void update(int index, long newValue, int node, int nodeLeft, int nodeRight) {
            // 범위 밖
            if (index < nodeLeft || index > nodeRight) return;

            tree[node] += newValue;

            if (nodeLeft != nodeRight) {
                int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
                update(index, newValue, node * 2, nodeLeft, mid);
                update(index, newValue, node * 2 + 1, mid + 1, nodeRight);
            }
        }
    }
}