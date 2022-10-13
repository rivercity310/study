# N x M
N, M = map(int, input().split())

# [x, y, direction] : 0 -> 북, 1 -> 동, 2 -> 남, 3 -> 서
data = list(map(int, input().split()))
crt = data[0:2]
direction = data[2] - 1

# 0 -> 육지, 1 -> 바다
field = []
for _ in range(N):
    tmp = list(map(int, input().split()))
    field.append(tmp)

# 가보지 않은 곳을 True, 갈 수 없는 곳 or 이미 방문한 곳 False
chk = [[1] * M for _ in range(N)]
chk[crt[0]][crt[1]] = 0

for i in range(N):
    for j in range(M):
        if (field[i][j] == 1):
            chk[i][j] = 0

# 방문한 칸 수
cnt = 1

# 이동 방향 [북, 서, 남, 동]
move = [(-1, 0), (0, -1), (1, 0), (0, 1)]

# 이동 수행
direction_count = 1

while True:
    # 현재 바라보면 방향 기준 왼쪽 방향
    if direction < 0:
        direction = 3

    # 현재 기준 왼쪽 방향으로 임시 이동 체크
    move_direction = move[direction]
    tmp = [crt[0] + move_direction[0], crt[1] + move_direction[1]]

    # 맵 체크
    # 해당 방향(바라보는 방향의 왼쪽 방향)으로 이동할 수 있다면
    if chk[tmp[0]][tmp[1]] == 1:
        cnt += 1
        crt = tmp
        chk[tmp[0]][tmp[1]] = 0

    # 이동할 수 없으면 방향 회전
    else:
        direction = direction - 1
        direction_count += 1
        if direction < 0:
            direction = 3

        if direction_count == 4:
            break

print(cnt)
