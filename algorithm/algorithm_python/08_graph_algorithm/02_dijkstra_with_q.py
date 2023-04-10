import heapq

N = 6
M = 11
start = 1

INF = int(1e9)

graph = [
    [],
    [(2, 2), (3, 5), (4, 1)],
    [(3, 3), (4, 2)],
    [(2, 3), (6, 5)],
    [(3, 3), (5, 1)],
    [(3, 1), (6, 2)],
    []
]

distance = [INF] * (N + 1)


def dijkstra(start):
    q = []

    # 시작 노드로 가기 위한 최단 경로를 0으로 설정하여 우선순위 큐에 삽입
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        # 현재 노드가 이미 처리된 노드라면 무시 (방문처리 배열 사용X)
        if distance[now] < dist:
            continue

        for end_vertex, weight in graph[now]:
            cost = distance[now] + weight

            # 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
            if cost < distance[end_vertex]:
                distance[end_vertex] = cost
                heapq.heappush(q, (cost, end_vertex))


dijkstra(start)
print(distance)
