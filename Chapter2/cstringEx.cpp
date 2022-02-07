#include <iostream>
#include <cstring>

using namespace std;

/*
정확한 암호를 입력받으면 종료하는 프로그램
*/

void correctPassword() {
	char password[11];     // 영어 10자, 한글 5자 저장 가능
	cout << "프로그램을 종료하려면 암호를 입력하세요" << endl;
	
	while (true) {
		cout << "암호: ";
		cin.getline(password, 11);

		// strcmp: 주어진 두 문자열이 같은지 비교, 같으면 0을 return한다. 
		if (strcmp(password, "C ++") == 0) {
			cout << "프로그램을 정상 종료합니다." << endl;
			break;
		}
		else {
			cout << "암호가 틀립니다!" << endl;
		}
	}
}