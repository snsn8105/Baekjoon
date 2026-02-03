import java.util.*;
class Solution {
    // 열만 뒤집는 것 중 최대 2^10 개 종류의 뒤집는 경우 찾기
    int answer = Integer.MAX_VALUE;
    int n;
    int m;
    int[][] map;
    int[][] target;
    void dfs(int r, int count) {
        if (r == n) {
            int totalFlips = count;   
            
            for (int j = 0 ; j < m ; j++) {
                int diff = 0;
                for (int i = 0 ; i < n ; i++) {
                    if (map[i][j] != target[i][j])
                        diff++;
                }
                if (diff == n)
                    totalFlips++;
                else if (diff != 0)
                    return;
            }
            
            answer = Math.min(answer, totalFlips);
            return;
        }
        
        // 뒤집지 않고 다음 단계
        dfs(r+1, count);
        
        // 뒤집고 다음 단계
        flip(r);
        dfs(r+1, count+1);
        
        // 원상 복구 (백트래킹)
        flip(r);
        
        return;
    }
    void flip(int r) {
        for (int i = 0 ; i < map[0].length ; i++)
            map[r][i] = 1 - map[r][i];
    }
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        map = new int[n][m];
        this.target = target;
        
        int idx = 0;
        for (int[] b : beginning) {
            map[idx] = Arrays.copyOf(b, b.length);
            idx++;
        }
        
        dfs(0,0);
        if (answer == Integer.MAX_VALUE)
            return -1;
        return answer;
    }
}