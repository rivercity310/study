import heapq

N = 6
M = 11
start = 1

INF = int(1e9)
'''
graph = [
    [],
    [(2, 2), (3, 5), (4, 1)],
    [(3, 3), (4, 2)],
    [(2, 3), (6, 5)],
    [(3, 3), (5, 1)],
    [(3, 1), (6, 2)],
    []
]
'''
graph = [[] for _ in range(N + 1)]
graph[1] = [(2, 2), (3, 5), (4, 1)]
graph[2] = [(3, 3), (4, 2)]
graph[3] = [(2, 3), (6, 5)]
graph[4] = [(3, 3), (5, 1)]
graph[5] = [(3, 1), (6, 2)]

distance = [INF] * (N + 1)


def dijkstra(start):
    q = []
    distance[start] = 0
    heapq.heappush(q, (0, start))

    while q:
        now_dist, now_vertex = heapq.heappop(q)

        if distance[now_vertex] < now_dist:
            continue

        for end_vertex, weight in graph[now_vertex]:
            cost = now_dist + weight

            if distance[end_vertex] > cost:
                distance[end_vertex] = cost
                heapq.heappush(q, (cost, end_vertex))


dijkstra(3)
for i in range(1, N + 1):
    val = -1 if distance[i] == INF else distance[i]
    print("%-5d" % val, end=" ")
print()


def floyd_warshall(N):
    for k in range(1, N + 1):
        for a in range(1, N + 1):
            for b in range(1, N + 1):
                fw_graph[a][b] = min(
                    fw_graph[a][b], fw_graph[a][k] + fw_graph[k][b])


fw_graph = [[INF] * (N + 1) for _ in range(N + 1)]

for i in range(0, len(graph)):
    for j in range(0, len(graph[i])):
        col, weight = graph[i][j]
        fw_graph[i][col] = weight

for a in range(1, N + 1):
    for b in range(1, N + 1):
        if a == b:
            fw_graph[a][b] = 0

floyd_warshall(N)

for i in range(1, N + 1):
    for j in range(1, N + 1):
        val = -1 if fw_graph[i][j] == INF else fw_graph[i][j]
        print("%-5d" % val, end=" ")
    print()
