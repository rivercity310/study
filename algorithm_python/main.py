import sys
import math

input = sys.stdin.readline

n = int(input())

pt = []
for _ in range(n):
    x, y = map(float, input().split())
    pt.append((x, y))

grp = []
for i in range(n):
    x1, y1 = pt[i]
    for j in range(i + 1, n):
        x2, y2 = pt[j]

        weight = math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2))
        grp.append((i, j, weight))

grp.sort(key=lambda x: x[2])

parent = [i for i in range(len(grp) + 1)]


def fp(x):
    if parent[x] != x:
        parent[x] = fp(parent[x])
    return parent[x]


def up(a, b):
    ra = fp(a)
    rb = fp(b)

    if ra < rb:
        parent[rb] = ra
    else:
        parent[ra] = rb


ans = 0
for a, b, w in grp:
    if fp(a) != fp(b):
        up(a, b)
        ans += w

print("%.2f" % ans)
