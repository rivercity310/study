from tkinter import *


if __name__ == "__main__":
    def show():
        name = entry01.get()
        age = entry02.get()

        if (name == ""):
            print("이름을 입력하세요.")
            entry01.focus()
            return

        if (age == ""):
            print("나이를 입력하세요.")
            entry02.focus()
            return

        print("이름: %s\n나이: %s" % (name, age))

        # text 위젯 내용 지우기
        entry01.delete(0, "end")
        entry02.delete(0, "end")

    window = Tk()

    # 엔트리(Entry): 한 줄로 값 입력받기
    Label(window, text="이름", bg="green").grid(row=0)
    Label(window, text="나이", bg="yellow").grid(row=1)

    entry01 = Entry(window)
    entry02 = Entry(window)

    entry01.grid(row=0, column=1)
    entry02.grid(row=1, column=1)

    Button(window, text="보이기", command=show).grid(
        row=2, column=1, sticky=W, pady=4)
    Button(window, text="종료", command=window.destroy).grid(
        row=2, column=0, sticky=W, pady=4)

    window.mainloop()
