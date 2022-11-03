#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

void boj_11000() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;

	vector<pair<int, int>> table(n);

	for (int i = 0; i < n; i++) 
		cin >> table[i].first >> table[i].second;
	
	std::sort(table.begin(), table.end(), [](const auto& a, const auto& b) {
		if (a.first == b.first)
			return a.second < b.second;
		return a.first < b.first;
		});

	priority_queue<int> pq;		// 기본적으로 최대힙
	pq.push(-table[0].second);

	for (int i = 1; i < n; i++) {
		if (-pq.top() <= table[i].first) pq.pop();
		pq.push(-table[i].second);
	}

	cout << pq.size();
}
