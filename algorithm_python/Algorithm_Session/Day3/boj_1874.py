import sys
from collections import deque

# 스택 수열

input = sys.stdin.readline

n = int(input())
q = deque()
bp = 0
flag = True
s = ""

while n:
    k = int(input())
    
    if k > bp:
        for i in range(bp + 1, k + 1):
            q.append(i)
            s += "+\n";  
        bp = k

    if q[-1] != k:
        flag = False
        break

    q.pop()
    s += "-\n";
    n -= 1

if flag:
    print(s)
else:
    print("NO")