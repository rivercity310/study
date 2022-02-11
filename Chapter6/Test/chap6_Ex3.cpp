#include <iostream>

class Bigger {
public:
	static int big(int x, int y, int max = 100);
};

int Bigger::big(int x, int y, int max) {
	int bigger = x > y ? x : y;

	if (bigger > max) return max;
	else return bigger;
}

void chap6_Ex3() {
	int x = Bigger::big(3, 5);
	int y = Bigger::big(300, 60);
	int z = Bigger::big(30, 60, 50);

	std::cout << x << " " << y << " " << z << std::endl;
}