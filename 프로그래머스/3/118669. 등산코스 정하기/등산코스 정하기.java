import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 1. 인접 리스트 초기화 (Matrix 대신 List 사용!)
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];
        for (int g : gates) isGate[g] = true;
        for (int s : summits) isSummit[s] = true;

        // 2. 그래프 구성 (편하게 양방향으로 넣으세요!)
        for (int[] p : paths) {
            int u = p[0], v = p[1], w = p[2];
            // 출발지와 목적지 조건에 맞춰 단방향/양방향 결정 (선택사항)
            if (isGate[u] || isSummit[v]) {
                adj[u].add(new int[]{v, w});
            } else if (isGate[v] || isSummit[u]) {
                adj[v].add(new int[]{u, w});
            } else {
                adj[u].add(new int[]{v, w});
                adj[v].add(new int[]{u, w});
            }
        }

        // 3. 다익스트라 준비
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }
        
        // 다익스트라 실행
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int weight = cur[1];
            
            if (weight > intensity[u])
                continue;
            if (isSummit[cur[0]])
                continue;
            
            for (int[] path : adj[u]) {
                int v = path[0];
                int w = path[1];
                int maxW = Math.max(w, intensity[u]);
                
                if (intensity[v] > maxW) {
                    intensity[v] = maxW;
                    pq.add(new int[]{v, maxW});
                }
            }
        }
        
        // 결과 추출
        int minIndex = n+1;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);
        
        for (int s : summits) {
            if (minIntensity > intensity[s])
                minIndex = s;
            minIntensity = Math.min(minIntensity, intensity[s]);
            
        }
        
        int[] answer = {minIndex, minIntensity};
        return answer;
    }
}