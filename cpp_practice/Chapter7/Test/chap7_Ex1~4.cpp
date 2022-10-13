#include <iostream>
#include <string>
using std::string;

class Book {
	string title;
	int price, pages;
public:
	Book(string title = "", int price = 0, int pages = 0);
	Book(const Book& book);
	void show();
	string getTitle() { return title; }
	Book& operator+=(int increase);
	Book& operator-=(int decrease);
	bool operator==(int price);
	bool operator==(string title);
	bool operator==(Book& book);
	bool operator>(string title);
	bool operator!();
};

Book::Book(string title, int price, int pages) {
	this->title = title;
	this->price = price;
	this->pages = pages;
}

Book::Book(const Book& book) {
	std::cout << "복사 생성자 호출" << std::endl;
	this->title = book.title;
	this->price = book.price;
	this->pages = book.pages;
}

void Book::show() {
	std::cout << title << " " << price << "원 " << pages << "페이지" << std::endl;
}

Book& Book::operator+=(int increase) {
	this->price += increase;
	return *this;
}

Book& Book::operator-=(int decrease) {
	this->price -= decrease;
	return *this;
}

bool Book::operator==(int price) {
	if (this->price == price) return true;
	else return false;
}

bool Book::operator==(string title) {
	if (this->title == title) return true;
	else return false;
}

bool Book::operator==(Book& book) {
	if (this->title == book.title && this->price == book.price && this->pages == book.pages) return true;
	else return false;
}

bool Book::operator!() {
	if (this->price == 0) return true;
	else return false;
}

bool Book::operator>(string title) {
	bool res = this->title > title;
	if (res) return true;
	else return false;
}

void chap7_Ex1() {
	Book a("청춘", 20000, 300), b("미래", 30000, 500);
	a += 500;    // 책 a의 가격 500원 증가
	b -= 500;    // 책 b의 가격 500원 감소
	a.show();
	b.show();
}

void chap7_Ex2() {
	// 다음 조건을 만족시키는 연산자 함수 작성
	Book a("명품 C++", 30000, 500), b("고품 C++", 30000, 500);
	Book c = a;
	
	if (a == 30000) std::cout << "정가 30000원" << std::endl;   // price 비교
	if (a == "명품 C++") std::cout << "명품 C++ 입니다." << std::endl;   // title 비교
	if (a == c) std::cout << "두 책이 같습니다." << std::endl;   // 모두 비교
}

void chap7_Ex3() {
	// 가격이 0원이면 true를 리턴하는 ! 연산자 작성
	
	Book book("벼룩시장", 0, 50);  
	if (!book) std::cout << "공짜다!" << std::endl;
}

void chap7_Ex4() {
	// 책의 제목을 사전 순으로 비교하는 < 연산자 작성
	Book a("청춘", 20000, 300);
	string b;
	getline(std::cin, b);
	if (a > b)
		std::cout << a.getTitle() << "이 " << b << "보다 뒤에 있구나!" << std::endl;
	else
		std::cout << a.getTitle() << "이 " << b << "보다 앞에 있구나!" << std::endl;

	if ("a" > "b") std::cout << true << std::endl;
}