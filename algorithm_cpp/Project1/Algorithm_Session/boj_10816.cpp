#include <iostream>
#include <vector>

using namespace std;

void boj_10816() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;

	int base = 10000000;

	vector<int> v(2 * base + 1, 0);
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;

		v[base + x]++;
	}

	int m;
	cin >> m;
	for (int i = 0; i < m; i++) {
		int x;
		cin >> x;

		cout << v[base + x] << " ";
	}
}