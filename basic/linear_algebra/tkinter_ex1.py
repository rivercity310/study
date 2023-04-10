# tkinter: 파이썬으로 GUI 개발할 때 필요한 모듈

from tkinter import *

'''
[배치 관리자]
1. 격차 배치 관리자(grid): 위젯을 그리드 형태 배치
2. 압축 배치 괸리자(pack): 위젯을 가로나 세로로 붙여서 배치
3. 절대 배치 괸리자(place): 사용자가 배치 위치를 직접 지정
'''

if __name__ == "__main__":
    window = Tk()

    '''
    [label 속성]
    text, fg, bg, padx, pady, width, height, image (Tkinter.PhotoImage 함수 이용)
    photo = PhotoImage(file=path)
    '''

    label01 = Label(window, text="Hello World!", fg="red", bg="blue")
    label01.pack()   # pack: label을 부모 위젯(컨테이너 위젯) window에 압축배치

    label02 = Label(window, text="Hello World!", fg="blue", bg="red")
    label02.pack()   # pack: label을 부모 위젯(컨테이너 위젯) window에 압축배치

    button01 = Button(window, text="MyBtn")
    button01.pack()

    button02 = Button(window, text="MyBtn2", fg="green", bg="red")
    button02.pack(side=LEFT, padx=10)

    # 동적으로 속성 변경하기
    button01["text"] = "One"
    button02["text"] = "Two"

    window.mainloop()  # 부모 위젯이 이벤트를 기다리는 루프
