import java.util.*;
class Solution {
    private int bfs(int start, List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        q.add(start);
        visited[start] = true;
        
        int count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int a : adj[cur]) {
                if (!visited[a]) {
                    q.add(a);
                    visited[a] = true;
                    count++;
                }
            }
        }
        return count;
    }
    public int solution(int n, int[][] results) {
        List<int[]> fixed = new ArrayList<>();
        
        List<Integer>[] winAdj = new ArrayList[n+1];
        List<Integer>[] loseAdj = new ArrayList[n+1];
        for (int i = 1; i <= n ; i++) {
            winAdj[i] = new ArrayList<>();
            loseAdj[i] = new ArrayList<>();
        }
        // adj에 상대 저장
        for (int[] r : results) {
            winAdj[r[0]].add(r[1]);
            loseAdj[r[1]].add(r[0]);
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n ; i++) {
            if (bfs(i, winAdj) + bfs(i, loseAdj) == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
}