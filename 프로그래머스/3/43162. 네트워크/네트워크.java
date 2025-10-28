import java.util.*;

class Solution {
    boolean[] visited; 
    int n; // 컴퓨터 개수
    int[][] computers; // 연결 정보
    
    public void dfs(int node){
        visited[node] = true;
        
        for(int i = 0 ; i < computers.length ; i++){
            if(computers[node][i] == 1 && !visited[i])
                dfs(i);
        }
    }
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        this.visited = new boolean[n];
        int answer = 0;
        // 0번 컴퓨터부터 n-1번 컴퓨터까지 순회
        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 컴퓨터를 발견하면, 새로운 네트워크의 시작점임
            if (!visited[i]) {
                answer++; // 네트워크 개수 증가
                // 해당 네트워크에 속한 모든 노드를 DFS로 탐색하고 방문 처리
                dfs(i);
            }
        }
        
        return answer;
    }
}