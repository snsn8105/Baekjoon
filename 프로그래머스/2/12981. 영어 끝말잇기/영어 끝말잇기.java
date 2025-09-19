import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int loops = 0;
        int defeat = 0;
        
        HashSet<String> set = new HashSet<>();
        
        int turnTracker = 0;
        char last = ' ';
        
        for(int i = 0; i < words.length; i++){
            if(i != 0)
                last = words[i-1].charAt(words[i-1].length() - 1); // getChar()와 length 속성 수정
            
            turnTracker = i;
            if(turnTracker >= n){
                turnTracker = turnTracker % n;
            }
            
            if(turnTracker == 0)
                loops++;
            
            if(i != 0 && last != words[i].charAt(0)) // getChar()와 length 속성 수정
                break;
            
            if(!set.contains(words[i])) // exist() 수정
                set.add(words[i]);
            else{
                break;
            }
        }
        
        int[] answer = {turnTracker+1, loops};
        
        if(set.size() == words.length){
            int[] answer2 = {0,0};
            answer = answer2;
        }
        
        return answer;
    }
}