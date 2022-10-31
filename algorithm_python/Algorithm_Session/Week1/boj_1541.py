# 잃어버린 괄호

# Input ex) 10+55-50+40-30+10    =>    최솟값: 10+55-(50+40)-(30+10)

a = input().split("-")      # a = ["10+55", "50+40", "30+10"]
plus = a[0]                 # plus = "10+55"          
minus = a[1:]               # minus = ["50+40", "30+10"]
ans = 0

for p in plus.split("+"):   # p <- ["10", "55"]
    ans += int(p)

for m in minus:                 
    for v in m.split("+"):      # i = 0, v <- ["50", "40"]
        ans -= int(v)           # i = 1, v <- ["30", "10"]  

print(ans)
