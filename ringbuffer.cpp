#include <iostream>

using namespace std;

class RingBuffer {
private:
	int front;
	int rear;
	int num;
	int max;
	int* q;

public:
	RingBuffer(int n) {
		this->front = this->rear = this->num = 0;
		this->max = n;
		q = new int[this->max];
	}

	~RingBuffer() {
		if (q)
			delete[] q;
	}

	bool Enque(int x) {
		if (num >= max)
			return false;
		else {
			num++;
			q[rear++] = x;

			if (rear == max)
				rear = 0;

			return true;
		}
	}

	bool Deque(int& x) {
		if (num <= 0)
			return false;
		else {
			num--;
			x = q[front++];

			if (front == max)
				front = 0;

			return true;
		}
	}

	bool Peek(int& x) {
		if (num <= 0) return false;

		x = q[front];
		return true;
	}

	int Capacity() {
		return max;
	}

	int Size() {
		return num;
	}

	bool isEmpty() {
		return num <= 0;
	}

	bool isFull() {
		return num >= max;
	}

	void Print() {
		for (int i = 0; i < num; i++)
			printf("%-5d", q[(i + front) % max]);
		putchar('\n');
	}

	int Search(int x) {
		int idx;
		for (int i = 0; i < num; i++)
			if (q[idx = (i + front) % max] == x)
				return idx;

		return -1;
	}
};

void ringbuffer() {
	int n;
	cin >> n;

	RingBuffer rb(n);

	while (!rb.isFull()) {
		int x;
		cin >> x;

		rb.Enque(x);
	}

	while (!rb.isEmpty()) {
		int x;
		rb.Deque(x);

		cout << "Deque : " << x << "\n";
	}
}