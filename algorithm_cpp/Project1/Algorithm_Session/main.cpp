#define MAIN
#define DAY1
#define BS
#define DAY2
#define DAY3

#ifdef MAIN
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0)
#include <iostream>
#include <stack>
using namespace std;
#endif

#ifdef DAY1
#endif

#ifdef BS
extern void boj_1620();			// 나는야 포켓몬 마스터 이다솜
extern void boj_2504();			// * 미해결
#endif

#ifdef DAY2
extern void boj_9012();			// 괄호 문자열
extern void boj_1541();			// 잃어버린 괄호
#endif

#ifdef DAY3
extern void boj_1874();			// 스택 수열
#endif

int main() {
	FAST_IO;
	
	int n;
	cin >> n;

	stack<int> stk;
	string ans = "";
	int bp = 0;

	while (n--) {
		int k;
		cin >> k;

		if (k > bp) {
			for (int i = bp + 1; i <= k; i++) {
				ans += "+\n";
				stk.push(i);
			}

			bp = k;
		}

		if (stk.top() != k) {
			cout << "NO";
			exit(0);
		}

		ans += "-\n";
		stk.pop();
	}

	cout << ans;
}