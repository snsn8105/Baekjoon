import java.util.*;
class Solution {
    public void mapAdd(Map<String, List<Integer>> map, String[] infos, String result, int depth) {
        if (depth == 4) {
            List<Integer> list = map.get(result);
            int v = Integer.parseInt(infos[4]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(v);
                map.put(result, list);
            } else {
                list.add(v);
                map.put(result, list);   
            }
            return;
        }
        
        // String에 어떤거 추가할지 분기
        mapAdd(map, infos, result + "-", depth+1);
        
        mapAdd(map, infos, result + infos[depth], depth+1);
        
        return;
    }
    public int binSearch(Map<String, List<Integer>> map, String sentence, int score) {
        List<Integer> cList = map.get(sentence);
        int left = 0;
        int right = cList.size() - 1; // List의 길이를 최댓값으로 지정
        int resultIdx = cList.size();
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (score <= cList.get(mid)) {
                resultIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return cList.size() - resultIdx;
    }
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>(); // 언어 ~ 소울푸드 붙여서 String으로 key, 점수가 value
        
        // map에 모든 경우의 문자열과 점수 입력
        for (String i : info) {
            String[] infos = i.split(" ");
            
            mapAdd(map, infos, "", 0);
        }
        // 정렬은 데이터 삽입이 다 끝난 후 한 번만
        for (List<Integer> values : map.values()) {
            Collections.sort(values);
        }
        
        int[] answer = new int[query.length];
        // query 순회
        int idx = 0;
        for (String q : query) {
            String cleaned = q.replaceAll(" and ", "");
            String[] qs = cleaned.split(" ");
            String sentence = qs[0];
            int score = Integer.parseInt(qs[1]);
            
            if (map.containsKey(sentence)) {
                answer[idx] = binSearch(map, sentence, score);
            } else {
                answer[idx] = 0;
            }
            
            idx++;
        }
        
        return answer;
    }
}