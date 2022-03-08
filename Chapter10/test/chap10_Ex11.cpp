#include <iostream>
#include <vector>
#include <string>

using std::cout;
using std::endl;
using std::cin;
using std::string;
using std::vector;

class Book {
	int year;
	string bookName;
	string author;
public:
	Book(int year, string bookName, string author) {
		this->year = year;
		this->bookName = bookName;
		this->author = author;
	}

	string getAuthor() { return this->author; }
	int getYear() { return this->year; }

	void showInfos() {
		cout << year << "년도, " << bookName << ", " << author << endl;
	}
};

class BookManager {
	vector<Book> v;
public:
	void run();
	void addBook();
	void search();
	void search_name();
	void search_year();
};

void BookManager::addBook() {
	while (true) {
		int year;
		string bookName, author;
		cout << "년도: "; cin >> year;
		if (year == -1) {
			cout << "총 입고된 책은 " << v.size() << "권 입니다." << endl;
			break;
		}
		cin.ignore();
		cout << "책이름: "; std::getline(cin, bookName);
		cout << "저자: "; std::getline(cin, author);

		v.push_back(Book(year, bookName, author));
		cout << "***** 입력이 완료되었습니다! *****" << endl;
		cout << endl;
	}
}

void BookManager::search() {
	while (true) {
		cout << "검색 서비스입니다.." << endl;
		cout << "이름(1), 연도(2): ";
		int input; cin >> input;
		cin.ignore();

		switch (input) {
		case 1:
			BookManager::search_name();
			break;
		case 2:
			BookManager::search_year();
			break;
		default:
			cout << "잘못된 입력입니다..." << endl;
			break;
		}
	}
}

void BookManager::search_name() {
	cout << "검색하고자 하는 저자 이름을 입력하세요: ";
	string name; std::getline(cin, name);

	for (int i = 0; i < v.size(); i++)
		if (v[i].getAuthor() == name) v[i].showInfos();
}

void BookManager::search_year() {
	cout << "검색하고자 하는 연도를 입력하세요: ";
	int year; cin >> year;

	for (int i = 0; i < v.size(); i++)
		if (v[i].getYear() == year) v[i].showInfos();
}

void BookManager::run() {
	cout << "입고할 책을 입력하세요. 년도에 -1을 입력하면 종료..." << endl;
	BookManager::addBook();
	BookManager::search();
}

void chap10_Ex11() {
	BookManager* BM = new BookManager();
	BM->run();

	delete BM;
}