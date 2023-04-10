import sys
from collections import deque

# Router

input = sys.stdin.readline

n = int(input())
router = deque()

while True:
    k = int(input())
    
    if k == -1:
        break

    elif k == 0:
        router.popleft()
        continue

    elif k > 0 and len(router) < n:
        router.append(k)

if len(router) == 0:
    print("empty")
else:
    for val in router:
        print(val, end=" ")
