#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct {
	int src;
	int dst;
	int weight;
} Edge;

vector<Edge> grp;

class Disjoint_Set {
private:
	int* parent;

public:
	Disjoint_Set(int n) { 
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;
	}
	~Disjoint_Set() { delete[] parent; }

	int fp(int x) {
		if (parent[x] != x)
			return fp(parent[x]);
		return x;
	}

	void up(int a, int b) {
		int ra = fp(a);
		int rb = fp(b);

		if (ra < rb) parent[rb] = ra;
		else parent[ra] = rb;
	}
};

int kruskal() {
	Disjoint_Set dset(grp.size());
	int rst = 0;
	
	for (Edge& e : grp) {
		if (dset.fp(e.src) != dset.fp(e.dst)) {
			dset.up(e.src, e.dst);
			rst += e.weight;
		}
	}

	return rst;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int v, e;
	cin >> v >> e;

	
	for (int i = 0; i < e; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		grp.push_back(Edge{ a, b, c });
	}

	std::sort(grp.begin(), grp.end(), [](const auto& a, const auto& b) {
		return a.weight < b.weight;
		});

	cout << kruskal();
}