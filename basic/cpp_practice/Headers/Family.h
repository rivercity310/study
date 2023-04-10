#pragma once
#include "Person.h"

class Family {
private:
	Person* p;   // Person 배열 포인터
	int size;    // Person 배열의 크기 (가족 구성원 수)
	string familyName;
public:
	Family(string familyName, int size);
	~Family();
	string getFamilyName() { return this->familyName; }
	void show();     // 모든 가족 구성원 출력
	void setInfo(int idx, string name, string tel);
};