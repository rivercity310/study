#include <iostream>
using namespace std;

/*
복사 생성자의 매개 변수는 자기 클래스에 대한 참조로 선언한다.

디폴트 복사 생성자의 경우 얕은 복사가 일어나기 때문에 포인터 타입의 멤버 변수가 있을 경우
공유의 문제가 발생할 수 있다.

따라서 그런 경우에는 복사 생성자를 작성하여 깊은 복사가 일어나게 만들어주어야 한다.
*/

class Person {
private:
	char* name;
	int id;

public:
	Person(int id, const char* name);
	
	Person(const Person& person);
	
	~Person();
	
	void changeName(const char* name);

	void show() {
		cout << id << ", " << name << endl;
	}
};

Person::Person(int id, const char* name) {
	this->id = id;
	int len = strlen(name) + 1;
	this->name = new char[len];
	strcpy_s(this->name, len, name);
}

Person::Person(const Person& person) {
	this->id = person.id;
	int len = strlen(person.name) + 1;
	this->name = new char[len];
	strcpy_s(this->name, len, person.name);
	cout << "복사 생성자 실행! 원본 객체의 이름: " << this->name << endl;
}

Person::~Person() {
	if (name) delete[] name;
}

void Person::changeName(const char* name) {
	if (strlen(name) > strlen(this->name)) return;
	strcpy_s(this->name, strlen(name) + 1, name);
}

void copy_constructor() {
	Person father(1, "Kitae");
	Person daughter(father);        // 객체 복사 생성 -> 복사 생성자 호출

	cout << "daughter 객체 생성 직후 --- " << endl;
	father.show();
	daughter.show();

	daughter.changeName("Grace");
	cout << "daughter 이름 변경 후 --- " << endl;
	father.show();
	daughter.show();

	Person newPerson = daughter;	// 객체 복사 생성 -> 복사 생성자 호출
	daughter.show();
	newPerson.show();
	newPerson.changeName("chged");

	cout << "복사된 생성자 이름 변경 후" << endl;
	daughter.show();
	newPerson.show();
}