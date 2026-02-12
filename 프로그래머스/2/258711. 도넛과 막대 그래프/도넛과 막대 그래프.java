import java.util.*;
class Solution {
    int bfs(List<Integer>[] adj, boolean[] visited, int start) {
        // edge == node 개수 이면 도넛 모양
        // egde ==  node + 1이면 8자 모양, 그 외는 막대 모양
        int edge = 0;
        int node = 0;
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            node++;
            List<Integer> nexts = adj[cur];
            for (int n : nexts) {
                edge++;
                if (!visited[n]) {
                    q.add(n);
                    visited[n] = true;
                }
            }
        }
        if (edge == node)
            return 0; // 도넛
        if (edge == (node + 1))
            return 2; // 8자
        return 1; // 막대
    }
    public int[] solution(int[][] edges) {
        int max = 0;
        for (int[] e : edges) {
            if (max < e[0])
                max = e[0];
            if (max < e[1])
                max = e[1];
        }
        boolean[] visited = new boolean[max+1];
        List<Integer>[] adj = new ArrayList[max+1];
        for (int i = 0 ; i <= max ; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
        }
        
        int newNode = 0;
        int[] in = new int[max+1];
        int[] out = new int[max+1];
        
        for (int i = 1 ; i <= max ; i++) {
            for (int a : adj[i]) {
                in[a]++;
                out[i]++;
            }
        }
        
        for (int i = 1 ; i <= max ; i++) {
            if (in[i] == 0 && out[i] >= 2)
                newNode = i;
        }
        int[] answer = new int[4];
        answer[0] = newNode;
        int idx = 1;
        for (int n : adj[newNode]) {
            int result = bfs(adj, new boolean[max+1], n);
            if (result == 0)
                answer[1]++;
            if (result == 1)
                answer[2]++;
            if (result == 2)
                answer[3]++;
        }
        
        return answer;
    }
}