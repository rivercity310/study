#include <iostream>

using std::string;

class Statistics {
private:
	int* data;
	int size;
public:
	Statistics();
	~Statistics();
	bool operator!();
	void operator~();
	Statistics& operator<<(int a);
	void operator>>(int &avg);
};

Statistics::Statistics() {
	size = 0;
	data = new int[100];
}

Statistics::~Statistics() {
	std::cout << "동적 메모리 해제" << std::endl;
	delete[] data;
}

Statistics& Statistics::operator<<(int a) {
	*(data + size) = a;
	size++;

	return *this;
}

void Statistics::operator~() {
	for (int i = 0; i < size; i++)
		std::cout << *(data + i) << " ";

	std::cout << std::endl;
}

bool Statistics::operator!() {
	if (!data) return false;
	else return true;
}

void Statistics::operator>>(int &avg) {
	for (int i = 0; i < size; i++)
		avg += *(data + i);

	avg = avg / size;
}

void chap7_Ex10() {
	Statistics stat;
	if (!stat) std::cout << "현재 통계 데이터가 없습니다." << std::endl;

	int x[5];
	std::cout << "5개의 정수 입력: ";
	for (int i = 0; i < 5; i++) std::cin >> x[i];

	for (int i = 0; i < 5; i++) stat << x[i];
	stat << 100 << 200;
	~stat;

	int avg = 0;
	stat >> avg; 
	std::cout << "avg = " << avg << std::endl;
}