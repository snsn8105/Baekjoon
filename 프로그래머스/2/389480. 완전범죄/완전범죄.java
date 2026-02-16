import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[m]; //  sumB마다 sumA의 최소값을 저장
        
        Arrays.fill(dp, 99999);
        dp[0] = 0;
        
        for (int[] i : info) {
            int costA = i[0];
            int costB = i[1];
            
            int[] newDp = new int[m];
            Arrays.fill(newDp,99999);
            
            for (int j = 0 ; j < m ; j++) {
                if (dp[j] == 99999)
                    continue;
                
                if (costA + dp[j] < n) {
                    newDp[j] = Math.min(newDp[j], costA + dp[j]);
                }
                if (costB + j < m) {
                    newDp[j+costB] = Math.min(newDp[j+costB], dp[j]);
                }
            }
            dp = newDp;
        }

        int answer = 99999;
        for (int i : dp) {
            answer = Math.min(answer, i);
        }
        if (answer == 99999)
            return -1;
        return answer;
    }
}