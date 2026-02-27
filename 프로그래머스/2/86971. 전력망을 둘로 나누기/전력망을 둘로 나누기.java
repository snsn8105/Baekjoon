import java.util.*;
class Solution {
    int dfs(int current, int parent, List<Integer>[] adj) {
        int count = 1;
        // 부모와 adj가 같으면 return
        for (int a : adj[current]) {
            if (a == parent) {
                continue;
            } else {
                count += dfs(a, current, adj);
            }
        }
        return count;
    }
    public int solution(int n, int[][] wires) {
        // 하나씩 다 잘라보고 나뉘어진 트리의 송전탑 개수의 차이를 계산
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1 ; i<= n ; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] w : wires) {
            // adj 초기화
            adj[w[0]].add(w[1]);
            adj[w[1]].add(w[0]);
        }
        
        int min = 999999;
        for (int[] w : wires) {
            adj[w[0]].remove(Integer.valueOf(w[1]));
            adj[w[1]].remove(Integer.valueOf(w[0]));
            // wires[0], [1]을 시작점으로 송전탑 개수를 계산
            int a = dfs( w[0], 0, adj);
            int b = dfs( w[1], 0, adj);
                
            adj[w[0]].add(w[1]);
            adj[w[1]].add(w[0]);
            
            min = Math.min(min, Math.abs(a-b));
        }
        int answer = min;
        return answer;
    }
}