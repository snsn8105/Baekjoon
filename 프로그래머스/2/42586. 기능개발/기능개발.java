import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<Integer> returnTmp = new ArrayList<>();
        
        for(int i = 0 ; i<speeds.length ; i++){
            queue.add(new int[]{progresses[i], speeds[i]});    
        }
        
        while(!queue.isEmpty()){
            int count = 0;            
            while (!queue.isEmpty() && queue.peek()[0] >= 100) {
                queue.poll();
                count++;
            }

            if(count > 0){
                returnTmp.add(count);
            }
            int length = queue.size();
            for(int i = 0 ; i<length ; i++){
                int[] k = new int[2];
                k = queue.poll();
                k[0] += k[1];
                queue.add(k);
            }
        }
        
        int[] answer = new int[returnTmp.size()];
        for(int i = 0 ; i<answer.length ; i++){
            answer[i] = returnTmp.get(i);
        }
        return answer;
    }
}