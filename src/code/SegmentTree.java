package code;

import java.util.Arrays;

public class SegmentTree {
    private static final int DEFAULT_VALUE = 0;
    private int[] tree;
    private int[] arr;

    public void build(int[] arr, int size) {
        tree = new int[size * 4];
        this.arr = arr;

        build(1, 0, arr.length - 1);
    }

    public int merge(int left, int right) {
        return left + right;
    }

    public int query(int start, int end) {
        return query(start, end, 1, 0, arr.length - 1);
    }

    public void update(int index, int newValue) {
        update(index, newValue, 1, 0, arr.length - 1);
    }

    public void update(int left, int right, int newValue) {
        update(left, right, newValue, 1, 0, arr.length - 1);
    }

    private int build(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            return tree[node] = arr[nodeLeft];
        }

        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        int leftVal = build(node * 2, nodeLeft, mid);
        int rightVal = build(node * 2 + 1, mid + 1, nodeRight);
        return tree[node] = merge(leftVal, rightVal);
    }

    private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
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

    private void update(int index, int newValue, int node, int nodeLeft, int nodeRight) {
        // 범위 밖
        if (index < nodeLeft || index > nodeRight) return;

        tree[node] += newValue;

        if (nodeLeft != nodeRight) {
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            update(index, newValue, node * 2, nodeLeft, mid);
            update(index, newValue, node * 2 + 1, mid + 1, nodeRight);
        }
    }

    private void update(int left, int right, int newValue, int node, int nodeLeft, int nodeRight) {
        // 범위 밖
        if (right < nodeLeft || left > nodeRight) return;

        if (nodeLeft == nodeRight) {
            tree[node] += newValue;
            return;
        }

        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        update(left, right, newValue, node * 2, nodeLeft, mid);
        update(left, right, newValue, node * 2 + 1, mid + 1, nodeRight);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public static void main(String[] args) {
        SegmentTree t = new SegmentTree();
        t.build(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println(Arrays.toString(t.tree));
        System.out.println(t.query(2, 4));
    }
}
