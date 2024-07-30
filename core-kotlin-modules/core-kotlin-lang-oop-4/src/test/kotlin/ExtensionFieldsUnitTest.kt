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

    class Foo(val id: Int) {
        var name: String = "Foo"
    }

    fun Foo.getName() : String = this.name

    fun Foo.getLength() : Int = this.name.length

    @Test
    fun `test using extension function`(){
        val foo = Foo(0)
        assertEquals("Foo", foo.getName())

        assertEquals("Foo".length, foo.getLength())
    }

    // var Foo.someProperty: Int = 20 // this not allowed

    val propertyStorage = mutableMapOf<Int, Int>()

    var Foo.someProperty: Int
        get() = propertyStorage[this.id] ?: 0
        set(value) {
            propertyStorage[this.id] = value
        }

    @Test
    fun `test using custom backing field`() {
        val foo = Foo(0)
        foo.someProperty = 20
        assertEquals(20, foo.someProperty)

        foo.someProperty = 11
        assertEquals(11, foo.someProperty)
    }
}