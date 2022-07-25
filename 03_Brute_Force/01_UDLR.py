# 책 풀이
n = int(input())
x, y = 1, 1
plans = input().split()

print(f"plans: {plans}")

# L, R, U, D에 따른 이동 방향
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ["L", "R", "U", "D"]

# 이동 계획을 하나씩 확인
for plan in plans:
    # 이동 후 좌표 구하기
    for i in range(len(move_types)):
        if plan == move_types[i]:
            nx = x + dx[i]
            ny = y + dy[i]

    # 공간을 벗어나는 경우 무시
    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue

    # 이동 수행
    x, y = nx, ny

print(x, y)


'''
import sys

N = int(sys.stdin.readline().rstrip())
move = list(map(str, sys.stdin.readline().rstrip().split()))

# direction[0]: 좌우, direction[1]: 상하
direction = [0, 0]

for ch in move:
    if ch == 'U':
        if direction[1] > 0:
            direction[1] -= 1
    elif ch == 'D':
        if direction[1] < N:
            direction[1] += 1
    elif ch == 'L':
        if direction[0] > 0:
            direction[0] -= 1
    elif ch == 'R':
        if direction[0] < N:
            direction[0] += 1

    print(f"{ch} 이동합니다 => {direction}")

direction[0] = N - direction[0] + 1
direction[1] = N - direction[1] + 1
print(direction)
'''
