package pl.edu.pb.todoapp

enum class Category(val label: String) {
    Studies("Studies"),
    Home("Home");

    override fun toString(): String {
        return label
    }
}