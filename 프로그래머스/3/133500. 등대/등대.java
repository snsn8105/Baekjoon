import java.util.*;

class Solution {
    List<Integer>[] adj;
    int answer = 0;

    // 반환값: 해당 노드가 불을 켰는지 여부 (0: 안 켬, 1: 켬)
    int dfs(int cur, int parent) {
        int childState = 0; // 1이면 내가 불을 켜야함
        
        for (int a : adj[cur]) {
            if (a == parent)
                continue;
            if (dfs(a, cur) == 0) {
                childState = 1;
            }
        }
        
        if (childState == 1) {
            answer++;
            return childState;
        } else
            return childState;
    }

    public int solution(int n, int[][] lighthouse) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] l : lighthouse) {
            adj[l[0]].add(l[1]);
            adj[l[1]].add(l[0]);
        }

        // 1번 노드를 루트로 시작 (트리이므로 어디서 시작해도 무관)
        dfs(2, 0);

        return answer;
    }
}