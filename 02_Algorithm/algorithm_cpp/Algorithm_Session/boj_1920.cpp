#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

static vector<int> a;

static int bin_sch(int left, int right, int x) {
	int pl = left;
	int pr = right - 1;

	do {
		int pc = (pl + pr) / 2;
		if (a[pc] == x) return pc;
		else if (a[pc] > x) pr = pc - 1;
		else pl = pc + 1;
	} while (pl <= pr);

	return -1;
}

void boj_1920() {
	int n;
	cin >> n;

	a = vector<int>(n);
	for (int i = 0; i < a.size(); i++)
		cin >> a[i];

	std::sort(a.begin(), a.end());

	int m;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int x;
		cin >> x;

		int rst = bin_sch(0, n, x);

		if (rst == -1) cout << 0 << "\n";
		else cout << 1 << "\n";
	}
}