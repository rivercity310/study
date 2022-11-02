#include <iostream>
#include <string>
#include <stack>

using namespace std;

void boj_4949() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	while (true) {
		string s;
		getline(cin, s);
		
		if (s == ".") break;

		stack<char> stk;
		bool flag = true;

		for (char c : s) {
			if (c == '(' || c == '[')
				stk.push(c);

			else if (c == ')') {
				if (stk.empty() || stk.top() == '[') {
					flag = false;
					break;
				}

				else if (stk.top() == '(')
					stk.pop();
			}

			else if (c == ']') {
				if (stk.empty() || stk.top() == '(') {
					flag = false;
					break;
				}

				else if (stk.top() == '[')
					stk.pop();
			}
		}

		if (stk.empty() && flag) cout << "yes";
		else cout << "no";
		cout << endl;
	}
}