#define MAIN

// #define WEEK1
// #define WEEK2
// #define WEEK3
#define WEEK4

#ifdef MAIN
#include <iostream>
#include <vector>
#include <algorithm>
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0)
using namespace std;
#endif

#ifdef WEEK1
extern void boj_1620();			// 나는야 포켓몬 마스터 이다솜
extern void boj_2504();			// * 미해결
extern void boj_9012();			// 괄호 문자열
extern void boj_1541();			// 잃어버린 괄호
#endif

#ifdef WEEK2
extern void boj_1874();			// 스택 수열
extern void boj_11866();        // 요세푸스 문제 (반복)
extern void boj_11866_2();		// 요세푸스 문제 (재귀)
extern void boj_4949();         // 균형잡힌 세상 (스택) 
extern void boj_10816();        // 숫자 카드2
extern void boj_2587();			// 대표값
extern void boj_4195();			// 친구 네트워크 * 미해결
extern void boj_24060();		// 재귀를 이용한 병합정렬 * 미해결
extern void boj_15828();		// Deque을 이용한 Router 문제
extern void boj_1966();			// 프린터 큐	* 미해결
extern void boj_1004();			// 어린 왕자
#endif

#ifdef WEEK3
extern void boj_11279();		// 최대힙
extern void boj_11000();		// 강의실 배정
#endif

#ifdef WEEK4
extern void boj_1012();			// 유기농 배추
extern void boj_1920();			// 수 찾기 (이분 탐색)
extern void boj_11051();		// 이항계수2	     * 미해결
extern void boj_1010();			// 다리놓기
extern void boj_9375();			// 패션왕 신해빈	 * 미해결
extern void boj_2667();			// 단지번호 붙이기 (그래프)
extern void boj_2178();			// 미로탐색 (그래프)
extern void pc_test();
#endif

int main() {
	boj_2178();
}