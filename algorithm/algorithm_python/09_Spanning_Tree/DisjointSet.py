# [ Disjoint - Set ]
# 서로소 집합은 무방향 그래프 내에서의 사이클을 판별할 때 사용한다. (MST 등)
# 신장 트리 : 그래프에서 모든 노드를 포함하면서 사이클이 존재하지 않는 부분 그래프
# 각 간선에 연결된 두 노드의 루트 노드를 확인하여, 루트 노드가 같다면 사이클 발생

# 참고로 방향 그래프에서 사이클 여부는 DFS를 이용하여 판별할 수 있다.


# 특정 원소가 속한 집합 찾기
def find_parent_old(x):
    # 루트 노드를 찾을 때까지 재귀 호출
    if parent[x] != x:
        return find_parent(parent[x])
    return x


# find_parent 함수 개선 : 경로 압축 기법
def find_parent(x):
    # 루트 노드를 찾을 때까지 재귀 호출
    if parent[x] != x:
        parent[x] = find_parent(parent[x])

    return parent[x]


# 두 원소가 속한 집합 합치기
def union_parent(a, b):
    a = find_parent(a)
    b = find_parent(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


# 노드의 개수와 간선의 개수 입력받고 부모 테이블 초기화
v, e = map(int, input().split())
parent = [0] * (v + 1)


# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i

cycle = False

# Union 연산을 각각 수행
for _ in range(e):
    a, b = map(int, input().split())

    # 사이클 발생 여부 체크
    if find_parent(a) == find_parent(b):
        cycle = True
        break

    else:
        union_parent(a, b)

# 각 원소가 속한 집합 출력하기
print("각 원소가 속한 집합: ", end="")
for i in range(1, v + 1):
    print(find_parent(i), end=" ")
print()


# 부모 테이블 내용 출력
print("부모 테이블: ", end="")
for i in range(1, v + 1):
    print(parent[i], end=" ")
