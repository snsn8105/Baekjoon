import java.util.*;

class Solution {
    public void dfs(int depth, boolean[] visited, List<Integer>[] adj, Set<String> resultSet) {
        if (depth == adj.length) {
            StringBuilder sb = new StringBuilder();
            // 방문한 순서가 아니라, 인덱스 번호 순서(0번 유저부터)대로 문자열 생성
            // 이 로직 덕분에 "ryan, frodo"와 "frodo, ryan"은 모두 "01"이 되어 중복 제거
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) sb.append(i);
            }
            resultSet.add(sb.toString());
            return;
        }

        for (int userIdx : adj[depth]) {
            if (!visited[userIdx]) { 
                visited[userIdx] = true;
                dfs(depth + 1, visited, adj, resultSet);
                visited[userIdx] = false; // 백트래킹
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        List<Integer>[] adj = new ArrayList[banned_id.length];
        for (int i = 0 ; i < banned_id.length ; i++) {
            adj[i] = new ArrayList<>();
        }

        int idxBI = 0;
        for (String bi : banned_id) {
            int idxUI = 0;
            for (String ui : user_id) {
                if (bi.length() == ui.length()) {
                    boolean[] check = new boolean[bi.length()];
                    
                    // 와일드카드 처리
                    for (int i = 0 ; i < bi.length() ; i++) {
                        if (bi.charAt(i) == '*') {
                            check[i] = true;
                        }
                    }
                    
                    // 나머지 같은지 확인
                    for (int i = 0 ; i < ui.length() ; i++) {
                        if (check[i] == false && ui.charAt(i) == bi.charAt(i)) {
                            check[i] = true;
                        }
                    }
            
                    boolean c = true; // 기본값을 true로 잡고 체크하는 게 편합니다.
                    for (boolean b : check) {
                        if (!b) {
                            c = false;
                            break;
                        }
                    }
                    
                    if (c) {
                        adj[idxBI].add(idxUI);
                    }
                }
                idxUI++;
            }
            idxBI++;
        }
        
        Set<String> resultSet = new HashSet<>();
        dfs(0, new boolean[user_id.length], adj, resultSet);

        return resultSet.size();
    }
}