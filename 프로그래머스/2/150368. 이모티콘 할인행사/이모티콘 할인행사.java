import java.util.*;
class Solution {
    int maxSubs = 0;
    int maxSell = 0;
    int[] rates = {10, 20, 30, 40};
    
    // 각각 10000원이 넘는 최소 인덱스를 저장
    void select(int[] selectedRates, int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            calc(selectedRates, users, emoticons);
            return;
        }
        // {10, 10, 10, 10} ~ {40,40,40,40} 까지 모두 탐색 
        for (int i = 0 ; i < 4 ; i++) {
            selectedRates[depth] = rates[i];
            select(selectedRates, depth+1, users, emoticons);
        }
    }
    void calc(int[] selectedRates, int[][] users, int[] emoticons) {
        // 해당 할인율에 대해 구독한 사람 수와 판매액을 계산
        int subs = 0;
        int sell = 0;
        
        for (int[] u : users) {
            int rate = u[0];
            int subPrice = u[1];
            int price = 0;
            for (int i = 0 ; i < emoticons.length ; i++) {
                // 할인율이 u의 기준보다 높으면 구매
                if (selectedRates[i] >= rate) {
                    price += (100 - selectedRates[i]) * emoticons[i] / 100; 
                }
            }
            if (price >= subPrice) {
                subs++;
            } else {
                sell += price;
            }
        }
        // maxSubs와 maxSell과 비교
        if (subs > maxSubs) {
            maxSubs = subs;
            maxSell = sell;
            return;
        } else if (subs == maxSubs && maxSell < sell) {
            maxSell = sell;
            return;
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        int[] selectedRates = new int[emoticons.length];
        
        select(selectedRates, 0, users, emoticons);
               
        return new int[]{maxSubs, maxSell};
    }
}