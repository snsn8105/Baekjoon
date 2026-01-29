import java.util.*;
class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        // 1. 노드 최댓값 찾기 (배열 크기 결정용)
        int maxNode = 0;
        for (int node : nodes) maxNode = Math.max(maxNode, node);

        // 2. 인접 리스트 및 차수 계산
        List<Integer>[] adj = new ArrayList[maxNode + 1];
        for (int i = 0; i <= maxNode; i++) 
            adj[i] = new ArrayList<>();
        
        int[] degrees = new int[maxNode + 1];
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        boolean[] visited = new boolean[maxNode + 1];
        int[] answer = {0,0}; // 0번이 홀짝

        // bfs
        for (int node : nodes) {
            if (visited[node])
                continue;
            // 같은 tree의 노드들을 한번에 홀짝 노드인지 역홀짝 노드인지 확인
            Queue<Integer> q = new LinkedList<>();
            q.add(node);
            
            int oddEvenNodeCount = 0;
            int reverseNodeCount = 0;
            while (!q.isEmpty()) {
                int n = q.poll();
                visited[n] = true;
                
                if (n % 2 == 0 && degrees[n] % 2 == 0)
                    oddEvenNodeCount++;
                else if (n % 2 == 1 && degrees[n] % 2 == 1)
                    oddEvenNodeCount++;
                else
                    reverseNodeCount++;
                
                for (int i : adj[n]) {
                    if (!visited[i]){
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
            if (oddEvenNodeCount == 1) {
                answer[0]++;
            }
            if (reverseNodeCount == 1)
                answer[1]++;
        }
        
        return answer;
    }
}