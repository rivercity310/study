#include <iostream>

class AbstractGate {
protected:
	bool x, y;
public:
	void set(bool x, bool y) { this->x = x; this->y = y; }
	virtual bool operation() = 0;
};

class ORGate : public AbstractGate {
public:
	bool operation() {
		if (x || y) return true;
		else return false;
	}
};

class ANDGate : public AbstractGate {
public:
	bool operation() {
		if (x && y) return true;
		else return false;
	}
};

class XORGate : public AbstractGate {
public:
	bool operation() {
		if (x != y) return true;
		else return false;
	}
};

void chap9_Ex5() {
	ANDGate andGate;
	ORGate orGate;
	XORGate xorGate;

	andGate.set(true, false);
	orGate.set(true, false);
	xorGate.set(true, false);

	std::cout.setf(std::ios::boolalpha);
	std::cout << andGate.operation() << std::endl;
	std::cout << orGate.operation() << std::endl;
	std::cout << xorGate.operation() << std::endl;
}