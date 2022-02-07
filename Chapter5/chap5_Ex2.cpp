#include <iostream>
using namespace std;

/*
참조에 의한 호출 방식으로 주어진 n의 반값을 구해 n을 바꾸는 half() 함수를 작성하라
*/

void half(double& n) {
	n = n / 2;
}

void Ex2() {
	double n = 20;
	cout << n << endl;
	half(n);
	cout << n << endl;
}