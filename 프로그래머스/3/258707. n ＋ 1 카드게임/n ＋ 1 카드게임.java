import java.util.*;

class Solution {
    private int n;
    private int target;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        target = n + 1;
        int idx = n / 3;
        
        Set<Integer> hand = new HashSet<>();
        Set<Integer> candidate = new HashSet<>();

        // 1. 초기 n/3장의 카드를 손에 넣음
        for (int i = 0; i < idx; i++) {
            hand.add(cards[i]);
        }

        int round = 1;
        while (idx < n) {
            // 2. 매 라운드 카드 2장을 후보군에 추가
            candidate.add(cards[idx++]);
            candidate.add(cards[idx++]);

            // 3. 우선순위에 따라 라운드 연장 시도
            if (canMakePair(hand, hand, 0)) {
                // 코인 0개 소모 (이미 손에 있는 것끼리)
            } else if (coin >= 1 && canMakePair(hand, candidate, 1)) {
                // 코인 1개 소모 (손 + 후보군)
                coin -= 1;
            } else if (coin >= 2 && canMakePair(candidate, candidate, 2)) {
                // 코인 2개 소모 (후보군끼리)
                coin -= 2;
            } else {
                // 어떤 조합으로도 합이 n+1을 못 만들면 게임 종료
                break;
            }
            round++;
        }

        return round;
    }

    // 두 집합 사이에서 합이 target(n+1)이 되는 쌍을 찾아 제거하는 함수
    private boolean canMakePair(Set<Integer> set1, Set<Integer> set2, int mode) {
        for (int card : set1) {
            int partner = target - card;
            if (set2.contains(partner)) {
                set1.remove(card);
                set2.remove(partner);
                return true;
            }
        }
        return false;
    }
}