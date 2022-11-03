#include <iostream>
#include <queue>

using namespace std;

void boj_11279() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;

	priority_queue<int> pq;
	
	while (n--) {
		int x;
		cin >> x;

		if (x == 0) {
			if (pq.empty())
				cout << 0;
			else {
				cout << pq.top();
				pq.pop();
			}

			cout << "\n";
		}
		else {
			pq.push(x);
		}
	}
}