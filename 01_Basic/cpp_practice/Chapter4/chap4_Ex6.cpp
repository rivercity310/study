#include <iostream>
#include <string>

using namespace std;

/*
Ex6: string 클래스를 이용하여 영문 한 줄을 입력받고 거꾸로 출력하는 프로그램 작성
*/

void Ex6() {
	cout << "영문 한 줄을 입력하세요" << endl;
	string input;
	getline(cin, input, '\n');

	/* 회전하는 글자
	for (int i = 1; i <= input.size(); i++) {
		string first = input.substr(0, i);
		string remains = input.substr(i, input.size());

		string res = remains + first;
		cout << res << endl;
	}
	*/
	
	string res1, res2, res3;
	for (int i = 0; i < input.size(); i++) {
		char k = input[input.length() - i - 1];
		res1.push_back(k);           
		res2.append(1, k);           
		res3.insert(i, 1, k);        
	}

	/*
	[ char -> string ]
	1. push_back()
	2. append()
	3. insert(문자(열)을 넣을 위치 인덱스, 삽입하려는 문자 개수, char || string)
	*/

	cout << res1 << endl;
	cout << res2 << endl;
	cout << res3 << endl;
}