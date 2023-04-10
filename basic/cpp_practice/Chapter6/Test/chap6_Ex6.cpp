#include <iostream>

class ArrayUtil6_2 {
public:
	// s1과 s2를 연결한 새로운 배열을 동적 생성하고 포인터 리턴
	static int* concat(int* s1, int* s2, int size = 5);

	// s1에서 s2에 있는 숫자를 모두 삭제 (차집합, s1 - s2) 한 새로운 배열을 동적 생성하여 리턴
	// retSize는 리턴하는 배열의 크기, retSize가 0인 경우 NULL 리턴
	static int* remove(int* s1, int* s2, int size, int& retSize);
};

int* ArrayUtil6_2::concat(int* s1, int* s2, int size) {
	int* concated = new int[size * 2];

	for (int i = 0; i < size; i++) {
		*(concated + i) = *(s1 + i);
		*(concated + size + i) = *(s2 + i);
	}

	return concated;
}

int* ArrayUtil6_2::remove(int* s1, int* s2, int size, int& retSize) {
	int* removed = new int[size];

	int rIdx = 0;
	for (int i = 0; i < size; i++) {
		bool check = false;
		for (int j = 0; j < size; j++) {
			if (s1[i] == s2[j]) {
				check = true;
				break;
			}
		}

		if (!check) {
			*(removed + rIdx) = s1[i];
			rIdx++;
		}
	}

	retSize = rIdx;
	if (retSize == 0) return NULL;
	else return removed;
}


void chap6_Ex6() {
	std::cout << "(x) 정수 5개 입력: ";
	int x[5];
	for (int i = 0; i < 5; i++) {
		int input;
		std::cin >> input;
		x[i] = input;
	}

	std::cout << "(y) 정수 5개 입력: ";
	int y[5];
	for (int i = 0; i < 5; i++) {
		int input;
		std::cin >> input;
		y[i] = input;
	}

	std::cout << std::endl << "합친 정수 배열을 출력한다..." << std::endl;
	int* concated = ArrayUtil6_2::concat(x, y);
	for (int i = 0; i < 10; i++) std::cout << *(concated + i) << " ";
	std::cout << std::endl;

	std::cout << std::endl << "배열 x에서 y를 뺀 결과를 출력한다...." << std::endl;
	int retSize;
	int* removed = ArrayUtil6_2::remove(x, y, 5, retSize);
	for (int i = 0; i < retSize; i++) std::cout << *(removed + i) << " ";
	std::cout << std::endl << std::endl << "삭제 후 배열의 길이는 " << retSize << std::endl;

	delete[] concated;
	delete[] removed;
}