#include <iostream>

using namespace std;

/*
1. 1부터 100까지 정수를 한 줄에 10개씩 출력, 각 정수는 탭으로 구분
2. 구구단을 출력하는 프로그램 작성, 탭으로 구분
*/

void printInteger() {
	int count = 1;
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			cout << count++ << "\t";
		}

		cout << endl;
	}
}

void printMultiplication() {
	for (int i = 1; i <= 9; i++) {
		for (int j = 1; j <= 9; j++)
			cout << i << "x" << j << "=" << i * j << '\t';

		cout << endl;
	}
}
