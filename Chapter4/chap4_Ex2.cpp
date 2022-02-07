#include <iostream>

using namespace std;

/*
정수 공간 5개를 배열로 동적 할당받고, 정수를 5개 입력받아 평균을 출력한 뒤 배열을 소멸시키도록 작성
*/

void Ex2() {
	int* dIntAry = new int[5];
	int sum = 0;

	cout << "정수 5개 입력: ";
	for (int i = 0; i < 5; i++) {
		int inputVal;
		cin >> inputVal;
		dIntAry[i] = inputVal;

		sum += dIntAry[i];
	}

	double aver = (double)sum / 5;
	cout << "평균은: " << aver << "입니다." << endl;
	delete[] dIntAry;
}