#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int cntt;
bool dfs(vector<vector<int>>& grp, int i, int j) {
	if (i < 0 || i >= grp.size() || j < 0 || j >= grp.size())
		return false;

	if (grp[i][j] == 1) {
		grp[i][j] = 0;
		cntt++;
		
		dfs(grp, i + 1, j);
		dfs(grp, i - 1, j);
		dfs(grp, i, j + 1);
		dfs(grp, i, j - 1);

		return true;
	}

	return false;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	vector<vector<int>> grp(n, vector<int>(n));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			scanf_s("%1d", &grp[i][j]);

	vector<int> c;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (dfs(grp, i, j))
				c.push_back(cntt);

			cntt = 0;
		}
	}

	std::sort(c.begin(), c.end());
	cout << c.size() << "\n";
	for (int k : c)
		cout << k << "\n";
}