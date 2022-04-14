package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 점심식사시간_2383 {

	static class Person implements Comparable<Person> {
		int r, c, downCnt, status, time;	// 행, 열, 계단 수, 상태, 입구 도착 시간

		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public void init() {
			time = downCnt = 0;
			status = M;
		}

		@Override
		public int compareTo(Person o) {
			// 계단 입구까지 도착하는데 소요시간 가장 짧은 사람 (오름차순)
			return this.time - o.time;
		}
	}
	
	static final int M = 1, W = 2, D = 3, C = 4;	// Move Wait Down Complete
	
	static int N, min, cnt;		// 한 변의 길이, 모두 계단을 내려가는 최소 시간, 사람 수
	static boolean[] selected;	// 부분집합의 구현에서 사용할 선택 배열 (선택되면 계단 1, 안되면 계단 2)
	static List<Person> pList;	// 사람 리스트
	static int[][] sList;		// 계단 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			
			pList = new ArrayList<>();
			sList = new int[2][];	// 계단 수 2개
			min = Integer.MAX_VALUE;
			
			for (int i = 0, k = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int c = Integer.parseInt(st.nextToken());
					if (c == 1) {	// 사람이면
						pList.add(new Person(i, j));
					} else if (c > 1) {	// 계단이면
						sList[k++] = new int[] {i, j, c};
					}
				}
			}
			
			cnt = pList.size();		// 사람 수
			selected = new boolean[cnt];
			
			divide(0);	// 계단 배정하기
			System.out.println("#" + t + " " + min);
		}
	}

	// 부분 집합을 이용한 계단 배정
	private static void divide(int index) {	// index : 처리할 사람의 인덱스
		if (index == cnt) {
			makeList();
			return;
		}
		
		// 부분집합에 포함 : 계단 1 배정
		selected[index] = true;
		divide(index + 1);
		// 부분집합에 비포함 : 계단 2 배정
		selected[index] = false;
		divide(index + 1);
	}

	// selected 상태에 따라 두 계단을 이용하는 각각의 리스트 생성
	private static void makeList() {
		ArrayList<Person> aList = new ArrayList<>();
		ArrayList<Person> bList = new ArrayList<>();
		
		for (int i = 0; i < cnt; i++) {
			Person p = pList.get(i);
			p.init();	// 데이터 초기화
			if (selected[i]) {
				p.time = Math.abs(p.r - sList[0][0]) + Math.abs(p.c - sList[0][1]);
				aList.add(p);
			} else {
				p.time = Math.abs(p.r - sList[1][0]) + Math.abs(p.c - sList[1][1]);
				bList.add(p);
			}
		}
		
		int res = go(aList, bList);	// 두 계단 리스트의 사람들이 모두 내려가는데 소요 시간
		
		if (min > res) min = res;
	}

	private static int go(ArrayList<Person> aList, ArrayList<Person> bList) {
		
		int timeA = 0, timeB = 0;
		
		if (aList.size() > 0) timeA = goDown(aList, sList[0][2]);
		if (bList.size() > 0) timeB = goDown(bList, sList[1][2]);
		
		return timeA > timeB ? timeA : timeB;
	}

	private static int goDown(ArrayList<Person> list, int height) {
		Collections.sort(list);		// 계단 입구까지 도착하는데 소요 시간 빠른 순 정렬
		
		int time = list.get(0).time;	// 흘러가는 시간, 첫 번째 사람의 계단입구 도착 시간부터 처리
		int size = list.size();
		int ingCnt = 0, cCnt = 0;	// 내려가고 있는 사람 수, 다 내려간 사람 수
		while (true) {
			// 매 시간 사람들을 하나씩 꺼내어 상태를 처리
			for (int i = 0; i < size; i++) {
				Person p = list.get(i);
				
				if (p.status == C) continue;
				
				if (p.time == time) {	// 현재 시간이 사람의 계단입구 도착 시간과 같으면
					p.status = W;
				} else if (p.status == W && ingCnt < 3) {
					p.status = D;
					p.downCnt = 1;
					++ingCnt;
				} else if (p.status == D) {
					if (p.downCnt < height) {	// 다 내려가지 못한 사람
						p.downCnt++;
					} else {		// 다 내려간 사람
						p.status = C;
						++cCnt;
						--ingCnt;
					}
				}
			}
			if (cCnt == size) break;
			++time;
		}
		
		return time;
	}
}
