import com.baeldung.iteratePropertiesOfDataClass.Person
import com.baeldung.iteratePropertiesOfDataClass.getFields
import kotlin.test.Test
import kotlin.test.assertEquals

class IteratePropertiesOfDataClassUnitTest {
    @Test
    fun `iterate fields using destructuring declaration`() {
        val person = Person("Robert", 28)
        val (name, age) = person
        assertEquals("Robert", name)
        assertEquals(28, age)
    }

    @Test
    fun `iterate fields using componentN methods`() {
        val person = Person("Robert", 28)
        val fields = listOf(person.component1(), person.component2())

        assertEquals("Robert", fields[0])
        assertEquals(28, fields[1])
    }

    @Test
    fun `iterate fields using KClassUnpacker plugin`() {
        val person = Person("Robert", 28)
        val list = getFields(person)

        assertEquals("Robert", list[0])
        assertEquals("28", list[1])
    }
}
