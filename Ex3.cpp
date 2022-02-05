#include <iostream>
#include <string>
using namespace std;

/*
1. <Enter>키가 입력될 때까지 문자들을 읽고, 입력된 문자 'x'의 개수를 출력
2. 문자열 두개를 입력받고 같으면 같습니다, 다르면 다릅니다 출력
*/

void checkX() {
	cout << "문자들을 입력하라 (100개 미만)\n";
	char buf[100];
	cin.getline(buf, 100, '\n');

	int count = 0;
	for (int i = 0; i < 100; i++) 
		if (buf[i] == 'x') count++;
	
	cout << "x의 개수는 " << count << endl;
}

void inputStr() {
	string str1;
	cout << "새 암호를 입력하세요: ";
	getline(cin, str1);

	string str2;
	cout << "새 암호를 다시 한번 입력하세요: ";
	getline(cin, str2);

	if (str1 == str2) cout << "같습니다.\n";
	else cout << "다릅니다.\n";
}