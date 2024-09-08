import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ExtensionFieldsAlternativesUnitTest {


    private fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.capitalize() }

    private val String.firstChar: Char
        get() = this.first()

    class Person(var name: String, var age: Int)

    val Person.details: String
        get() = "Name: ${this.name}, Age: ${this.age}"
    
    var Person.address: String
        get() = "Address is not set"
        set(value) {
            println("Cannot store the address directly: $value")
        }

    interface PersonDecorator {
        var address: String
        var person: Person
        fun getDetails(): String
    }

    class PersonWithAddress(override var person: Person) : PersonDecorator {
        private var _address: String = ""

        override var address: String
            get() = _address
            set(value) {
                _address = value
            }

        override fun getDetails(): String {
            return "Name: ${person.name}, Age: ${person.age}, Address: $address"
        }
    }

    @Test
    fun `test using properties`() {
        val person = Person("Hangga Aji Sayekti", 35)

        assertEquals("Name: Hangga Aji Sayekti, Age: 35", person.details)

        person.address = "Jln. Kemasan Kotagede"
        assertEquals("Address is not set", person.address)
    }

    @Test
    fun `test using decorator`() {
        val person = Person("Hangga Aji Sayekti", 35)

        val personWithAddress = PersonWithAddress(person)

        personWithAddress.address = "Jln. Kemasan Kotagede"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jln. Kemasan Kotagede", personWithAddress.getDetails())

        personWithAddress.address = "Jln. Kalasan Sleman"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jln. Kalasan Sleman", personWithAddress.getDetails())
    }
}
