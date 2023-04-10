#include <iostream>
#include <string>

using namespace std;

/*
1. 이름, 나이, 주소를 입력받고 다시 출력
2. 문자열을 하나 입력받고 부분 문자열을 출력
*/

void printInfo() {
	string name;
	string address;
	string age;

	cout << "이름? ";
	getline(cin, name);
	cout << "주소? ";
	getline(cin, address);
	cout << "나이? ";
	getline(cin, age);

	cout << name + ", " + address + ", " + age + "세" << endl;
}

void printSubStr() {
	string inputStr;
	cout << "문자열 입력: ";
	getline(cin, inputStr);

	for (int i = 0; i < inputStr.size(); i++)
		cout << inputStr.substr(0, i + 1) << endl;
}
