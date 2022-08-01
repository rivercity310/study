#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

constexpr int MAX = 7;

int parent[MAX];
vector<pair<int, pair<int, int>>> edges;

int find_parent(int x) {
	if (parent[x] != x)
		return find_parent(parent[x]);
	return x;
}

void union_parent(int a, int b) {
	int root_a = find_parent(a);
	int root_b = find_parent(b);

	if (root_a < root_b)
		parent[b] = a;
	else
		parent[a] = b;
}

void kruskal_mst() {
	for (int i = 1; i < MAX; i++)
		parent[i] = i;

	int M;
	cin >> M;

	for (int i = 0; i < M; i++) {
		int a, b, cost;
		cin >> a >> b >> cost;

		edges.push_back({ cost, {a, b} });
	}

	std::sort(edges.begin(), edges.end());

	int result = 0;
	vector<pair<int, int>> lst;

	for (auto& p : edges) {
		int cost = p.first;
		int a = p.second.first;
		int b = p.second.second;

		if (find_parent(a) != find_parent(b)) {
			union_parent(a, b);

			result += cost;
			lst.push_back({ a, b });
		}
		else
			cout << a << " <--> " << b << " 사이클 발생!" << endl;
	}

	cout << "최소 비용 : " << result << "\n";
	cout << "연결된 정점\n";
	for (auto& p : lst)
		cout << "(" << p.first << ", " << p.second << ")\t";
}