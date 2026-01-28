import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 1. 능력치에 도달하기까지의 최소 시간을 dp로 추적
        int maxA = 0;
        int maxC = 0;
        for (int[] p : problems) {
            maxA = Math.max(maxA, p[0]);
            maxC = Math.max(maxC, p[1]);
        }
        alp = Math.min(maxA, alp);
        cop = Math.min(maxC, cop);
        
        int dp[][] = new int[maxA+1][maxC+1];
        for (int[] d : dp) {
            Arrays.fill(d, 1000000);
        }
        
        int a = alp;
        int c = cop;
        dp[a][c] = 0;
        for (int i = a ; i <= maxA ; i++) {
            for (int j = c ; j <= maxC ; j++) {
                // [i+1][j+1] 전까지는 모두 +1 처리
                // 1. 알고력 공부
                if (i+1 <= maxA) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                // 2. 코딩력 공부
                if (j+1 <= maxC)
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                                
                // 풀 수 있는 문제가 있으면 풀어서 결과 저장
                for (int[] p : problems) {
                    if (p[0] <= i && p[1] <= j) {
                        int newA = Math.min(maxA, i + p[2]);
                        int newC = Math.min(maxC, j + p[3]);
                        
                        dp[newA][newC] = Math.min(dp[newA][newC], dp[i][j] + p[4]);
                    }
                    
                    
                }
            }
        }
        
        int answer = dp[maxA][maxC];
        return answer;
    }
}