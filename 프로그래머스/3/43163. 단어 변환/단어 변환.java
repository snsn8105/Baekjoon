import java.util.*;
class Solution {
    public boolean stringCompare(String now, String word){
        boolean result = false;
        int notEqualCount = 0;
        for(int i = 0 ; i<now.length() ; i++){
            if(now.charAt(i) != word.charAt(i)){
                notEqualCount++;
            }
        }
        
        if(notEqualCount == 1){
            result = true;
        }
        
        return result;
    }
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);

        int count = 0; // 변환 횟수 (레벨)

        while (!queue.isEmpty()) {
            // 1. 현재 레벨(큐)에 있는 노드 개수 확인
            int size = queue.size(); 

            // 2. 현재 레벨의 노드들만(size 만큼) 처리
            for (int i = 0; i < size; i++) { 
                String now = queue.poll();

                if (now.equals(target)) {
                    return count; // 찾으면 현재 레벨(count) 반환
                }

                // 다음 레벨 노드들을 큐에 추가
                for (int j = 0; j < words.length; j++) {
                    if (!visited[j] && stringCompare(now, words[j])) {
                        visited[j] = true;
                        queue.add(words[j]);
                    }
                }
            }

            // 3. 현재 레벨 탐색이 끝났으므로 횟수(레벨) 증가
            count++; 
        }

        return 0; // 큐가 비었는데 못 찾음
    }
}