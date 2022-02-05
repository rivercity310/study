#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>

using namespace std;

/*
string 클래스를 이용하여 영문 한 줄을 입력받고 글자 하나만 랜덤하게 수정하여 출력하는 프로그램 작성
*/

char randomAlphabet[26];

void Ex5() {
	// randomAlphabet 초기화
	int index = 0;
	for (char c = 'a'; c <= 'z'; c++) 
		randomAlphabet[index++] = c;

	cout << "아래에 영문 한 줄을 입력하세요 (exit을 입력하면 종료합니다)" << endl;
	while (true) {
		string inputStr;
		getline(cin, inputStr, '\n');
		if (inputStr == "exit") break;

		srand(time(NULL));
		int randomAlphabetIndex = rand() % 26;
		int randomIndex = rand() % inputStr.size();

		// 알파벳이 아닌 문자가 인덱스로 뽑힌 경우 제외
		while (true) {
			char c = inputStr[randomIndex];
			if (isalpha(c) == 1 || isalpha(c) == 2) break;
			
			randomIndex = rand() % inputStr.size();
		}

		cout << "랜덤 알파벳 인덱스: " << randomAlphabetIndex << endl;
		cout << "랜덤 인덱스: " << randomIndex << endl;

		inputStr[randomIndex] = randomAlphabet[randomAlphabetIndex];
		cout << inputStr << endl;
	}
}