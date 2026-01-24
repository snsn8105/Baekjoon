import java.util.*;
class Solution {
    public int[] solution(int target) {
        // [0] 최소 던진 횟수   [1] 최대 싱글, 불의 합
        int dp[][] = new int[target+1][2];
        for (int i = 1 ; i <= target ; i++) {
            dp[i][0] = 1000000; // 큰 수로 초기화
            dp[i][1] = 0;
        }
        List<int[]> allPossibleNums = new ArrayList<>();
        
        int[] singles = new int[20];
        for (int i = 1 ; i <= 20 ; i++) {
            singles[i-1] = i;
        }
        int[] doubles = new int[20];
        for (int i = 1 ; i <= 20 ; i++) {
            doubles[i-1] = i*2;
        }
        int[] triples = new int[20];
        for (int i = 1 ; i <= 20 ; i++) {
            triples[i-1] = i*3;
        }
        int[] bull = {50};
        allPossibleNums.add(singles);
        allPossibleNums.add(doubles);
        allPossibleNums.add(triples);
        allPossibleNums.add(bull);
        
        for (int i = 1 ; i <= target ; i++) {
            int idx = 0; // 0: single, 1: double, 2: triple, 3: bull
            for (int[] possibleNums : allPossibleNums) {
                int isSingleBull;
                if (idx == 0 || idx == 3)
                    isSingleBull = 1;
                else
                    isSingleBull = 0;
                
                for (int num : possibleNums) {
                    int prev = i - num;
                    if (prev < 0)
                        continue;
                    
                    int minTry = dp[prev][0] + 1;
                    int maxSum = dp[prev][1] + isSingleBull;
                    
                    if (minTry < dp[i][0]) {
                        dp[i][0] = minTry;
                        dp[i][1] = maxSum;
                    }
                    else if (minTry == dp[i][0] && maxSum > dp[i][1]) {
                        dp[i][1] = maxSum;
                    }
                }
                idx++;
            }
        }

        
        int[] answer = dp[target];
        return answer;
    }
}