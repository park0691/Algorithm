package programmers;

public class 아날로그시계_PCCP기출_3 {

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        int count = 0;

        if (startTime == 0 || startTime == 12 * 3600) {
            count++;
        }

        double hAngle, mAngle, sAngle, hNextAngle, mNextAngle, sNextAngle;

        while (startTime < endTime) {
            // 시침 1H = 30도 : 1S = 30/3600도 (1/120도)
            // 분침 1M = 6도 : 1S = 6/60도 (1/10도)
            // 초침 1S = 6도
            hAngle = startTime / 120d % 360;
            mAngle = startTime / 10d % 360;
            sAngle = startTime * 6d % 360;

            hNextAngle = (startTime + 1d) / 120 % 360 == 0 ? 360 : (startTime + 1d) / 120 % 360;
            mNextAngle = (startTime + 1d) / 10 % 360 == 0 ? 360 : (startTime + 1d) / 10 % 360;
            sNextAngle = (startTime + 1d) * 6 % 360 == 0 ? 360 : (startTime + 1d) * 6 % 360;

            if (sAngle < hAngle && sNextAngle >= hNextAngle) {
                count++;
            }
            if (sAngle < mAngle && sNextAngle >= mNextAngle) {
                count++;
            }
            if (sNextAngle == hNextAngle && hNextAngle == mNextAngle) {
                count--;
            }
            startTime++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(0,6,1,0,6,6));
    }
}
