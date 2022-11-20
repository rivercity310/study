#include <iostream>

using namespace std;

constexpr int MAX = 1001;
size_t ft[MAX];

static size_t factorial(int n) {
	if (ft[n])
		return ft[n];

	for (int i = 2; i <= n; i++)
		ft[i] = ft[i - 1] * i;

	return ft[n];
}

void boj_11051() {
	ft[0] = 1;
	ft[1] = 1;

	for (int i = 0; i < 30; i++) {
		size_t rst = factorial(i);
		cout << "factorial(" << i << ") = " << rst << "\n";
	}
}