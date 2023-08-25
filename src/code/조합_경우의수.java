package code;

public class 조합_경우의수 {
    public static int comb(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }
        return comb(n - 1, r - 1) + comb(n - 1, r);
    }
}
