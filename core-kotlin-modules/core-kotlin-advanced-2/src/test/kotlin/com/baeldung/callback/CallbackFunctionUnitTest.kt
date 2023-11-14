package com.baeldung.callback

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

data class User(var firstName: String, var lastName: String)
class CallbackFunctionUnitTest {

    @Test
    fun `callback function to perform LCM`(){
        val lcm = {x: Int, y: Int ->
            var gcd = 1

            var i = 1
            while (i <= x && i <= y) {
                // Checks if i is factor of both integers
                if (x % i == 0 && y % i == 0)
                    gcd = i
                ++i
            }

            x * y / gcd

        }


        var res1 = listOf(2,3,4).reduce(lcm)
        var res2 = listOf(5,15,4).reduce(lcm)

        assertEquals(12, res1)
        assertEquals(60, res2)
    }

    @Test
    fun `asynchronous callback to load remote data`(){

        suspend fun loadUsersFromServer(callback: (List<User>) -> Unit) {
            delay(5000)
            val users = listOf(User("Flore", "P"), User("Nappy", "Sean"), User("Ndole", "Paul"))
            callback(users)
        }

        var listofUsers = emptyList<User>()
        suspend fun executeLoading(){
            loadUsersFromServer { users ->
                listofUsers = users
            }

        }

        runBlocking {
            executeLoading()
        }
        assertEquals(3, listofUsers.size)
        assertEquals("Flore", listofUsers[0].firstName)
        assertEquals("Sean", listofUsers[1].lastName)
        assertEquals("Ndole Paul", "${listofUsers[2].firstName} ${listofUsers[2].lastName}" )

    }

    @Test
    fun `demonstration of callback hell`(){

        fun getUserToken(id: Int, callback: (id:Int) -> Int): Int{
            return callback(id)
        }

        fun hashUserToken(id:Int): Int{
            return (id%100) * 12000
        }

        fun downloadBook(callback: (id: Int) -> Unit){
            var userToken = getUserToken(2){
                    id ->  hashUserToken(id)
            }

            callback(userToken)
        }

        fun saveBook(bookId: Int, callback: (bookId: Int) -> Unit){
            callback(bookId)
        }

        fun openBook(bookId: Int): Boolean{

            if (bookId>0)
                return true
            return false
        }

        fun openPDF(bookId: Int){
            downloadBook { id ->
                saveBook(bookId){bookId ->
                    openBook(bookId)
                }
            }
        }

    }

    @Test
    fun `assign variable to function`(){
        var square = fun (x: Int) : Int{
            return x*x
        }

        assertEquals(16, square(4))
    }

    @Test
    fun `demo use of callback in higher order functions`(){
        var  numbers = arrayOf(1,2,3,4,5,6,7,8,9,10)
        var evenNumbers = numbers.filter(fun(number): Boolean {
            return number%2==0
        })

        assertContentEquals(arrayOf(2, 4, 6, 8, 10).toIntArray(), evenNumbers.toIntArray())
    }
}