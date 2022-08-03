# [ Kruskal Algorithm ] : O(ElogE)
# 대표적인 최소 신장 트리 알고리즘 (그리디 알고리즘으로 분류)
# 동작 과정은 다음과 같다.
'''
1. 간선 데이터를 비용에 따라 오름차순으로 정렬
2. 간선을 하나씩 확인하여 현재의 간선이 사이클을 발생시키는지 확인
    2 - 1. 사이클이 발생하지 않는 경우 MST에 포함시킨다.
    2 - 2. 사이클이 발생하는 경우 해당 간선을 무시한다.
3. 모든 간선에 대해 2번 과정을 반복한다.
'''

'''
class Edge:
    def __init__(self, src, dst, weight):
        self.src = src
        self.dst = dst
        self.weight = weight
'''


def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]


def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


v, e = map(int, input().split())
parent = [0] * (v + 1)
for i in range(1, v + 1):
    parent[i] = i

# 모든 간선을 담을 리스트와 최소 비용을 담을 변수
edges = []
result = 0


# 간선 정보 입력받기
for _ in range(e):
    a, b, cost = map(int, input().split())
    # 비용순으로 정렬하기 위해 튜플의 첫번쨰 원소를 비용으로 설정
    edges.append((cost, a, b))

edges.sort()

for edge in edges:
    cost, a, b = edge

    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        result += cost

print(result)
