input_str = input()

sum = 0
new_str = ""
for ch in input_str:
    if ch.isalpha():
        new_str += ch

    if ch.isdigit():
        sum += int(ch)

print("".join(sorted(new_str)) + str(sum))

'''
[문자열 정렬 방법]
sorted(str) : 정렬된 문자 리스트를 반환한다
따라서 join 함수를 같이 사용하여 sorted에서 반환된 문자 리스트를
문자열로 합쳐서 변환할 수 있다.
'''
