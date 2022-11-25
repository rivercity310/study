#include <iostream>
#include <sstream>
#include <string>
#include <unordered_map>

using namespace std;

class DS {
private:
	int* parent;
public:
	DS(int n) {
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;
	}
	
	~DS() {
		delete[] parent;
	}

	int find_parent(int x) {
		if (parent[x] != x)
			return find_parent(parent[x]);

		return x;
	}

	void union_parent(int a, int b) {
		int ra = find_parent(a);
		int rb = find_parent(b);

		if (ra < rb)
			parent[rb] = ra;
		else
			parent[ra] = rb;
	}
};

void boj_4195() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	
}