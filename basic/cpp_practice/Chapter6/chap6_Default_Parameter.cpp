#include <iostream>
using namespace std;

//void msg(string name = "Ã¶¼ö", string sayHi = "hi everyone");

void msg(string name = "Ã¶¼ö", string sayHi = "hi everyone") {
	cout << name << "´Ô: " << sayHi << endl;
}

void DP() {
	msg();
	msg("½Â¼ö");
	msg("¼ö¿¬", "¾È³ç ¾êµé¾Æ");
}

class MyVector {
	int* p;
	int size;
public:
	/*
	MyVector() {
		p = new int[100];
		size = 100;
	}
	MyVector(int n) {
		p = new int[n];
		size = n;
	}
	*/
	MyVector(int n = 100) {
		p = new int[n];
		size = n;
	}
	~MyVector() { delete[] p; }
};

