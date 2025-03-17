import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int totalItems = 10; // 10일 동안의 연속 구매
        
        // 원하는 품목과 개수를 저장한 맵 (원본)
        HashMap<String, Integer> originalWishList = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            originalWishList.put(want[i], number[i]);
        }

        // 슬라이딩 윈도우 방식으로 10일씩 검사
        for (int i = 0; i <= discount.length - totalItems; i++) {
            // `wishList`를 초기 상태로 복사
            HashMap<String, Integer> wishList = new HashMap<>(originalWishList);
            
            // 10일 동안의 품목을 확인
            for (int j = i; j < i + totalItems; j++) {
                if (wishList.containsKey(discount[j])) {
                    wishList.put(discount[j], wishList.get(discount[j]) - 1);
                    
                    // 개수가 0이면 제거
                    if (wishList.get(discount[j]) == 0) {
                        wishList.remove(discount[j]);
                    }
                }
            }
            
            // 모든 품목을 만족하면 answer 증가
            if (wishList.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}
