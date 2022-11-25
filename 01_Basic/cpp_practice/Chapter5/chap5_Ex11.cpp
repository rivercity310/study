#include <iostream>
#include <cstring>
#include <string>
using namespace std;

class Book {
	string title;    // 책 제목
	int price;
public:
	Book(string title, int price);
	//~Book();
	void set(string title, int price);
	void show() { cout << title << ", " << price << "원 " << endl; }
};

Book::Book(string title, int price) {
	set(title, price);
}

void Book::set(string title, int price) {
	this->title = title;
	this->price = price;
}

/* [ C 스트링 사용 ]
Book::Book(const char* title, int price) {
	this->price = price;
	int size = strlen(title) + 1;
	this->title = new char[size];
	strcpy(this->title, title);
}


[ 컴파일러가 삽입하는 디폴트 복사 생성자(얕은 복사) ]
Book::Book(const Book& b) {
	this->title = b.title;
	this->price = b.price;
}

[ 깊은 복사 생성자(C - 스트링) ]
Book::Book(const Book& b) {
	this->price = price;
	int size = strlen(b.title) + 1;
	this->title = new char[size];
	strcpy(this->title, b.title);
}


Book::~Book() {
	if (this->title) delete[] title;
	cout << title << " 소멸!" << endl;
}

void Book::set(char* title, int price) {
	if (this->title) delete[] this->title;
	this->price = price;
	int size = strlen(title) + 1;
	this->title = new char[size];
	strcpy(this->title, title);
}
*/

void Ex11() {
	Book cpp("명품C++", 10000);
	Book java = cpp;       // 복사 생성자 호출
	java.set("명품자바", 12000);
	cpp.show();
	java.show();
}