a = input().upper()

cnt = [0] * 26

for char in a:
    cnt[ord(char) - 65] += 1

if cnt.count(max(cnt)) > 1:
    print("?")
else:
    print(chr(cnt.index(max(cnt)) + 65))
