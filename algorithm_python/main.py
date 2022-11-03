import heapq

N = 6

graph = [
    [],
    [(2, 2), (3, 5), (4, 1)],
    [(3, 3), (4, 2)],
    [(2, 3), (6, 5)],
    [(3, 3), (5, 1)],
    [(3, 1), (6, 2)],
    [],
]

INF = int(1e9)
distance = [INF] * (N + 1)

def dijkstra(start):
    pq = []

    distance[start] = 0
    heapq.heappush(pq, (0, start))

    while pq:
        dist, now = heapq.heappop(pq)

        if distance[now] < dist:
            continue

        for end_vertex, weight in graph[now]:
            cost = distance[now] + weight

            if distance[end_vertex] > cost:
                distance[end_vertex] = cost
                heapq.heappush(pq, (cost, end_vertex))

dijkstra(1)
print(distance[1:])