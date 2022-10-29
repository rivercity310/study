#include <iostream>
#include <stack>

using namespace std;

void boj_1874() {
	
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

		/*
		exit : 바로 C 프로그램 자체 프로세스 종료 (0 : 정상종료, 1 : 에러메세지 종료)
		return : 뒤 문장을 실행하며 종료
		*/

		if (stk.top() != k) {
			cout << "NO";
			exit(0);
		}

		ans += "-\n";
		stk.pop();
	}

	cout << ans;
}