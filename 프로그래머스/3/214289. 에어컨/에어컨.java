import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int t11 = t1 + 10;
        int t22 = t2 + 10;
        // dp[i][j] 에 i분에 j도에 도달하는 최소 비용을 넣는다
        int[][] dp = new int[onboard.length][51];
        for (int i = 0 ; i < onboard.length ; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        int currentTmp = temperature + 10;
        dp[0][currentTmp] = 0;

        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j <= 50; j++) {
                // 1. 먼저 이번 칸(dp[i][j])을 채울 수 있는 후보들을 모을 변수
                int minValue = 1000000;

                // 후보 1: 에어컨을 껐을 때 (비용 0)
                if (j-1 < currentTmp && j-1 >= 0)
                    minValue = Math.min(minValue, dp[i-1][j-1]);
                if (j+1 > currentTmp && j+1 <= 50)
                    minValue = Math.min(minValue, dp[i-1][j+1]);
                if (j == currentTmp)
                    minValue = Math.min(minValue, dp[i-1][j]);

                // 후보 2: 에어컨을 켜서 온도를 변화시킬 때 (비용 a)
                if (i-1 >= 0 && j-1 >= 0)
                    minValue = Math.min(minValue, dp[i-1][j-1] + a);
                
                if (j+1 <= 50) 
                    minValue = Math.min(minValue, dp[i-1][j+1] + a);
                
                // 후보 3: 에어컨을 켜서 온도를 유지할 때 (비용 b)
                if (currentTmp != j) {
                    minValue = Math.min(minValue, dp[i-1][j] + b);
                }

                // 2. 위 후보들 중 최솟값을 dp[i][j]에 저장
                dp[i][j] = minValue;
                // 3. 승객 체크 (가장 중요!)
                if (onboard[i] == 1 && (j < t11 || j > t22)) {
                    dp[i][j] = 1000000; // 범위를 벗어나면 다시 무효화
                }
            }
        }
        int answer = 10000000;
        for(int j = 0 ; j <= 50 ; j++) {
            answer = Math.min(dp[onboard.length-1][j], answer);    
        }
        
        return answer;
    }
    
}