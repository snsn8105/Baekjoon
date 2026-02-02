import java.util.*;
class Solution {
    int[] dijkstra(int start, List<int[]>[] adj, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, 99999999);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        pq.add(new int[]{start, 0});
        dist[start] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curPoint = cur[0];
            int curDist = dist[curPoint];
            
            if (dist[curPoint] < curDist)
                continue;
            
            for (int[] edge : adj[curPoint]) {
                int nextPoint = edge[0];
                int nextDist = edge[1];
                
                if (dist[nextPoint] > curDist + nextDist) {
                    dist[nextPoint] = curDist + nextDist;
                    pq.add(new int[]{nextPoint, dist[nextPoint]});
                }
            }
        }
        
        return dist;
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 인접 리스트
        List<int[]>[] adj = new ArrayList[n+1];
        for (int i = 0 ; i <= n ; i++) {
            adj[i] = new ArrayList<>(); // [0]이 도착지, [1]이 비용
        }
        
        for (int i = 1 ; i <= n ; i++) {            
            for (int j = 0 ; j < fares.length ; j++) {
                if (fares[j][0] == i) {
                    adj[i].add(new int[]{fares[j][1], fares[j][2]});
                    adj[fares[j][1]].add(new int[]{fares[j][0], fares[j][2]}); // 반대 방향도 추가
                }
            }
        }
        
        int[] aDist = dijkstra(a, adj, n);
        int[] bDist = dijkstra(b, adj, n);
        int[] sDist = dijkstra(s, adj, n);
        
        // dist(s, i) + dist(i, a) + dist(i, b)가 최소인 경우 찾기
        int minDist = 99999999;
        
        for (int i = 1 ; i <= n ; i++) {
            int tmp = sDist[i] + aDist[i] + bDist[i];
            minDist = Math.min(tmp, minDist);
        }
        int answer = minDist;
        return answer;
    }
}