import java.util.*;
class Solution {
    public long solution(int[] a, int[][] edges) {
        // 리프에서부터 가중치를 부모노드에 넘기면서 모든 노드를 순회
        int[] degrees = new int[a.length];
        long[] longA = new long[a.length];
        
        List<Integer>[] adj = new ArrayList[a.length];
        
        for (int i = 0 ; i < a.length ; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int [] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
            degrees[e[0]]++;
            degrees[e[1]]++;
        }
                
        long sum = 0;
        for (int i = 0 ; i < a.length ; i++) {
            sum += a[i];
            longA[i] = a[i];
        }
        
        if (sum != 0)
            return -1;
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[a.length];
        
        // 리프 노드만 큐에 추가
        for (int i = 0 ; i < a.length ; i++) {
            if (degrees[i] == 1) {
                q.add(i);
            }
        }

        long answer = 0;
        
        while (!q.isEmpty()) {
            // 돌면서 부모의 가중치 longA[]에 값을 넘기고 answer에 그 값의 절댓값을 더해줌
            int cur = q.poll();
            visited[cur] = true;
            
            for (int neighbor : adj[cur]) {
                if (!visited[neighbor]) {
                    answer += Math.abs(longA[cur]);
                    longA[neighbor] += longA[cur];
                    
                    degrees[neighbor]--;
                    if (degrees[neighbor] == 1)
                        q.add(neighbor);
                }
            }
        }
        
        return answer;
    }
}