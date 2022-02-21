#include <iostream>
#include <string>
using std::string;

class Product {
	int id, price;
	string intro, manufacturer;
protected:
	Product(int id, int price, string intro, string manufacturer) {
		this->id = id;
		this->price = price;
		this->intro = intro;
		this->manufacturer = manufacturer;
		std::cout << "Product 생성자 실행" << std::endl;
	}
	string getIntro() { return this->intro; }
	string getManu() { return this->manufacturer; }
	int getPrice() { return this->price; }
	int getID() { return this->id; }
public:
	virtual void show() = 0;
};

class Book : public Product {
	int ISBN;
	string author, title;
public:
	Book(int id, int price, string intro, string manufacturer, string author, string title, int ISBN) : Product(id, price, intro, manufacturer) {
		this->author = author;
		this->title = title;
		this->ISBN = ISBN;
		std::cout << "Book 생성자 실행" << std::endl;
	}
	void show() override;
};

void Book::show() {
	std::cout << "--- 상품ID : " << getID() << "\n";
	std::cout << "상품 설명: " << getIntro() << "\n";
	std::cout << "생산자: " << getManu() << "\n";
	std::cout << "가격: " << getPrice() << "\n";
	std::cout << "저자: " << author << "\n";
	std::cout << "책제목: " << title << "\n";
	std::cout << "ISBN: " << ISBN << std::endl;
}

class ConversationBook : public Book {
	string lang;
public:
	ConversationBook(int id, int price, string intro, string manufacturer, string author, string title, int ISBN, string lang) : Book(id, price, intro, manufacturer, author, title, ISBN) {
		this->lang = lang;
		std::cout << "ConversationBook 생성자 실행" << std::endl;
	}

	void show() override;
};

void ConversationBook::show() {
	Book::show();
	std::cout << "언어: " << lang << std::endl;
}

class CompactDisc : public Product {
	string albumTitle, singer;
public:
	CompactDisc(int id, int price, string intro, string manufacturer, string albumTitle, string singer) : Product(id, price, intro, manufacturer) {
		this->albumTitle = albumTitle;
		this->singer = singer;
		std::cout << "CompactDisc 생성자 실행" << std::endl;
	}
	void show() override;
};

void CompactDisc::show() {
	std::cout << "--- 상품ID : " << getID() << "\n";
	std::cout << "상품 설명: " << getIntro() << "\n";
	std::cout << "생산자: " << getManu() << "\n";
	std::cout << "가격: " << getPrice() << "\n";
	std::cout << "앨범 제목: " << albumTitle << "\n";
	std::cout << "가수: " << singer << std::endl;
}

void chap8_openchallenge() {
	string intro, manufacturer, author, title, lang, albumTitle, singer;
	int price, ISBN;
	int id = 0;

	Product* products[100];

	std::cout << "**** 상품 관리 프로그램을 작동합니다 ****" << std::endl;
	while (true) {
		std::cout << "상품 추가(1), 모든 상품 조회(2), 끝내기(3) : ";
		int select; std::cin >> select;

		switch (select) {
		case 1:
		{
			std::cout << "삼품 종류 >> 책(1), 음악 CD(2), 회화책(3): ";
			int kinds; std::cin >> kinds;
			std::cin.ignore();

			switch (kinds) {
			case 1:
			{
				// (int id, int price, string intro, string manufacturer, string author, string title, int ISBN)
				std::cout << "상품 설명: "; std::getline(std::cin, intro);
				std::cout << "생산자: "; std::getline(std::cin, manufacturer);
				std::cout << "가격: "; std::cin >> price; std::cin.ignore();
				std::cout << "책 제목: "; std::getline(std::cin, title); 
				std::cout << "저자: "; std::getline(std::cin, author);
				std::cout << "ISBN: "; std::cin >> ISBN;

				Book* book = new Book(id, price, intro, manufacturer, author, title, ISBN);
				products[id++] = book;
				break;
			}
			case 2:
			{
				//(int id, int price, string intro, string manufacturer, string albumTitle, string singer)
				std::cout << "상품 설명: "; std::getline(std::cin, intro);
				std::cout << "생산자: "; std::getline(std::cin, manufacturer);
				std::cout << "가격: "; std::cin >> price; std::cin.ignore();
				std::cout << "앨범 제목: "; std::getline(std::cin, albumTitle);
				std::cout << "가수: "; std::getline(std::cin, singer);

				CompactDisc* CD = new CompactDisc(id, price, intro, manufacturer, albumTitle, singer);
				products[id++] = CD;
				break;
			}
			case 3:
			{
				//int id, int price, string intro, string manufacturer, string author, string title, int ISBN, string lang)
				std::cout << "상품 설명: "; std::getline(std::cin, intro);
				std::cout << "생산자: "; std::getline(std::cin, manufacturer);
				std::cout << "가격: "; std::cin >> price; std::cin.ignore();
				std::cout << "책 제목: "; std::getline(std::cin, title);
				std::cout << "저자: "; std::getline(std::cin, author);
				std::cout << "ISBN: "; std::cin >> ISBN; std::cin.ignore();
				std::cout << "언어: "; std::getline(std::cin, lang);

				ConversationBook* CB = new ConversationBook(id, price, intro, manufacturer, author, title, ISBN, lang);
				products[id++] = CB;
				break;
			}
			default:
				std::cout << "잘못된 선택입니다.." << std::endl;
				break;
			}


			break;
		}
		case 2:
			for (int i = 0; i < id; i++) products[i]->show();
			std::cout << std::endl;
			break;
		case 3:
			std::cout << "프로그램을 종료합니다... " << std::endl;
			return;
		default:
			std::cout << "잘못된 선택입니다..." << std::endl;
			break;
		}
	}
}