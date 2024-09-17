import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ExtensionFieldsAlternativesUnitTest {

    private fun String.toTitleCase(): String = this.split(" ").joinToString(" ") { it.capitalize() }

    private val String.firstChar: Char
        get() = this.first()

    @Test
    fun `test using extension functions`() {
        val word = "baeldung kotlin"
        assertEquals("Baeldung Kotlin", word.toTitleCase())
        assertEquals('b', word.firstChar)
    }

    class Car {
        var model: String = "Toyota Supra"
            get() { return field }
            set(value) { field = value }
    }

    open class Person(var name: String, var age: Int)

    val Person.isAdult: Boolean
        get() = this.age >= 18

    val Person.details: String
        get() = "Name: ${this.name}, Age: ${this.age}, isAdult: ${this.isAdult}"

    var Person.ageInDecades: Int
        get() = this.age / 10
        set(value) { this.age = value * 10 }

    val externalMap = mutableMapOf<Person, String>()

    var Person.address: String
        get() = externalMap[this] ?: ""
        set(value) {
            externalMap[this] = value
        }

    @Test
    fun `test using external Map`(){
        val person = Person("Hangga Aji Sayekti", 35)
        person.address = "Jln. Kemasan Kotagede"
        assertEquals("Jln. Kemasan Kotagede", person.address)

        person.address = "Jln. Kalasan Sleman"
        assertEquals("Jln. Kalasan Sleman", person.address)
    }

    @Test
    fun `test using properties`() {
        val car = Car()
        assertEquals("Toyota Supra", car.model)

        val person = Person("Hangga Aji Sayekti", 35)

        assertEquals("Name: Hangga Aji Sayekti, Age: 35, isAdult: true", person.details)

        person.setAge = 10;
        assertEquals("Name: Hangga Aji Sayekti, Age: 10, isAdult: false", person.details)
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
    fun `test using decorator`() {
        val person = Person("Hangga Aji Sayekti", 35)

        val personWithAddress = PersonWithAddress(person)

        personWithAddress.address = "Jln. Kemasan Kotagede"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jln. Kemasan Kotagede", personWithAddress.getDetails())

        personWithAddress.address = "Jln. Kalasan Sleman"
        assertEquals("Name: Hangga Aji Sayekti, Age: 35, Address: Jln. Kalasan Sleman", personWithAddress.getDetails())
    }

    class PersonExtended(name: String, age: Int) : Person(name, age) {
        var jobtitle: String = ""
    }

    @Test
    fun `test using inheritance`(){
        val personExtended = PersonExtended("Hangga Aji Sayekti", 35)
        personExtended.jobtitle = "Software Engineer"
        assertEquals("Software Engineer", personExtended.jobtitle)

        personExtended.jobtitle = "Data Scientist"
        assertEquals("Data Scientist", personExtended.jobtitle)

        personExtended.jobtitle = "Mathematicians"
        assertEquals("Mathematicians", personExtended.jobtitle)
    }
}