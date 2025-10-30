import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, List<String>> sets = new HashMap<>();
        
        // 각각 1개씩만 입는거 + 섞어 입는거 (2개 ~ N개)
        for(String[] cloth : clothes){
            if(sets.containsKey(cloth[1]))
                sets.get(cloth[1]).add(cloth[0]);
            else{
                List<String> list = new ArrayList<>();
                list.add(cloth[0]);
                sets.put(cloth[1], list);
            }
        }
        
        int answer = 1;

        for (String key : sets.keySet()) {
            List<String> items = sets.get(key);
            int count = items.size();

            // 해당 종류에서 하나를 입거나 안 입는 경우
            answer *= (count + 1);
        }

        // 3. 아무것도 안 입는 경우 제외
        return answer - 1;
    }
}