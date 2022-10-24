#include <iostream>
#include <string>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	// 처음 나오는 - 이후의 숫자들을 다음 - 가 나오기 전까지 빼준다.
	string s;
	cin >> s;

	int ans = 0;
	string tmp = "";

	for (int i = 0; i <= s.size(); i++) {
		if (s[i] == '+') {
			ans += stoi(tmp);
			tmp = "";
		}
		else if (s[i] == '-') {
			ans -= stoi(tmp);
			tmp = "";
		}
		else tmp += s[i];
	}
	
	cout << ans;
}