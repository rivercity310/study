from itertools import permutations

n, m = map(int, input().split())

ans = list(permutations(range(1, n + 1), m))

for v in ans:
    print(*v)