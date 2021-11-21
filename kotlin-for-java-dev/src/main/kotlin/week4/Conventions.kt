package week4

data class Contact(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val additionalData: String
)

fun main() {

    val contact = Contact("Office phone", "email@company.com", "+342-34-234", "")

    ///destructuring data class
    /// use an underscore to show that the value is ignored.
    val (name, email, phone, _) = contact
    println("Contact name => $name")
    println("Contact email => $email")
    println("Contact phone => $phone")
}