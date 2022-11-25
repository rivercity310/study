import sys

# 방문 판매원 문제
# 입력받은 그래프에서 1 -> K -> X로 가는 최단 경로의 값 구하기


# N은 노드의 개수, M은 간선의 개수
input = sys.stdin.readline

N, M = map(int, input().split())
INF = int(1e9)


# 그래프를 입력받고 초기화하는 함수
def init_graph():
    grp = [[INF] * (N + 1) for _ in range(N + 1)]

    for _ in range(M):
        a, b = map(int, input().split())

        grp[a][b] = 1
        grp[b][a] = 1

    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if i == j:
                grp[i][j] = 0

    return grp


# Floyd-Warshall 알고리즘을 수행하는 함수
def floyd_warshall():
    grp = init_graph()

    for k in range(1, N + 1):
        for a in range(1, N + 1):
            for b in range(1, N + 1):
                grp[a][b] = min(grp[a][b], grp[a][k] + grp[k][b])

    return grp


graph = floyd_warshall()

# 그래프 출력
for i in range(1, N + 1):
    for j in range(1, N + 1):
        print("%-10d" % graph[i][j], end=" ")
    print()
print()

X, K = map(int, input().split())

distance = graph[1][K] + graph[K][X]

val = -1 if distance >= INF else distance
print(val)
