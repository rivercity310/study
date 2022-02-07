#include <iostream>
#include <string>

using namespace std;

/*
string 클래스를 이용하여 빈칸을 포함하는 문자열을 입력받고, 문자열에서 'a'의 개수를 출력하는 프로그램 작성
*/

void Ex3() {
	string inputStr;
	cout << "문자열 입력: ";
	getline(cin, inputStr, '\n');

	int startIndex = 0;
	int count = 0;
	while (true) {
		int fIndex = inputStr.find('a', startIndex);
		if (fIndex == -1) break;
		
		startIndex = fIndex + 1;
		count++;
	}

	cout << "a의 개수는 " << count << endl;
}