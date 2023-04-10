#include <iostream>
#include <string>
#include "Family.h"
#include "Person.h"

using namespace std;

Family::Family(string familyName, int size) {
	this->familyName = familyName;
	this->size = size;
	p = new Person[size];
}

Family::~Family() {
	delete[] p;
}

void Family::setInfo(int idx, string name, string tel) {
	(p + idx)->set(name, tel);
}

void Family::show() {
	cout << familyName << " 가족은 " << size << "명 입니다." << endl;
	for (int i = 0; i < size; i++)
		cout << (p + i)->getName() << '\t' << (p + i)->getTel() << endl;
}

void Ex10() {
	Family* simpson = new Family("Simpson", 3);
	
	simpson->setInfo(0, "Mr. Simpson", "010-1111-1111");
	simpson->setInfo(1, "Mrs. Simpson", "010-2222-2222");
	simpson->setInfo(2, "Bart Simpson", "010-3333-3333");
	
	simpson->show();
	delete simpson;
}