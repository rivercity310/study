#include <iostream>
using std::string;

class Printer {
	string model, menufacturer;
	int printedCount, availableCount;
protected:
	Printer(string model, string menufacturer) {
		this->model = model;
		this->menufacturer = menufacturer;
		this->printedCount = 0;
	}
	string getModel() { return this->model; }
	string getMenufacturer() { return this->menufacturer; }
	int getAvailableCount() { return this->availableCount; }
	void setAvailableCount(int availableCount) { this->availableCount = availableCount; }
};

class InkjetPrinter : public Printer {
	int availableInk;
public:
	InkjetPrinter(string model, string menufacturer, int availableInk) : Printer(model, menufacturer) {
		this->availableInk = availableInk;
		setAvailableCount(10);
	}
	void printInkjet(int pages);
	void show() {
		std::cout << "잉크젯: " << getModel() << ", " << getMenufacturer() << ", 남은 종이 " << getAvailableCount();
		std::cout << " 남은 잉크 " << this->availableInk << std::endl;
	};
};

void InkjetPrinter::printInkjet(int pages) {
	if (getAvailableCount() >= pages && availableInk >= pages) {
		setAvailableCount(getAvailableCount() - pages);
		availableInk -= pages;
		std::cout << "프린트하였습니다." << std::endl;
	}
	else {
		std::cout << "용지 또는 잉크가 부족합니다." << std::endl;
	}
}

class LaserPrinter : public Printer {
	int availableToner;
public:
	LaserPrinter(string model, string menufacturer, int availableToner) : Printer(model, menufacturer) {
		this->availableToner = availableToner;
		setAvailableCount(10);
	}
	void printLaser(int pages);
	void show() {
		std::cout << "잉크젯: " << getModel() << ", " << getMenufacturer() << ", 남은 종이 " << getAvailableCount();
		std::cout << " 남은 토너 " << this->availableToner << std::endl;
	};
};

void LaserPrinter::printLaser(int pages) {
	if (getAvailableCount() >= pages && availableToner >= pages / 2) {
		setAvailableCount(getAvailableCount() - pages);
		availableToner -= pages / 2;
		std::cout << "프린트하였습니다." << std::endl;
	}
	else {
		std::cout << "용지 또는 잉크가 부족합니다." << std::endl;
	}
}


void chap8_Ex8() {
	InkjetPrinter InkPrinter("Officejet V40", "HP", 10);
	LaserPrinter LaserPrinter("SCX-6x45", "Samsung Electronics", 20);
	
	std::cout << "현재 작동중인 2대의 프린터는 아래와 같다." << std::endl;
	InkPrinter.show();
	LaserPrinter.show();

	while (true) {
		std::cout << "프린터 (1: 잉크젯, 2: 레이저)와 매수 입력: ";
		int selected, pages;
		std::cin >> selected >> pages;

		switch (selected) {
		case 1:
			InkPrinter.printInkjet(pages);
			break;
		case 2:
			LaserPrinter.printLaser(pages);
			break;
		default:
			std::cout << "잘못된 입력입니다" << std::endl;
			break;
		}
		InkPrinter.show();
		LaserPrinter.show();

		char retry;
		std::cout << "계속 프린트 하시겠습니까?(y/n): ";
		std::cin >> retry;
		if (retry == 'n') break;
	}
}
