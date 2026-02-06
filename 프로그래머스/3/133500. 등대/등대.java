import java.util.*;

class Solution {
    List<Integer>[] adj;
    int answer = 0;

    // 반환값: 해당 노드가 불을 켰는지 여부 (0: 안 켬, 1: 켬)
    int dfs(int cur, int parent) {
        // 자식 노드가 하나도 없는 리프 노드라면 불을 켜지 않고 0 반환
        if (adj[cur].size() == 1 && adj[cur].get(0) == parent) {
            return 0;
        }

        int childState = 0;
        for (int next : adj[cur]) {
            if (next == parent) continue;
            // 자식들의 상태를 확인
            // 자식 중 하나라도 0(불 안 켬)을 반환하면, 현재 노드(부모)는 불을 켜야 함
            if (dfs(next, cur) == 0) {
                childState = 1;
            }
        }

        // 자식 중 불 안 켠 애가 있어서 내가 켜야 하는 경우
        if (childState == 1) {
            answer++;
            return 1; // 내가 불을 켰음을 부모에게 알림
        }

        return 0; // 나는 불을 켜지 않았음
    }

    public int solution(int n, int[][] lighthouse) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] l : lighthouse) {
            adj[l[0]].add(l[1]);
            adj[l[1]].add(l[0]);
        }

        // 1번 노드를 루트로 시작 (트리이므로 어디서 시작해도 무관)
        dfs(1, 0);

        return answer;
    }
}