import sys

input = sys.stdin.readline

# 미해결
def bsh(arr, h, m):
    pl = 0
    pr = len(arr) - 1

    while (pl <= pr):
        pc = int((pl + pr) / 2)
        rst = 0
        for i in range(pc, len(arr)):
            rst += arr[i] - h

        print(f"rst: {rst}")

        if rst == m:
            return h
        elif rst < m:
            pl = pc - 1
        else:
            pr = pc + 1

    return -1

n, m = map(int, input().split())

arr = list(map(int, input().split()))

arr.sort()

ans = bsh(arr, 10, m)

