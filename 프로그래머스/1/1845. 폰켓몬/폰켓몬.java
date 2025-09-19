import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        
        for(int i : nums){
            set.add(i);
        }
        
        int N = nums.length;
        int size = set.size();
        int pickNum = N / 2;
        
        int answer = 0;
        
        if(size >= pickNum){
            answer = pickNum;    
        }
        else{
            answer = size;
        }
        
        return answer;
    }
}