#include <iostream>
#include <string>    // string 클래스를 사용하기 위한 헤더파일

using namespace std;

/*
해당 노래를 부른 가수의 이름을 묻는 프로그램 코드
string으로 문자열을 입력받기 위해 C++ 표준 라이브러리에서 지원하는 전역 함수
getline(istream&, string&)을 사용하였다.
*/

void Quiz() {
	string song("Falling in love with you");
	string elvis("Elvis presley");
	string singer;

	cout << song + "를 부른 가수는? ";
	cout << "(힌트: 첫글자는" << elvis[0] << ") ";
	getline(cin, singer);

	if (singer == elvis) cout << "정답!";
	else cout << "틀렸습니다. 정답은 " << elvis + "입니다." << endl;
}
