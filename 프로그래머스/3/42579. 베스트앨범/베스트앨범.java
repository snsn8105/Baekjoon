import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, Integer> genreList = new HashMap<>(); // 장르들을 저장할 해시맵 <장르이름, 총 재생횟수>
        HashMap<String, TreeSet<int[]>> rankofGenre = new HashMap<>(); // 장르마다 노래 순위 저장 [0]이 index, [1]이 plays
        
        for(int i = 0 ; i < plays.length ; i++){
            if(!genreList.containsKey(genres[i])){
                genreList.put(genres[i], plays[i]);
                rankofGenre.put(genres[i], new TreeSet<>(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        if (b[1] == a[1]) return a[0] - b[0]; // 재생 횟수가 같다면 index 오름차순
                        return b[1] - a[1]; // 재생 횟수 내림차순 정렬
                    }
                }));
                rankofGenre.get(genres[i]).add(new int[]{i, plays[i]});
            }
            else{
                genreList.put(genres[i], genreList.getOrDefault(genres[i], 0) + plays[i]); // 이미 존재하면 plays만큼 더한 값을 value에 저장
                rankofGenre.get(genres[i]).add(new int[]{i, plays[i]});
            }
        }
        
        List<Integer> answerList = new ArrayList<>(); // ✅ 변경: 결과 저장을 위한 ArrayList 사용

        while(!genreList.isEmpty()){
            List<String> keys = new ArrayList<>(genreList.keySet());
            String maxKey = keys.get(0);
            
            for(String key : keys){ // value가 가장 큰 key를 찾는다
                if(genreList.get(key) > genreList.get(maxKey)){
                    maxKey = key;
                }
            }
            
            // maxKey로 plays가 가장 큰 2개 노래를 찾음, 노래 개수가 충분하면 2개까지 반복
            for(int i = 0 ; i < 2 && !rankofGenre.get(maxKey).isEmpty() ; i++){
                int[] song = rankofGenre.get(maxKey).pollFirst(); // 최대값 반환 후 삭제
                answerList.add(song[0]); // ✅ 변경: ArrayList에 추가
            }
            genreList.remove(maxKey);
        }

        // ✅ 변경: ArrayList를 배열로 변환하여 반환
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
