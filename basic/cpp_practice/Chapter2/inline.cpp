#include <iostream>

using namespace std;

class Person {
private:
	string name;
public:
	Person() { this->name = "홍길동"; }
	~Person() {
		cout << this->name << " 소멸" << endl;;
	}

	// 멤버 함수를 클래스 선언부에 직접 구현
	// 컴파일러에 의해 inline으로 자동 처리됨
	void setName(string c) { this->name = c; }
	string getName() { return this->name; }

	void introduce();
};

// inline 선언
inline void Person::introduce() {
	cout << "안녕하세요! 제 이름은 " << this->name << "입니다." << endl;;
}

/*
int main() {
	Person p1;
	p1.introduce();

	p1.setName("황승수");
	p1.introduce();
}
*/