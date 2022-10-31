#define MAIN
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0)

#define WEEK1
#define WEEK2

#ifdef MAIN
#include <iostream>
#include <stack>
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
extern void boj_11866();        // 요세푸스 문제
extern void boj_4949();         // 균형잡힌 세상 (스택)  * 미해결
#endif

int main() {
	boj_4949();
}