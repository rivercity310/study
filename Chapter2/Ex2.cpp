#include <iostream>

using namespace std;

void bubbleSort(float* k, int size);
void maxInteger();
void maxFloat();

/*
1. 키보드로부터 두 개의 정수를 읽어 큰 수를 화면에 출력
2. 소수점을 가지는 5개의 실수를 입력 받아 제일 큰 수를 화면에 출력
*/

void maxInteger() {
	cout << "두 수를 입력하라: ";
	int x, y;
	cin >> x >> y;

	int result = x > y ? x : y;
	cout << "큰 수 = " << result << endl;
}

void maxFloat() {
	cout << "5개의 실수를 입력하라: ";
	
	float k[5];
	for (int i = 0; i < sizeof(k) / sizeof(k[0]); i++) cin >> k[i];

	cout << "입력받은 배열: \n";
	for (int i = 0; i < 5; i++) cout << k[i] << "  ";
	cout << endl;

	bubbleSort(k, 5);
	cout << "버블 정렬 후 배열: \n";
	for (int i = 0; i < 5; i++) cout << k[i] << "  ";
	cout << endl;

	cout << "제일 큰 수 = " << k[4] << endl;
}

void bubbleSort(float *k, int size) {
	for (int i = 0; i < size - 1; i++) {
		for (int j = 0; j < size - i - 1; j++) {
			if (k[j] > k[j + 1]) {
				float temp = k[j + 1];
				k[j + 1] = k[j];
				k[j] = temp;
			}
		}
	}
}
