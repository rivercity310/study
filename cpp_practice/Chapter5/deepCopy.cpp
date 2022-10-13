#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>

using namespace std;

class Person {
	char* name;
	int id;
public:
	Person(int id, const char* name);     // 성성자
	Person(const Person& person);         // 복사 생성자
	~Person();
	void changeName(const char* name);
	void show() {
		cout << id << ", " << name << endl;
	}
};

Person::Person(int id, const char* name) {
	this->id = id;
	int len = strlen(name);
	this->name = new char[len + 1];  // name 문자열 공간 할당
	strcpy(this->name, name);        // name에 문자열 복사
}

// 깊은 복사 생성자
Person::Person(const Person& person) {
	this->id = person.id;
	int len = strlen(person.name);
	this->name = new char[len + 1];
	strcpy(this->name, person.name);
	cout << "복사 생성자 실행. 원본 객체의 이름 " << this->name << endl;
}

Person::~Person() {
	if (name)  // 만일 name에 동적 할당된 배열이 있으면
		delete[] name;
}

void Person::changeName(const char* name) {
	if (strlen(name) > strlen(this->name)) return;     // 현재 name에 할당된 메모리보다 긴 이름으로 변경 불가
	strcpy(this->name, name);
}

void deepCopy() {
	Person father(1, "Kitae");
	Person daughter(father);

	cout << "daughter 객체 생성 직후 ---- " << endl;
	father.show();
	daughter.show();

	daughter.changeName("Grace");
	cout << "daughter의 이름을 Grace로 변경 후 ---- " << endl;
	father.show();
	daughter.show();
}

// 값에 의한 호출로 객체가 전달되는 경우 person 객체의 복사 생성자 호출
void f(Person person) {
	person.changeName("dummy");
}

// 함수에서 객체를 리턴하는 경우, mother 객체의 복사본 생성, 복사본의 복사 생성자 호출
Person g() {
	Person mother(2, "Jane");
	return mother;
}

void impliedlyCall() {
	Person father(1, "Kitae");
	Person son = father;       // 객체로 초기화하여 객체가 생성될 때, son 객체의 복사 생성자 호출
	f(father);
	g();
}