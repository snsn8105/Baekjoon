import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer> profitMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            profitMap.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            String currentPerson = seller[i];
            int money = amount[i] * 100;

            // 추천인을 따라 올라가며 수익을 분배
            // currentPerson이 "-"가 아니고, 분배할 돈이 1원 이상일 때까지 반복
            while (!currentPerson.equals("-") && money >= 1) {
                int commission = money / 10; // 추천인에게 갈 10%
                int myProfit = money - commission; // 내가 가질 90%

                // 내 수익금 누적
                profitMap.put(currentPerson, profitMap.get(currentPerson) + myProfit);

                // 다음 분배를 위해 현재 인물을 추천인으로 변경하고,
                // 분배할 돈을 commission으로 설정
                currentPerson = parentMap.get(currentPerson);
                money = commission;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profitMap.get(enroll[i]);
        }

        return answer;
    }
}