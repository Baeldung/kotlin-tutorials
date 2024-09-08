import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ExtensionFieldsAlternativesUnitTest {


    private fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.capitalize() }

    private val String.firstChar: Char
        get() = this.first()

    class Person(var name: String, var age: Int)

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
    fun `test using extension`() {
        val person = Person("Hangga Aji Sayekti", 35)
        val personWithAddress = PersonWithAddress(person)
        personWithAddress.address = "Jalan Kemasan Kotagede"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jalan Kemasan Kotagede", personWithAddress.getDetails())

        personWithAddress.address = "Jalan Kalasan Sleman"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jalan Kalasan Sleman", personWithAddress.getDetails())
    }
}
