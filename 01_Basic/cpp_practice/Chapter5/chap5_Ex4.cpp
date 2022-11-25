#include <iostream>
using namespace std;

bool bigger(int a, int b, int& big) {
	if (a == b) {
		big = a;
		return true;
	}

	big = a > b ? a : b;
	return false;
}

void Ex4() {
	cout << "정수 2개 입력: ";
	int a, b;
	cin >> a >> b;

	int big;
	if (!bigger(a, b, big)) cout << "더 큰수는 " << big << "입니다." << endl;
	else cout << "두 수가 같습니다." << endl;
}