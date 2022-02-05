#include <iostream>
#include <cstring>

using namespace std;

/*
한 라인에 ';'을 기준으로 5개의 이름을 구분하여 입력받아,
각 이름을 끊어내어 화면에 출력하고 가장 긴 이름을 판별하라.

C-스트링 사용
- strlen
- strcopy_s
- cin.getline
*/

void nameInput() {
	cout << "5명의 이름을 ';'으로 구분하여 입력하세요" << endl;
	char name[100];
	char longName[100];
	
	int A = 0;
	for (int i = 0; i < 5; i++) {
		cin.getline(name, 100, ';');
		cout << i + 1 << " : " << name << endl;

		if (A <= strlen(name)) {
			A = strlen(name);
			strcpy_s(longName, name);
		}
	}

	cout << "가장 긴 이름은 " << longName;
}
