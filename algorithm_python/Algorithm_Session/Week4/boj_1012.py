import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

def dfs(graph, i, j):
    if i < 0 or j < 0 or i > len(graph) or j > len(graph[i]):
        return False

    if graph[i][j] == 1:
        graph[i][j] = 0

        dfs(graph, i + 1, j)
        dfs(graph, i - 1, j)
        dfs(graph, i, j + 1)
        dfs(graph, i, j - 1)

        return True
    
    return False


t = int(input())

while t:
    cols, rows, k = map(int, input().split())
    graph = [[0] * (cols + 1) for _ in range(rows + 1)]

    for _ in range(k):
        x, y = map(int, input().split())
        graph[y][x] = 1

    cnt = 0
    for i in range(rows):
        for j in range(cols):
            if dfs(graph, i, j):
                cnt += 1
    
    print(cnt)

    t -= 1