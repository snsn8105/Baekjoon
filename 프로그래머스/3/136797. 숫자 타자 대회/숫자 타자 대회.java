import java.util.*;
class Solution {
    // 1. 각 숫자의 좌표를 미리 정의
    static int[][] pos = {
        {3, 1}, // 0
        {0, 0}, {0, 1}, {0, 2}, // 1, 2, 3
        {1, 0}, {1, 1}, {1, 2}, // 4, 5, 6
        {2, 0}, {2, 1}, {2, 2}  // 7, 8, 9
    };
    
    int calcDistance(int p1, int p2) {
        int r1 = pos[p1][1];
        int c1 = pos[p1][0];
        
        int r2 = pos[p2][1];
        int c2 = pos[p2][0];
            
        // 2. 두 좌표 (r1, c1), (r2, c2) 사이의 거리 계산 로직
        int dr = Math.abs(r1 - r2);
        int dc = Math.abs(c1 - c2);

        // 대각선으로 최대한 이동하고 남은 거리를 상하좌우로 이동
        int weight = Math.min(dr, dc) * 3 + Math.abs(dr - dc) * 2;
        if (dr == 0 && dc == 0) 
            weight = 1; // 제자리 예외처리
        
        return weight;
    }
    public int solution(String numbers) {
        int[][] weights = new int[10][10];

        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                weights[i][j] = calcDistance(i, j);
            }
            
        }
        
        int[][][] dp = new int[2][10][10]; // i-1, i번째에서 L R의 위치 별 최소 비용 저장
        for (int l = 0 ; l<2 ; l++) {
            for (int i = 0 ; i < 10 ; i++) {
                for (int j = 0 ; j < 10 ;j++) {
                    dp[l][i][j] = 999999;
                }
            }
        }
        int left = 4;
        int right = 6;
        dp[0][left][right] = 0;
        for (int i = 0 ; i < numbers.length() ; i++) {
            int num = numbers.charAt(i) - '0';
            int now = i % 2;
            int next = (i+1) % 2;
            
            for (int j = 0 ; j < 10 ; j++) {
                Arrays.fill(dp[next][j], 999999);
            }
            for (int L = 0 ; L < 10 ; L++) {
                for (int R = 0 ; R < 10 ; R++) {
                    if (dp[now][L][R] == 999999)
                        continue;
                    
                    // 왼손 이동
                    if (num != R) 
                        dp[next][num][R] = Math.min(dp[now][L][R] + weights[num][L], dp[next][num][R]);
                    
                    // 오른손 이동
                    if (num != L)
                        dp[next][L][num] = Math.min(dp[now][L][R] + weights[num][R], dp[next][L][num]);
                }
            }
        }
        int finalIdx = numbers.length() % 2;
        int min = 999999;
        for (int L = 0 ; L < 10 ; L++) {
            for (int R = 0 ; R < 10 ; R++) {
                min = Math.min(dp[finalIdx][L][R], min);
            }
        }
       
        
        int answer = min;
        return answer;
    }
}