import sys
import heapq

# [ 전보 ]

input = sys.stdin.readline

N, M, C = map(int, input().split())
INF = int(1e9)

distance = [INF] * (N + 1)
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    X, Y, Z = map(int, input().split())
    graph[X].append((Y, Z))


def dijk(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        now_dist, now_vertex = heapq.heappop(q)

        if distance[now_vertex] < now_dist:
            continue

        for end_vertex, weight in graph[now_vertex]:
            cost = now_dist + weight

            if distance[end_vertex] > cost:
                distance[end_vertex] = cost
                heapq.heappush(q, (cost, end_vertex))


dijk(C)

cnt = 0
time = 0

for dist in distance:
    if dist == 0 or dist == INF:
        continue

    time = max(time, dist)
    cnt += 1

print(cnt, time)
