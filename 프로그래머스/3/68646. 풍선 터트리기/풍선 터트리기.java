import java.util.*;
class Solution {
    public int solution(int[] a) {
        if (a.length <= 2)
            return a.length;
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        leftMin[0] = a[0];
        for (int i = 1 ; i < a.length ; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        rightMin[a.length-1] = a[a.length-1];
        for (int i = a.length - 2; i >= 0 ; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        int answer = 0;
        for (int i = 0 ; i < a.length ; i++) {
            // left i과 right i 보다 둘 중 하나라도 작으면
            if (leftMin[i] >= a[i] || rightMin[i] >= a[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}