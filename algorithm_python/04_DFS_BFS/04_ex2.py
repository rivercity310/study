from collections import deque


# 미로 탈출
# 0 -> 벽, 1 -> 이동 가능
# (0, 0) 위치에서 (N, M) 위치까지의 최단 거리 => bfs를 이용하여 구할 수 있다.


# 입력 받기
N, M = map(int, input().split())

graph = []
for i in range(N):
    graph.append(list(map(int, input())))

# 이동할 네 방향을 방향 벡터로 정의 (상, 하, 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


# bfs 소스코드 구현
def bfs(x, y):
    queue = deque()
    queue.append((x, y))

    while queue:
        x, y = queue.popleft()

        # 현재 위치에서 네 방향으로 위치 확인
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 공간 벗어난 경우 continue
            if nx < 0 or ny < 0 or nx >= N or ny >= M:
                continue

            # 벽인 경우 무시
            if graph[nx][ny] == 0:
                continue

            # 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx, ny))

    # 가장 오른쪽 아래까지의 최단 거리 반환
    return graph[N - 1][M - 1]


print(bfs(0, 0))
print(graph)
