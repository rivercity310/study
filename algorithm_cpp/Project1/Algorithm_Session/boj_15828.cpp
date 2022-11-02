#include <iostream>
#include <deque>

using namespace std;

void boj_15828() {
	int n;
	cin >> n;

	deque<int> dq;

	while (true) {
		int x;
		cin >> x;

		if (x == -1)
			break;
		else if (x == 0) 
			dq.pop_front();
		else if (x > 0 && dq.size() < n)
			dq.push_back(x);
	}

	if (dq.empty()) cout << "empty";
	else
		for (int k : dq)
			cout << k << " ";
}