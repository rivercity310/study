import sys
import heapq

input = sys.stdin.readline

n = int(input())
table = []

for _ in range(n):
    s, t = map(int, input().split())
    table.append((s, t))

table.sort(key=lambda x: (x[0], x[1]))

pq = []
heapq.heappush(pq, table[0][1])

# pq[0] => peek
for i in range(1, n):
    if pq[0] <= table[i][0]:
        heapq.heappop(pq)
    heapq.heappush(pq, table[i][1])

print(len(pq))