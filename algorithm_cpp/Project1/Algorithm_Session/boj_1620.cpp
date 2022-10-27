#include <iostream>
#include <unordered_map>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

void boj_1620() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n, m;
	cin >> n >> m;

	unordered_map<string, int> map;
	vector<string> v(n + 1);
	for (int i = 1; i <= n; i++) {
		string s;
		cin >> s;
		map.insert(make_pair(s, i));
		v[i] = s;
	}

	for (int i = 0; i < m; i++) {
		string str;
		cin >> str;

		if ('1' <= str[0] && str[0] <= '9')
			cout << v[stoi(str)];
		else
			cout << map.at(str);

		cout << "\n";
	}
}