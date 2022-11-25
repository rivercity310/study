#include <iostream>
#include <cstring>
using namespace std;


/*
문자열 a에서 문자 c를 찾아, 문자 c가 있는 공간에 대한 참조를 리턴하고 success에 true 설정
만일 c를 찾을 수 없다면 success 참조 매개 변수에 false를 설정
*/
char& find(char* a, char c, bool& success) {
	for (int i = 0; i < strlen(a); i++) {
		if (a[i] == c) {
			success = true;
			return a[i];
		}
	}

	success = false;
	return a[c];
}

void Ex6() {
	char s[] = "Mike";
	bool b = false;
	char& loc = find(s, 'M', b);

	if (!b) {
		cout << "M을 발견할 수 없음" << endl;
		return;
	}
	loc = 'S';
	cout << s << endl;
}