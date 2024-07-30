import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExtensionFieldsUnitTest {

    // using Extension Properties
    val String.firstChar: Char
        get() = this[0]

    @Test
    fun `test using extension properties`() {
        val word = "Baeldung"

        assertEquals("B", word.firstChar.toString())
    }

    class Foo {
        var name: String = "Foo"
    }

    fun Foo.getName() : String = this.name

    fun Foo.getLength() : Int = this.name.length

    @Test
    fun `test using extension function`(){
        val foo = Foo()

        assertEquals("Foo", foo.getName())
        assertEquals("Foo".length, foo.getLength())
    }

    // var Foo.someProperty: Int = 20 // this not allowed

    val propertyStorage = mutableMapOf<Foo, Int>()

    var Foo.someProperty: Int
        get() = propertyStorage[this] ?: 0
        set(value) {
            propertyStorage[this] = value
        }

    @Test
    fun `test using custom backing field`() {
        val foo = Foo()

        foo.someProperty = 20
        assertEquals(20, foo.someProperty)

        foo.someProperty = 11
        assertEquals(11, foo.someProperty)
    }
}