import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 인접 리스트? 이거도 다익스트라인듯
        // 1. 인접 리스트에 경로들 정리 
        List<int[]>[] adj = new ArrayList[n+1]; // [0]에 도착점, [1]에 누적 거리
        for (int i = 0 ; i <=n ; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] r : roads) {
            int a = r[0];
            int b = r[1];
            
            adj[a].add(new int[]{b,0});
            adj[b].add(new int[]{a,0});
        }
        Queue<int[]> p = new LinkedList<>();
        int[] distances = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(distances, -1);
        // 2. destination에서 다른 모든 점으로 갈 수 있는지 확인
        // pq가 비었거나 destination이면 종료
        int[] answer = new int[sources.length];
       
        p.add(new int[]{destination, 0});
        
        while (!p.isEmpty()) {
            int[] cur = p.poll();
            int from = cur[0]; // 현재 위치
            int distance = cur[1]; // 현재 누적 거리
            
            if (!visited[from]) {
                distances[from] = distance;
                visited[from] = true;
            }
            // 아니면 인접 리스트에서 경로 꺼내서 큐에 삽입
            // 직전에 왔던 길은 빼기?
            for (int[] path : adj[from]) {
                if (!visited[path[0]])
                    p.add(new int[]{path[0], distance+1});
            }
        }
        
        for (int i = 0; i < answer.length ; i++) {
            answer[i] = distances[sources[i]];
        }
        
        return answer;
    }
}