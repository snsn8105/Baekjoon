import java.util.*;
class Solution {
    // 방향 배열: d, l, r, u 순서 (사전 순)
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] dChar = {'d', 'l', 'r', 'u'};
    String answer = "impossible";
    boolean found = false;
    
    void dfs(int n, int m, int x, int y, int r, int c, int k, int depth, StringBuilder path) {
        // 1. [중요] 이미 정답을 찾았다면 더 이상 탐색하지 않음 (이 로직이 추가되어야 함)
        if (found) return;

        // 2. 가지치기 (Pruning): 현재 위치에서 목적지까지 남은 거리 계산
        int dist = Math.abs(x - r) + Math.abs(y - c);

        // 남은 이동 횟수(k - depth)로 절대 목적지에 못 가거나, 
        // 남은 횟수와 거리의 홀짝성이 맞지 않으면 즉시 종료
        if (dist > (k - depth) || (k - depth - dist) % 2 != 0) {
            return;
        }

        // 3. 목적지 도착 및 이동 횟수 충족
        if (depth == k) {
            if (x == r && y == c) {
                answer = path.toString();
                found = true; // 사전 순 가장 빠른 답을 찾았으므로 플래그 세움
            }
            return;
        }

        // 4. 4방향 탐색 (d, l, r, u 순서)
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 격자 범위 내에 있는지 확인
            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                path.append(dChar[i]);
                dfs(n, m, nx, ny, r, c, k, depth + 1, path);
                path.deleteCharAt(path.length() - 1); // 백트래킹: 마지막 문자 제거
            }
        }
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        
        dfs(n, m, x, y, r, c, k, 0, sb);
        
        return answer;
    }
}