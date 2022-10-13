#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

void big_number() {
	int n, m, k;
	cin >> n >> m >> k;

	vector<int> v;
	v.reserve(n);

	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;

		v.push_back(num);
	}

	std::sort(v.begin(), v.end());

	int first = *(v.end() - 1);
	int second = *(v.end() - 2);

	cout << first << " " << second << endl;

	int cnt = m;
	int ans = 0;

	while (true) {
		for (int _ = 0; _ < k; _++) {
			if (cnt == 0) break;

			ans += first;
			cnt--;
		}

		if (cnt == 0) break;

		ans += second;
		cnt--;
	}

	cout << ans << endl;




	int ans2 = 0;
	int count = ((m / (k + 1)) * k) + (m % (k + 1));

	ans2 += count * first;
	ans2 += (m - count) * second;

	cout << ans2 << endl;
}