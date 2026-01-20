import java.util.*;
class Solution {
    public boolean checkFullTree(String s, boolean isParentZero) {
        int midIndex = s.length() / 2;
        char current = s.charAt(midIndex);

        // [핵심 조건] 부모가 0이었는데 내가 1이라면? -> 표현 불가능!
        if (isParentZero && current == '1') {
            return false;
        }

        // Base Case: 리프 노드까지 도달했다면 성공
        if (s.length() == 1) {
            return true;
        }

        // 다음 자식들에게 전달할 플래그 설정
        // 현재 노드가 '0'이거나, 이미 부모가 '0'이었다면 다음 자식들도 '0'이어야 함
        boolean nextIsParentZero = (current == '0' || isParentZero);

        String sLeft = s.substring(0, midIndex);
        String sRight = s.substring(midIndex + 1);

        return checkFullTree(sLeft, nextIsParentZero) && checkFullTree(sRight, nextIsParentZero);
    }
    public int[] solution(long[] numbers) {
        // numbers를 이진수로 바꾼 후에 리프노드까지 중앙값이 0인 경우가 있는지 재귀적으로 확인
        // String 길이를 2^n -1 로 맞춰줘야함        
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String s = Long.toBinaryString(numbers[i]);

            // 1. 목표 길이(targetlength) 찾기 (이미 비트 연산으로 잘 구현하셨습니다!)
            int h = 1;
            int targetlength = 1;
            while (targetlength < s.length()) {
                h++;
                targetlength = (1 << h) - 1;
            }

            // 2. [수정] 부족한 만큼만 '0'을 앞에 채워넣기
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < targetlength) {
                sb.insert(0, "0");
            }
            s = sb.toString();

            // 3. 재귀 호출 (처음 시작할 때는 부모가 없으므로 false 전달)
            if (checkFullTree(s, false)) 
                answer[i] = 1;
            else 
                answer[i] = 0;
        }
        return answer;
    }
}
