#pragma once
#include <string>

using namespace std;

class Histogram {
private:
	string written;
public:
	Histogram(string str);
	void put(string putStr);
	void putc(char putChar);
	void print();
};
