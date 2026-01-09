import java.util.*;

class Solution {
    boolean[] visited;
    ArrayList<String> allPaths = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        // 1. 모든 경로를 찾기 위해 DFS 시작
        dfs("ICN", "ICN", tickets, 0);
        
        // 2. 만들어진 모든 경로 중 사전순으로 가장 앞선 것을 선택
        Collections.sort(allPaths);
        
        return allPaths.get(0).split(" ");
    }

    void dfs(String current, String path, String[][] tickets, int count) {
        // 모든 티켓을 다 썼을 때
        if (count == tickets.length) {
            allPaths.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 아직 안 쓴 티켓이고, 출발지가 현재 위치와 같다면
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false; // 백트래킹 (다시 되돌려놓음)
            }
        }
    }
}