import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        // 스택 활용
        String[] answer = new String[s.length];
        int idx = 0;
        for (String s1 : s) {
            StringBuilder stack = new StringBuilder();
            int oozCount = 0;
            for (int i = 0 ; i < s1.length() ; i++) {
                
                stack.append(s1.charAt(i));
                int l = stack.length();
                if (l >= 3) {
                    char char1 = stack.charAt(l-3);
                    char char2 = stack.charAt(l-2);
                    char char3 = stack.charAt(l-1);
                    
                    if (char1 == '1' && char2 == '1' && char3 == '0') {
                        oozCount++;
                        stack.delete(l-3, l);
                    }
                }
            }
            // 110 다 찾았으면 가장 오른쪽 0을 찾아서 그 뒤에 110을 넣는다
            // 1만 있으면 가장 앞에 넣는다
            boolean existZero = false;
            int lastZeroIdx = -1;
            for (int i = stack.length() - 1; i >= 0; i--) {
                if (stack.charAt(i) == '0') {
                    lastZeroIdx = i;
                    break; 
                }
            }

            if (lastZeroIdx != -1) {
                // 마지막 0을 찾은 경우
                for (int j = 0; j < oozCount; j++) {
                    stack.insert(lastZeroIdx + 1, "110");
                }
            } else {
                // 0이 하나도 없는 경우
                for (int j = 0; j < oozCount; j++) {
                    stack.insert(0, "110");
                }
            }
            answer[idx] = stack.toString();
            idx++;
        }
        
        return answer;
    }
}