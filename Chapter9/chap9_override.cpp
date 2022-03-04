#include <iostream>

class Base {
public:
	virtual void f() = 0;
};

class Derived : public Base {
public:
	virtual void f() { std::cout << "Derived::f()" << std::endl; }
};

void chap9_override() {
	Derived d;
	Derived* pDerived = &d;

	Base* pBase = pDerived;

	pBase->f();
	pDerived->f();
}