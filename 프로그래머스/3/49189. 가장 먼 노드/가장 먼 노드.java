import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        
        List<Integer>[] adj = new ArrayList[n+1];
        
        for (int i = 1 ; i <= n ; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        boolean[] visited = new boolean[n+1];
        int prevCount = 0;
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{1, 0}); // 번호, 거리로 넣는다
        visited[1] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (prevCount == cur[1])
                count++;
            else {
                count = 1;
                prevCount = cur[1];
            }
            
            for (int next : adj[cur[0]]) {
                // 방문하지 않았으면 큐에 넣음
                if (visited[next])
                    continue;
                    
                q.add(new int[]{next, cur[1] + 1});
                visited[next] = true;
            }
        }
        return count;
    }
}