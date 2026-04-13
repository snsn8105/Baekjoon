import java.util.*;
class Solution {
    // d, l, r, u 순으로 반복
    // 순서대로 d, l, r, u
    int[] dx = {1, 0, 0, -1}; 
    int[] dy = {0, -1, 1, 0};
    char[] dChar = {'d', 'l', 'r', 'u'};
    String answer = "impossible";

    public void dfs(int n, int m, int x, int y, int r, int c, int k, int dist, StringBuilder sb) {
        // StringBuilder에 사전 순으로 추가하면서 재귀 진행
        // r,c에 도착하면 answer에 저장
        // 맨해튼 거리가 k - dist보다 크면 가지치기
        if (!answer.equals("impossible"))
            return;
        
        int remainDist = Math.abs(x - r) + Math.abs(y - c);
        
        if (remainDist > k - dist || (k - dist - remainDist) % 2 != 0 )
            return;
        
        if (dist == k && x == r && y == c) {
            answer = sb.toString();
            return;
        }
        
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (!isInMap(n, m, nx, ny))
                continue;
            
            sb.append(dChar[i]);
            dfs(n, m, nx, ny, r, c, k, dist+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    public boolean isInMap(int n, int m, int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m)
            return true;
        else
            return false;
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // n x m 미로에서 k 만큼 이동해서 도착지 r,c로 가야함
        // abcdefghijklmnop
        // d, l, r, u 순
        StringBuilder sb = new StringBuilder();
        
        dfs(n, m, x-1, y-1, r-1, c-1, k, 0, sb);
        return answer;
    }
}