#include <iostream>
#include <vector>

using namespace std;
constexpr int MAX = 31;

vector<vector<int>> v(MAX, vector<int>(MAX));

void boj_1010() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	v[1][0] = 1;
	v[1][1] = 1;
	
	for (int i = 2; i < MAX; i++) {
		for (int j = 0; j <= i; j++) {
			if (j == i || j == 0) {
				v[i][j] = 1;
				continue;
			}

			v[i][j] = v[i - 1][j - 1] + v[i - 1][j];
		}
	}

	int t;
	cin >> t;

	while (t--) {
		int n, m;
		cin >> n >> m;
		
		cout << v[m][n] << "\n";
	}
}