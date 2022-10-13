n = input()

col = ord(n[0]) - 96
row = int(n[1])

# L, U, R, D 순서 각 두쌍
move = [(-1, -2), (1, -2), (-2, -1), (-2, 1), (-1, 2), (1, 2), (2, -1), (2, 1)]

knight = [row, col]
cnt = 0
for mv in move:
    tmp = [knight[0] + mv[0], knight[1] + mv[1]]
    if tmp[0] > 0 and tmp[0] < 9 and tmp[1] > 0 and tmp[1] < 9:
        cnt += 1

print(cnt)
