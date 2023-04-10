import org.example.CustomWebApplicationServer

// GET /calculate?operand=11&operator=+&operand2=22
fun main(args: Array<String>) {
    CustomWebApplicationServer(8080).start()
}