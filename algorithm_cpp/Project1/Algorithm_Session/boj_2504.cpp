#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
using namespace std;

void boj_2504() {
	string s;
	cin >> s;

	if (s[0] == ')' || s[0] == ']' || s[s.size() - 1] == '[' || s[s.size() - 1] == '(') {
		cout << 0;
		return;
	}

	vector<int> rst(15, 1);
	stack<char> stk;

	int idx = 0;

	stk.push(s[0]);
	for (int i = 1; i < s.size(); i++) {
		while (!stk.empty()) {
			char c = s[i];
			char d = stk.top();

			if (d == '(' && c == ')') {
				stk.pop();
				rst[idx] *= 2;
			}
			else if (d == '[' && c == ']') {
				stk.pop();
				rst[idx] *= 3;
			}
			else {
				stk.push(c);
				break;
			}
		}

		if (stk.size() == 0)
			idx++;
	}



	int ans = 0;
	if (stk.size() != 0) {
		cout << "Stack not Empty" << "\n";
		while (!stk.empty()) {
			cout << "pop: " << stk.top() << "\n";
			stk.pop();
		}
		
		cout << 0;
	}
	else {
		for (int i = 0; i < idx; i++)
			if (rst[i] != 1) {
				cout << "rst[" << i << "] = " << rst[i] << "\n";
				ans += rst[i];
			}

		cout << ans;
	}
}

// rst * 2 * 3 * 3 * 2
// ([