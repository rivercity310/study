#include <iostream>
using std::string;

class Converter {
protected:
	double ratio;
	virtual double convert(double src) = 0;
	virtual string getSourceString() = 0;
	virtual string getDestString() = 0;
public:
	Converter(double ratio) { this->ratio = ratio; }
	void run() {
		double src;
		std::cout << getSourceString() << "을 " << getDestString() << "로 바꿉니다." << std::endl;
		std::cout << getSourceString() << " 입력: ";
		std::cin >> src;
		std::cout << "변환 결과: " << convert(src) << getDestString() << std::endl;
	}
};

class WonToDollar : public Converter {
protected:
	double convert(double src);
	string getSourceString() { return "원"; }
	string getDestString() { return "달러"; }
public:
	WonToDollar(double ratio) : Converter(ratio) {}
};

double WonToDollar::convert(double src) {
	return src / ratio;
}

void chap9_Ex1() {
	WonToDollar wd(1010);  // 1달러 = 1010원
	wd.run();
}


class KmToMile : public Converter {
protected:
	double convert(double src);
	string getSourceString() { return "km"; }
	string getDestString() { return "mile"; }
public:
	KmToMile(double ratio) : Converter(ratio) {};
};

double KmToMile::convert(double src) {
	return src / ratio;
}

void chap9_Ex2() {
	KmToMile toMile(1.609344);
	toMile.run();
}