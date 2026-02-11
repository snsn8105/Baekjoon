import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while (end < n) {
            sum += sequence[end];
            
            while (sum > k && start <= end) {
                sum -= sequence[start];
                start++;
            }
            if (sum == k) {
                int currentLength = end - start;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    answer[0] = start;
                    answer[1] = end;
                }
            }   
            end++;
        }
        return answer;
    }
}