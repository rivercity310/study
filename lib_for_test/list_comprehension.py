a = [i for i in range(10)]
print(a)

b = list(i for i in range(10))
print(b)

c = list(range(10))
print(c)

d = [i for i in range(10) if i % 2 == 0]
print(d)

chk = [1, 7, 8]
e = list(i for i in range(10) if i not in chk)
print(e)

n = 3
m = 4
f = [[0] * m for _ in range(n)]
print(f)

g = list(e[1:5])
print(g)
