n = int(input())

cnt = 1
rst = n

while True:
    x1 = rst // 10
    x2 = rst % 10
    y1 = (x1 + x2) // 10
    y2 = (x1 + x2) % 10

    rst = x2 * 10 + y2
    if rst == n:
        break
    cnt += 1

print(cnt)
