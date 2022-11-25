# 책 풀이


n, m = map(int, input().split())

# 방문한 위치를 저장하기 위한 맵 생성 및 초기화
d = [[0] * m for _ in range(n)]

# 현재 캐릭터의 좌표, 방향 입력받고, 현재 좌표 방문처리
x, y, direction = map(int, input().split())
d[x][y] = 1

# 전체 맵 정보 입력받기
arr = []
for i in range(n):
    arr.append(list(map(int, input().split())))

# 북, 동, 남, 서 방향 정의
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


# 왼쪽으로 회전시키는 함수
def turn_left():
    global direction
    direction -= 1
    if direction == -1:
        direction = 3


# 시뮬레이션 시작
count = 1
turn_time = 0

while True:
    turn_left()
    nx = x + dx[direction]
    ny = y + dy[direction]

    # 회전한 이후 정면에 가보지 않은 칸이 존재하면 이동한다
    if d[nx][ny] == 0 and arr[nx][ny] == 0:
        d[nx][ny] = 1
        x = nx
        y = ny
        count += 1
        turn_time = 0
        continue

    else:
        turn_time += 1

    # 네 방향 모두 갈 수 없으면
    if turn_time == 4:
        nx = x - dx[direction]
        ny = y - dy[direction]

        # 뒤로 갈 수 있으면 이동한다
        if arr[nx][ny] == 0:
            x = nx
            y = ny

        # 뒤가 막혀있으면 종료
        else:
            break

        turn_time = 0

print(count)
