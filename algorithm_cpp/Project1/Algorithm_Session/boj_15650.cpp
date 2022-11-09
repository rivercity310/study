#include <iostream>

using namespace std;

int comb(int n, int r) {
	if (r == 0 || r == n)
		return 1;

	return comb(n - 1, r - 1) + comb(n - 1, r);
}