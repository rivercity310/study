# 파이썬 내장 정렬 라이브러리 => 최악의 경우에도 O(NlogN) 보장

# [ 1. sorted ]
# 별도의 정렬된 객체를 반환 (원본 객체 정렬 x)
# set{}, dict{}, tuple() 자료형을 입력받아도 리스트로 반환한다.
arr = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

result = sorted(arr)
print(f"arr = {arr}")
print(f"result = {result}")

arr2 = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8}
result = sorted(arr2)
print(f"arr2 = {arr2}")
print(f"result = {result}")


# [ 2. sort ]
# 별도의 정렬된 리스트를 반환하지 않고 내부 원소를 바로 정렬한다.
arr.sort()
print(f"arr.sort() 후 => {arr}")


# [ 3. key 속성 ]
# sorted()나 sort()에서 key 매개변수에 정렬의 기준이 되는 함수를 전달할 수 있다.
# 람다 형태로 작성하면 좋다.

arr3 = [('바나나', 2), ('사과', 5), ('당근', 3)]
result = sorted(arr3, key=lambda x: x[1])
print(f"result = {result}")
