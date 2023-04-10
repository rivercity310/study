from collections import deque

n, k = map(int, input().split())

q = deque()
for i in range(1, n + 1):
    q.append(i)

print("<", end="")
for i in range(n):

    for __ in range(k - 1):
        q.append(q.popleft())

    print(q.popleft(), end="")
    if i < n - 1:
        print(", ", end="")

print(">") 