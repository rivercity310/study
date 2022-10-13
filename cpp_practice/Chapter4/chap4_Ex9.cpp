#include <iostream>
#include <string>

#include "Person.h"

using namespace std;

void Ex9() {
	cout << "전화번호부를 생성합니다... " << endl;
	cout << "몇명을 저장하시겠어요? ";
	int length;
	cin >> length;

	Person* personPtr = new Person[length];
	for (int i = 0; i < length; i++) {
		string name, tel;
		cout << "사람 " << i << " >> ";
		cin >> name >> tel;

		(personPtr + i)->set(name, tel);
	}

	cout << "모든 사람의 이름은 ";
	for (int i = 0; i < length; i++) {
		cout << (personPtr + i)->getName() << " ";
	}
	cout << endl;

	cout << "전화번호를 검색합니다. 이름은? ";
	string searching;
	cin >> searching;
	for (int i = 0; i < length; i++)
		if ((personPtr + i)->getName() == searching)
			cout << "전화번호는 " << (personPtr + i)->getTel() << endl;


	delete[] personPtr;
}