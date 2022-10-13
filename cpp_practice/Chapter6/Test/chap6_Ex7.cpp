#include <iostream>
#include <cstdlib>
#include <ctime>

class Random_6 {
public:
	static void seed() { srand((unsigned)time(NULL)); }
	static int nextInt(int min = 0, int max = 32767);    // min과 max 사이의 랜덤한 정수 리턴
	static char nextAlphabet();      // 랜덤 알파벳 문자 리턴
	static double nextDouble();      // 0보다 크거나 같고 1보다 작은 랜덤 실수 리턴
};


int Random_6::nextInt(int min, int max) {
	while (true) {
		int randomInt = rand();
		if ((min <= randomInt && max >= randomInt)) return randomInt;
		else continue;
	}
}

char Random_6::nextAlphabet() {
	char alphaSet[26];
	for (char c = 'A'; c <= 'Z'; c++) alphaSet[c - 65] = c;
	
	int randomInteger = nextInt(0, 25);
	return alphaSet[randomInteger];
}

double Random_6::nextDouble() {
	return rand() / (double)32277;
}


void chap6_Ex7() {
	Random_6::seed();

	for (int i = 0; i < 100; i++) {
		int randomInt = Random_6::nextInt(1, 100);
		std::cout << randomInt << " ";
	}
	std::cout << std::endl;


	for (int i = 0; i < 100; i++) {
		char randomAlphabet = Random_6::nextAlphabet();
		std::cout << randomAlphabet << " ";
	}
	std::cout << std::endl;


	for (int i = 0; i < 20; i++) {
		double randomDouble = Random_6::nextDouble();
		std::cout << randomDouble << " ";
	}
	std::cout << std::endl;
}