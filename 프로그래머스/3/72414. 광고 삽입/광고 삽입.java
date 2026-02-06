import java.util.*;

class Solution {
    // 99:59:59를 초로 환산하면 359,999이므로 여유 있게 360,001 크기 할당
    long[] timeline = new long[360001]; 

    void timeCalc(int st, int et) {
        timeline[st] += 1;
        timeline[et] -= 1; // 종료 시점에는 시청자 수 감소
    }

    // 1. 초 단위 변환 헬퍼 함수
    int toSecond(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 3600 + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
    }

    // 2. 누적합 및 광고 최적 지점 계산
    int sweep(int playTime, int advTime) {
        // 1차 누적합: 특정 초(i)에 시청 중인 사람 수
        for (int i = 1; i <= playTime; i++) {
            timeline[i] += timeline[i - 1];
        }

        // 2차 누적합: 0초부터 i초까지의 모든 시청 시간의 누적 합 (매우 커지므로 long 필수)
        for (int i = 1; i <= playTime; i++) {
            timeline[i] += timeline[i - 1];
        }

        long maxView = timeline[advTime - 1]; // 0초부터 광고가 끝날 때까지의 합
        int maxStart = 0;

        // 슬라이딩 윈도우처럼 구간 합 계산
        for (int i = advTime; i <= playTime; i++) {
            // totalTime[i] - totalTime[i - advTime] = 해당 구간의 누적 재생 시간
            long currentView = timeline[i] - timeline[i - advTime];
            if (currentView > maxView) {
                maxView = currentView;
                maxStart = i - advTime + 1;
            }
        }
        return maxStart;
    }

    String convertToStr(int time) {
        int h = time / 3600;
        int m = (time % 3600) / 60;
        int s = time % 60;
        // String.format을 쓰면 01:02:03 처럼 두 자리 형식을 맞추기 매우 쉽습니다.
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = toSecond(play_time);
        int advSeconds = toSecond(adv_time);

        for (String log : logs) {
            String[] split = log.split("-");
            timeCalc(toSecond(split[0]), toSecond(split[1]));
        }

        int resultTime = sweep(playSeconds, advSeconds);
        return convertToStr(resultTime);
    }
}