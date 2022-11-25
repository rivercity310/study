#include <iostream>
#include <queue>

using namespace std;

void boj_11866() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	queue<int> q;
	for (int i = 1; i <= n; i++) 
		q.push(i);

	cout << "<";
	while (n--) {
		int tmp = k - 1;
		
		while (tmp--) {
			q.push(q.front());
			q.pop();
		}

		cout << q.front();
		if (n > 0) cout << ", ";
		
		q.pop();
	}
	cout << ">";
}