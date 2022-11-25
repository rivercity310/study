#include <iostream>

class BaseIO {
public:
	int mode;
};

class In : virtual public BaseIO {
public:
	int readPos;
};

class Out : virtual public BaseIO {
public:
	int writePos;
};

class InOut : public In, public Out {
public:
	bool safe;
};

void chap8_virtual() {
	InOut ioObj;

	// mode가 하나이므로 모호성 해결
	ioObj.mode = 10;    
}