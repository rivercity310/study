#pragma once

#include <string>

using namespace std;

class Player {
private:
	string name;
public:
	void setName(string name);
	string getName();
};

class GamblingGame {
private:
	Player* players;
public:
	GamblingGame();
	~GamblingGame();
	void initPlayers();
	void run();
};

