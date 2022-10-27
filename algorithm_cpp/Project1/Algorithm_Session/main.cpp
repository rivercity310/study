#define MAIN
//#define DAY1
#define BS
//#define DAY2

#ifdef MAIN
#include <iostream>
#include <stack>
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0)
using namespace std;
#endif

#ifdef DAY1
#endif

#ifdef BS
extern void boj_1620();
extern void boj_2504();
#endif

#ifdef DAY2
extern void boj_9012();
extern void boj_1541();
#endif

int main() {
	FAST_IO;
	
	int n;
	cin >> n;

	stack<int> stk;
	string ans = "";

	int x;
	cin >> x;

	for (int i = 1; i <= x; i++) {
		ans += "+\n";
		stk.push(i);
	}
	ans += "-\n";
	stk.pop();

	while (true) {
		int k;
		cin >> k;

		if (k < x) {
			for (int i = 0; i < x - k; i++) {
				stk.pop();
				ans += "-\n";
			}
		}
		else {
			for (int i = x + 1; i <= k; i++) {
				stk.push(i);
				ans += "+\n";
			}
			stk.pop();
			ans += "-\n";
		}

		if (stk.empty()) {
			cout << "NO";
			return 0;
		}
	}

	cout << ans;
}