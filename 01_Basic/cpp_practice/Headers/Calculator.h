#ifndef CALCULATOR_H
#define CALCULATOR_H

class Operation {
protected:
	int a, b;
public:
	void setValue(int a, int b) { this->a = a; this->b = b; }
	virtual int calculate() = 0;
};

class Add : public Operation {
public:
	int calculate() override;
};

class Sub : public Operation {
public:
	int calculate() override;
};

class Mul : public Operation {
public:
	int calculate() override;
};

class Div : public Operation {
public:
	int calculate() override;
	double newCalc(int a, int b);
};

#endif