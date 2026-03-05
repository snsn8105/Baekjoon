import java.util.*;
class Solution {
    public String convertToString(int time) {
        int hour = time / 60;
        int minute = time % 60;
        
        String s = String.format("%02d", hour) + ":" + String.format("%02d", minute);
        
        return s;
    }
    public String solution(int n, int t, int m, String[] timetable) {
        // n번 반복해서 언제가 제일 늦은 시각인지 구하기
        // 일단 분으로 펼쳐서 생각
        List<Integer> table = new ArrayList<>();
        
        for (String s : timetable) {
            String[] hm = s.split(":");
            int minute = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]); 
            
            table.add(minute);
        }
        Collections.sort(table);
        
        // 시작점은 540분, t간격으로 n번 온다
        int conTime = 0;
        int currentTime = 540;
        for (int i = 0 ; i < n ; i++) {
            int onBoard = 0;
            // 마지막 차일 때 탑승
            if (i == n-1) {
                while (onBoard < m - 1 && table.size() > 0 && table.get(0) <= currentTime) {
                    table.remove(0);
                    onBoard++;
                }
                if (table.size() == 0 || table.get(0) > currentTime)
                    conTime = currentTime;
                else
                    conTime = table.get(0) - 1;
            }
            // 마지막 차 이전에 온 승객들은 탑승 처리
            while (onBoard < m && table.size() > 0 && table.get(0) <= currentTime) {
                table.remove(0);
                onBoard++; // 탑승객 +1
                
            }
            
            currentTime += t;
        }
        
        return convertToString(conTime);
    }
}