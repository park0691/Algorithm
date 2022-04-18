package programmers;

import java.util.*;

public class 신고결과받기 {

	public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, ArrayList<String>> reportMap = new HashMap<>();
        StringTokenizer st;
        
        for (String id : id_list) {
            map.put(id, 0);
            reportMap.put(id, new ArrayList<>());
        }
        
        Set<String> set = new HashSet<>(Arrays.asList(report));
        
        for (String r : set) {
            st = new StringTokenizer(r);
            String id = st.nextToken();
            String target = st.nextToken();
            map.put(target, map.get(target) + 1);
            reportMap.get(id).add(target);
        }
        
        int i = 0;
        int[] answer = new int[id_list.length];
        
        for (String id : id_list) {
            int count = 0;
            ArrayList<String> list = reportMap.get(id);
            for (String target : list) {
                if (map.get(target) >= k) {
                    count++;
                }
            }
            answer[i++] = count;
        }
        
        return answer;
    }

}
