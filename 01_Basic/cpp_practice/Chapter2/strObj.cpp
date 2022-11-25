#include <iostream>
#include <string>
using namespace std;

void strObj() {
	// 1. 빈 문자열을 가진 스트링 객체 생성
	string str;

	// 2. address의 문자열을 복사한 copyAddress 스트링 객체 생성
	string address("서울시 노원구 상계 1동");
	string copyAddress(address);

	// 3. C-스트링을 복사한 스트링 객체 생성
	char text[] = { 'l', 'o', 'v', 'e', ' ', 'C', '+', '+', '\0' };
	string title(text);
	string title2(text, 3);


	// 스트링 출력
	cout << str << endl;
	cout << address << endl;
	cout << copyAddress << endl;
	cout << title << endl;
	cout << title2 << endl;     // lov 출력 (3개만 복사)
	cout << address.size() << endl;
}

void rotateStr() {
	string s;
	cout << "문자열 입력 (한글 X)" << endl;
	getline(cin, s, '\n');
	
	for (int i = 0; i < s.length(); i++) {
		string first = s.substr(0, 1);
		string sub = s.substr(1, s.length() - 1);
		s = sub + first;

		cout << s << endl;
	}
}

void strAdd() {
	string s;
	cout << "덧셈 문자열을 입력하세요" << endl;
	getline(cin, s, '\n');

	int sum = 0;
	int startIndex = 0;    // 문자열 내에 검색할 시작 인덱스
	while (true) {
		int fIndex = s.find('+', startIndex);   // '+' 문자 검색
	
		// '+' 문자가 없으면
		if (fIndex == -1) {
			string part = s.substr(startIndex);
			if (part == "") break;
			cout << part << endl;
			sum += stoi(part);
			break;
		}

		int count = fIndex - startIndex;   // 서브스트링으로 자를 문자 개수
		string part = s.substr(startIndex, count);

		cout << part << endl;
		sum += stoi(part);
		startIndex = fIndex + 1;
	}

	cout << "숫자들의 합은 " << sum << endl;
}

void replaceStr() {
	string s;
	cout << "여러 줄의 문자열 입력, 끝은 &문자" << endl;
	getline(cin, s, '&');
	cin.ignore();    // '&' 뒤에 따라오는 <Enter> 키를 제거하기 위한 코드

	string f, r;
	cout << endl << "find: ";
	getline(cin, f, '\n');   // 검색할 문자열 입력
	cout << "replace: ";
	getline(cin, r, '\n');   // 대치할 문자열 입력

	int startIndex = 0;
	while (true) {
		int fIndex = s.find(f, startIndex);
		if (fIndex == -1) break;

		s.replace(fIndex, f.length(), r);
		startIndex = fIndex + r.length();
	}

	cout << s << endl;
}
