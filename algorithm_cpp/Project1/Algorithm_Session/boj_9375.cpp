#include <iostream>
#include <string>
#include <sstream>
#include <unordered_map>

using namespace std;

void boj_9375() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int t;
	cin >> t;

	while (t--) {
		int n;
		cin >> n;

		string s1, s2;
		cin >> s1 >> s2;

		unordered_map<string, vector<string>> m;
		m[s2].push_back(s1);

		int cnt = 0;

	}
}