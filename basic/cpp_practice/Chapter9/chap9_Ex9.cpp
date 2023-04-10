#include <iostream>

using std::cout;
using std::endl;
using std::string;

class Printer {
protected:
	string model, manufacturer;
	int printedCount, availableCount;
public:
	Printer(string model, string manufacturer, int availableCount) {
		this->model = model;
		this->manufacturer = manufacturer;
		this->availableCount = availableCount;
	}
	virtual bool print(int pages) = 0;
	virtual void show() = 0;
};

class InkjetPrinter : public Printer {
	int availableInk;
public:
	InkjetPrinter(string model, string manufacturer, int availableCount, int availableInk)
		: Printer(model, manufacturer, availableCount) {
		this->availableInk = availableInk;
	}
	bool print(int pages) {
		if (availableInk >= pages && availableCount >= pages) {
			availableCount -= pages;
			availableInk -= pages;
			return true;
		}
		
		return false;
	}

	void show() {
		cout << "잉크젯 : " << model << ", " << manufacturer << ", 남은 종이 " <<
			availableCount << "장, " << "남은 잉크 " << availableInk << endl;
	}
};

class LaserPrinter : public Printer {
	int availableToner;
public:
	LaserPrinter(string model, string manufacturer, int availableCount, int availableToner)
		: Printer(model, manufacturer, availableCount) {
		this->availableToner = availableToner;
	}

	bool print(int pages) {
		if (availableToner >= pages && availableCount >= pages) {
			availableCount -= pages;
			availableToner--;
			return true;
		}

		return false;
	}

	void show() {
		cout << "레이저 : " << model << ", " << manufacturer << ", 남은 종이 " <<
			availableCount << "장, " << "남은 토너 " << availableToner << endl;
	}
};

void chap9_Ex9() {
	Printer* p[2];
	p[0] = new InkjetPrinter("Officejet V40", "HP", 5, 10);
	p[1] = new LaserPrinter("SCX-6x45", "삼성전자", 3, 20);

	string retry;
	
	cout << "현재 작동중인 2대의 프린터는 다음과 같다." << endl;
	for (int i = 0; i < 2; i++) p[i]->show();
	while (true) {
		cout << "프린터(1:잉크젯, 2:레이저)와 매수 입력 >> ";
		int pSelect, pages;
		std::cin >> pSelect >> pages;

		if (p[pSelect - 1]->print(pages)) {
			cout << "프린트 하였습니다" << endl;
		}
		else {
			cout << "용지가 부족합니다.." << endl;
		}
		for (int i = 0; i < 2; i++) p[i]->show();

		cout << "계속 프린트 하시겠습니까? (y/n) >> ";
		std::cin >> retry;

		if (retry != "y") {
			cout << "프로그램을 종료합니다" << endl;
			return;
		}
	}
	
}