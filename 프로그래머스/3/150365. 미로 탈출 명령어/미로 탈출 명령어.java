import java.util.*;
class Solution {
    int[][] map;
    String answer = "impossible";
    void dfs(StringBuilder sb, int x, int y, int r, int c, int k) {
        // base : count == k 이면 종료
        if (k == sb.length()) {
            if (x == r && y == c) {
                if (answer.equals("impossible"))
                    answer = sb.toString();
                return;
            }
            else
                return;
        }
        // 가지치기 -> k - sb.length() 보다 x-r + y-c가 더 크면 종료
        int remain = k - sb.length();
        int distance = Math.abs(x-r) + Math.abs(y-c);
        
        if (remain < distance || (remain - distance) % 2 != 0)
            return;
        
        // 사전 순으로 d->l->r->u 순으로 배열 생성
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        
        for (int i = 0 ; i < 4 ; i++) {
            if (!inMap(x+dx[i], y+dy[i])) {
                continue;
            }
            if (!answer.equals("impossible"))
                return;
            
            if (i == 0) {
                sb.append('d');
                dfs(sb, x+dx[i], y+dy[i], r, c, k);
                sb.deleteCharAt(sb.length()-1);
            } else if (i == 1) {
                sb.append('l');
                dfs(sb, x+dx[i], y+dy[i], r, c, k);
                sb.deleteCharAt(sb.length()-1);
            } else if (i == 2) {
                sb.append('r');
                dfs(sb, x+dx[i], y+dy[i], r, c, k);
                sb.deleteCharAt(sb.length()-1);
            } else if (i == 3) {
                sb.append('u');
                dfs(sb, x+dx[i], y+dy[i], r, c, k);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    } 
    boolean inMap(int x, int y) {
        int n = map.length;
        int m = map[0].length;
        
        if (x >= 1 && x < n && y >= 1 && y < m)
            return true;
        else
            return false;
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        map = new int[n+1][m+1]; //[x][y]가 출발 위치, [r][c]가 도착지
        StringBuilder sb = new StringBuilder();
        
        dfs(sb, x, y, r, c, k);
        
        return answer;
    }
}