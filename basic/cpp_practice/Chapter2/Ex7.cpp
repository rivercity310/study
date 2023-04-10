#include <iostream>
#include <cstring>

using namespace std;
/*
1. 정수 5칙 연산을 할 수 있는 프로그램 작성
2. 영문 텍스트를 입력받아 알파벳 히스토그램을 그리는 프로그램 작성
*/

void operation() {
	while (true) {
		cout << "? ";
		int first;
		string oper;
		int second;
		cin >> first >> oper >> second;
		
		int res;
		if (oper == "+") res = first + second;
		else if (oper == "-") res = first - second;
		else if (oper == "*") res = first * second;
		else if (oper == "/") res = first / second;
		else if (oper == "%") res = first % second;
		else {
			cout << "잘못된 입력입니다..." << endl;
			continue;
		}

		cout << first << " " << oper << " " << second << " = " << res << endl;
	}
}

void draw() {
	cout << "영문 텍스트를 입력하세요: ";
	char str[10000];
	cin.getline(str, 10000, ';');
	int alphaCount[26] = { 0, };
	
	for (int i = 0; i < strlen(str); i++) {
		if (isalpha(str[i]) > 0) {     // isalpha: 대문자 1, 소문자 2, 알파벳 아닌 것 0
			for (char c = 'a'; c <= 'z'; c++) {
				int k = tolower(str[i]);  // int tolower(int c): 아스키 기준으로 정수 반환 
				if ((char)k == c) alphaCount[k - 97]++;
			}
		}
	}
	
	for (char c = 'a'; c <= 'z'; c++) {
		cout << c << " (" << alphaCount[(int)c - 97] << ") : ";
		for (int i = 0; i < alphaCount[(int)c - 97]; i++) cout << "*";
		cout << endl;
	}
}