fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) {
        return false;
    }

    ///Implement the function that checks whether a string is a valid identifier.
    // A valid identifier is a non-empty string that starts with a letter or underscore and consists of only letters, digits and underscores.

    for ((index, element) in s.withIndex()) {
        if (index == 0) {
            if (!((element in 'a'..'z') || (element == '_'))) {
                return false;
            }
        } else if (!(element in 'a'..'z' || element in '0'..'9')) {
            return false;
        }
    }

    return true;
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}