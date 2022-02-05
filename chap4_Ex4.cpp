#include <iostream>
#include "Sample.h"
using namespace std;

/*
Sample 클래스를 작성하고 활용하기
*/


void Sample::read() {
	cout << "정수 " << size << "개 입력: ";
	for (int i = 0; i < size; i++) {
		int inputInt;
		cin >> inputInt;
		p[i] = inputInt;
	}

	cout << "배열을 성공적으로 저장하였습니다." << endl;
}

void Sample::write() {
	cout << "배열을 출력합니다... " << endl;
	for (int i = 0; i < size; i++) {
		cout << "*(p + " << i << ")의 값: " << *(p + i) << endl;
	}
	cout << endl;
}

int Sample::big() {
	int max = 0;
	for (int i = 0; i < size; i++) {
		if (p[i] >= max) max = p[i];
	}

	return max;
}

void Ex4() {
	Sample s(10);
	s.read();
	s.write();
	cout << "배열에서 가장 큰 값은 " << s.big() << endl;
}