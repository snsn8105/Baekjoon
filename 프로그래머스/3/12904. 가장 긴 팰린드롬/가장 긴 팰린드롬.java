import java.util.*;

class Solution
{
    public int solution(String s)
    {
        // 모든 문자를 중심으로 두고 좌우가 같은 길이를 측정해서 answer에 저장
        int answer = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            char cur = s.charAt(i);
            int count = 0;
            boolean isSame = true;
            
            while (isSame) {
                int left = i - count;
                int right = i + count;
                
                if (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                    count++;
                } else
                    isSame = false;
            }
            
            // 중심이 없는 짝수 길이인 경우도 확인
            int countEven = 0;
            while (i - countEven >= 0 && i + countEven + 1 < s.length()) {
                if (s.charAt(i - countEven) == s.charAt(i + countEven + 1))
                    countEven++;
                else
                    break;
            }
            answer = Math.max(Math.max(answer, count * 2 - 1), countEven * 2);
        }

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}