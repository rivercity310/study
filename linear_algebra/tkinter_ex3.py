from tkinter import *

if __name__ == "__main__":
    def show():
        print("이름: %s\n나이: %s" % (e1.get(), e2.get()))

    def clear01():
        e1.delete(0, END)  # END: 파이썬 상수, 문자열 길이 자동 계산

    def clear02():
        e2.delete(0, END)

    def copy():
        e2.insert(0, e1.get())

    def neon():
        text = e2.get()
        size = len(text)

        e2.delete(0)
        e2.insert(size, text[0])

    window = Tk()

    Label(window, text="이름: ").grid(row=0)
    Label(window, text="나이: ").grid(row=1)

    e1 = Entry(window)
    e2 = Entry(window)

    e1.grid(row=0, column=1)
    e2.grid(row=1, column=1)
    e1.focus_set()

    Button(window, text="보이기", command=show).grid(
        row=2, column=1, sticky=W, pady=4
    )

    Button(window, text="종료", command=window.destroy).grid(
        row=2, column=0, sticky=W, pady=4
    )

    Button(window, text="삭제(e1)", command=clear01).grid(
        row=2, column=2, sticky=W, pady=4
    )

    Button(window, text="삭제(e2)", command=clear02).grid(
        row=2, column=3, sticky=W, pady=4
    )

    Button(window, text="복사(e1->e2)", command=copy).grid(
        row=2, column=4, sticky=W, pady=4
    )

    Button(window, text="Neon", command=neon).grid(
        row=2, column=5, sticky=W, pady=4
    )

    window.mainloop()
