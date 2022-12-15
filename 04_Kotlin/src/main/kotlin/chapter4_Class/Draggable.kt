package chapter4_Class

interface Draggable {
    fun drag(b: Boolean) = println("Now i'm ${if (b) "can" else "can't"} draggable")
    fun showOff() = println("I'm Draggable")
    fun sayHi()
}