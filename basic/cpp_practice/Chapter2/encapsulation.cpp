#include <iostream>

using namespace std;

class Phone {
	void call();
	void receive();
};

class MobilePhone : public Phone {
	void connectWireless();
	void recharge();
};

class MusicPhone : public MobilePhone {
	void downloadMusic();
	void play();
};