import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 1 base 좌표
        int[][] dp = new int[n+1][m+1];
        
        for (int[] p : puddles) {
            dp[p[1]][p[0]] = -1;
        }
        
        for (int i = 1 ; i <= m ; i++) {
            if (dp[1][i] != -1)   
                dp[1][i] = 1;
            else {
                for (int j = i ; j <=m ; j++) {
                    dp[1][j] = -1;   
                }
                break;
            }
        }
        
        for (int i = 1 ; i <= n ; i++) {
            if (dp[i][1] != -1)   
                dp[i][1] = 1;
            else {
                for (int j = i ; j <=m ; j++) {
                    dp[j][1] = -1;   
                }
                break;
            }
        }
        // 왼쪽, 위쪽 칸의 값을 더해서 현재 칸에 저장
        for (int i = 2 ; i <= n ; i++) {
            for (int j = 2 ; j <= m ; j++) {
                if (dp[i][j] == -1) {
                    continue;    
                }
                if (dp[i-1][j] != -1)
                    dp[i][j] += dp[i-1][j] % 1000000007;
                if (dp[i][j-1] != -1)
                    dp[i][j] += dp[i][j-1] % 1000000007;
            }
        }
        
        int answer = dp[n][m] % 1000000007;
        return answer;
    }
}