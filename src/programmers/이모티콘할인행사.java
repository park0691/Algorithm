package programmers;

public class 이모티콘할인행사 {

    static int[] discountRatio = {10, 20, 30, 40};
    static int regPrice, regCnt;

    static public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] usersPayPrice = new int[users.length];;

        perm(0, users, emoticons, usersPayPrice);

        answer[0] = regCnt;
        answer[1] = regPrice;
        return answer;
    }

    static void perm(int tgtIdx, int[][] users, int[] emoticons, int[] usersPayPrice) {
        if (tgtIdx == emoticons.length) {
            int curRegCnt = 0;
            int curRegPrice = 0;
            // 사용자별 플러스 서비스 또는 이모티콘 구입 여부 판단
            for (int i = 0; i < users.length; i++) {
                // 이모티콘 구매 비용이 기준 가격 이상
                if (users[i][1] <= usersPayPrice[i]) {
                    // 이모티콘 플러스 서비스 가입
                    curRegCnt++;
                } else {
                    // 이모티콘 구입
                    curRegPrice += usersPayPrice[i];
                }
            }
            // 가입수, 매출액 업데이트
            if (regCnt < curRegCnt) {
                regCnt = curRegCnt;
                regPrice = curRegPrice;
            } else if (regCnt == curRegCnt) {
                if (regPrice < curRegPrice) {
                    regPrice = curRegPrice;
                }
            }

            return;
        }

        for (int srcIdx = 0; srcIdx < discountRatio.length; srcIdx++) {
            int curRatio = discountRatio[srcIdx];

            for (int i = 0; i < users.length; i++) {
                int userRatio = users[i][0];

                if (userRatio <= curRatio) {
                    usersPayPrice[i] += emoticons[tgtIdx] * (100 - curRatio) / 100;
                }
            }

            perm(tgtIdx + 1, users, emoticons, usersPayPrice);

            for (int i = 0; i < users.length; i++) {
                int userRatio = users[i][0];

                if (userRatio <= curRatio) {
                    usersPayPrice[i] -= emoticons[tgtIdx] * (100 - curRatio)  / 100;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        users = new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        emoticons = new int[]{1300, 1500, 1600, 4900};
        solution(users, emoticons);
    }
}