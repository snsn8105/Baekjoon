import java.util.*;
class Solution {
    public void addElement(Set<Integer>[] dp, int C) {
        // C개의 N을 사칙연산 해야함
        for (int i = 1; i < C ; i++) {
            int A = i;
            int B = C - i;
            
            // dp[C]에 a b 사칙연산 결과 모두 저장
            // 모든 a 가져오고 모든 b 가져오면서 계산
            for (int a : dp[A]) {
                for (int b : dp[B]) {
                    dp[C].add(a+b);
                    dp[C].add(a-b);
                    
                    if (b != 0)
                        dp[C].add(a/b);
                    dp[C].add(a*b);
                }
            }
        }
    }
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9]; // 1 ~ 8개의 N을 사용한 모든 경우를 저장, 1부터 쌓아가면서
        
        for (int i = 1 ; i < 9 ; i++) {
            dp[i] = new HashSet<>();
        }
        
        // 5, 55, ... 부터 모두 넣어둠
        int n = 0;
        for (int i = 1 ; i < 9 ; i++) {
            n = n * 10 + N ; 
            dp[i].add(n);
        }
        if (dp[1].contains(number))
            return 1;
        
        // 다른 사칙연산 하는 경우 넣기
        for (int i = 2 ; i < 9 ; i++) {
            addElement(dp, i);

            if (dp[i].contains(number))
                return i;
        }
        
        return -1;
    }
}