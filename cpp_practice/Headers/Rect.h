// #pragma once

#ifndef RECT_H
#define RECT_H

class Rect {
private:
	int width, height;
public:
	Rect();
	Rect(int w, int h);
	~Rect();
	void setWidth(int w);
	void setHeight(int h);
	int getArea();
};

#endif