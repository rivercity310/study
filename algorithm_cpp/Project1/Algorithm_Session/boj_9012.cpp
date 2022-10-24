#include <iostream>
#include <stack>
#include <string>

using namespace std;

/*
괄호 문자열 문제
- Stack을 이용하여 해결
*/

void boj_9012() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	int T;
	cin >> T;

	while (T--) {
		string s;
		cin >> s;
		
		stack<char> stk;
		bool flag = true;

		for (char c : s) {
			if (c == '(')
				stk.push(c);
			else {
				if (!stk.empty())
					stk.pop();
				else {
					flag = false;
					break;
				}
			}
		}

		if (stk.empty() && flag) cout << "YES" << "\n";
		else cout << "NO" << "\n";
	}
}
