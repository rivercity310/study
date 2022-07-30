import sys

# [ 간단한 다익스트라 알고리즘 = O(V^2) ]

input = sys.stdin.readline
INF = int(1e9)         # 무한을 의미하는 값으로 10억을 설정

# 노드의 개수, 간선의 개수 입력받기
N, M = map(int, input().rstrip().split())

# 시작 노드 번호 입력받기
start = int(input().rstrip())

# 각 노드에 연결되어 있는 노드에 대한 정보를 담는 리스트
graph = [[] for i in range(N + 1)]

# 방문 체크
visited = [False] * (N + 1)

# 거리 테이블
distance = [INF] * (N + 1)

# 모든 간선 정보 입력받기
for _ in range(M):
    a, b, c = map(int, input().rstrip().split())

    # a번 노드에서 b번 노드로 가는 비용이 c
    graph[a].append((b, c))

# 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호 반환


def get_smallest_node():
    min_value = INF
    index = 0       # 가장 최단 거리가 짧은 노드(인덱스)

    for i in range(1, N + 1):
        if distance[i] < min_value and not visited[i]:
            min_value = distance[i]
            index = i

    return index

# 다익스트라


def dijkstra(start):
    # 시작 노드에 대해 초기화 작업 수행
    distance[start] = 0
    visited[start] = True
    for j in graph[start]:
        distance[j[0]] = j[1]

    # 시작 노드를 제외한 전체 N -1개 노드에 대한 반복 수행
    for i in range(N - 1):
        # 현재 최단 거리가 가장 짧은 노드를 꺼내, 방문 처리
        now = get_smallest_node()
        visited[now] = True

        # 현재 노드와 연결된 다른 노드 확인
        for j in graph[now]:
            cost = distance[now] + j[1]

            # 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
            if cost < distance[j[0]]:
                distance[j[0]] = cost


dijkstra(start)

# 모든 노드로 가기 위한 최단 거리 출력
for i in range(1, N + 1):
    if distance[i] == INF:
        print("INFINITY")
    else:
        print(distance[i])
