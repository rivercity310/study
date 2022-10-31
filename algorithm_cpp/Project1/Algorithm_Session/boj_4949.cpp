#include <iostream>
#include <sstream>
#include <string>
#include <stack>

using namespace std;

void boj_4949() {
	string ans = "";

	while (true) {
		string s;
		getline(cin, s);

		if (s == ".")
			break;

		istringstream iss(s);

		string buf;
		char delim = '.';
		while (getline(iss, buf, delim)) {
			stack<char> stk;

			for (char c : buf) {
				if (isalpha(c) || c == ' ')
					continue;

				if (c == '(' || c == '[') 
					stk.push(c);
		
				else if (!stk.empty()) {
					char top = stk.top();

					if (top == '(' && c == ')') {
						stk.pop();
						continue;
					}

					if (top == '[' && c == ']')
						stk.pop();
				}
			}

			if (stk.empty()) ans += "yes\n";
			else ans += "no\n";
		}
	}

	cout << ans;
}