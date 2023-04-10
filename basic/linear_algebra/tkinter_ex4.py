from tkinter import *

if __name__ == "__main__":
    def add():
        s1 = int(num01.get())
        s2 = int(num02.get())

        result["text"] = s1 + s2

    def sub():
        s1 = int(num01.get())
        s2 = int(num02.get())

        result["text"] = s1 - s2

    def mul():
        s1 = int(num01.get())
        s2 = int(num02.get())

        result["text"] = s1 * s2

    def div():
        s1 = int(num01.get())
        s2 = int(num02.get())

        result["text"] = s1 / s2

    calc = Tk()
    calc.title("Calculator")
    calc.geometry("160x100")

    Label(calc, text="수 - 1").grid(row=0)
    Label(calc, text="수 - 2").grid(row=1)
    Label(calc, text="결과").grid(row=2)

    num01 = Entry(calc, width=20)
    num01.grid(row=0, column=1)

    num02 = Entry(calc, width=20)
    num02.grid(row=1, column=1)

    result = Label(calc, relief="raised", width=17, bd=2, bg="green")
    result.grid(row=2, column=1)

    Button(calc, text="+", width=2, command=add).place(x=30, y=70)
    Button(calc, text="-", width=2, command=sub).place(x=60, y=70)
    Button(calc, text="*", width=2, command=mul).place(x=90, y=70)
    Button(calc, text="/", width=2, command=div).place(x=120, y=70)

    calc.mainloop()
