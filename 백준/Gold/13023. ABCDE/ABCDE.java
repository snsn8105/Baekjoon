import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 인접 리스트로 그래프 표현
    static ArrayList<Integer>[] adjList;
    // 방문 여부를 체크할 배열
    static boolean[] visited;
    // ABCDE 관계 존재 여부 플래그
    static boolean arrived = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            // 양방향 관계 설정
            adjList[p1].add(p2);
            adjList[p2].add(p1);
        }

        // 모든 노드를 시작점으로 하여 DFS 실행
        for (int i = 0; i < N; i++) {
            visited = new boolean[N]; // 각 시작점마다 visited 배열 초기화
            dfs(i, 0);
            if (arrived) {
                break; // 관계를 찾았으면 더 이상 탐색할 필요 없음
            }
        }

        if (arrived) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    /**
     * 깊이 우선 탐색 (DFS)
     * @param current 현재 탐색 중인 사람의 번호
     * @param depth 현재까지의 친구 관계 깊이
     */
    public static void dfs(int current, int depth) {
        // 깊이가 4에 도달하면 5명이 연결된 것 (A-B-C-D-E)
        if (depth == 4) {
            arrived = true;
            return;
        }

        visited[current] = true; // 현재 노드 방문 처리

        // 현재 노드의 친구들을 순회
        for (int next : adjList[current]) {
            // 아직 방문하지 않은 친구라면
            if (!visited[next]) {
                dfs(next, depth + 1); // 깊이를 1 증가시켜 재귀 호출
                if (arrived) return; // 다른 노드에서 이미 경로를 찾았다면 즉시 종료
            }
        }
        
        // 현재 노드에서 출발하는 모든 경로 탐색 후, 다른 경로에서도 현재 노드를 방문할 수 있도록 방문 기록을 해제
        visited[current] = false;
    }
}