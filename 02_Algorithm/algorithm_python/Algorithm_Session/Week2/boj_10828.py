import sys

input = sys.stdin.readline

class Stack:
    def __init__(self):
        self.stk = []
        self.tos = 0

    def push(self, x):
        self.stk.append(x)
        self.tos += 1

    def pop(self):
        if self.tos == 0:
            return -1
        
        self.tos -= 1
        return self.stk[self.tos]

    def size(self):
        return self.tos

    def empty(self):
        return 1 if self.tos == 0 else 0
    
    def top(self):
        if self.tos == 0:
            return -1
        return self.stk[self.tos - 1]

n = int(input())
stk = Stack()
ans = ""

for _ in range(n):
    s = input().split(" ")
    op = s[0]
    
    if op == "push":
        stk.push(int(s[1]))
    elif op == "top":
        ans += str(stk.top())
    elif op == "pop":
        ans += str(stk.pop())
    elif op == "size":
        ans += str(stk.size())
    elif op == "empty":
        ans += str(stk.empty())

    if op != "push":
        ans += "\n"

print(ans)