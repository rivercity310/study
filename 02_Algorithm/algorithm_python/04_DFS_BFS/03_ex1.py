# 음료수 얼려먹기

N, M = map(int, input().split())

# 2차원 리스트의 맵 정보 입력받기
graph = []
for _ in range(N):
    graph.append(list(map(int, input())))

print(graph)


# DFS로 특정한 노드를 방문한 뒤에 연결된 모든 노드들 방문
def dfs(x, y):

    # 주어진 범위를 벗어나는 경우 즉시 종료
    if x <= -1 or x >= N or y <= -1 or y >= M:
        return False

    # 현재 노드를 아직 방문하지 않았다면
    if graph[x][y] == 0:
        # 해당 노드 방문처리
        graph[x][y] = 1

        # 상, 하, 좌, 우 모두 재귀적으로 호출
        dfs(x - 1, y)
        dfs(x, y - 1)
        dfs(x + 1, y)
        dfs(x, y + 1)

        return True

    return False


# 모든 노드에 대해 음료수 채우기
result = 0
for i in range(N):
    for j in range(M):
        if dfs(i, j):
            result += 1

print(result)
