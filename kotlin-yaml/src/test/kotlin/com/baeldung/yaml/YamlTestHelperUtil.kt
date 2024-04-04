package com.baeldung.yaml

import com.baeldung.yaml.model.Users
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


fun verifyUsers(users: Users) {
    assertNotNull(users)

    val user1 = users.users[0]
    assertNotNull(user1)
    assertEquals("Alice", user1.name)
    assertEquals(30, user1.age)
    assertEquals("New York", user1.address.city)
    assertEquals("USA", user1.address.country)

    val user2 = users.users[1]
    assertNotNull(user2)
    assertEquals("Bob", user2.name)
    assertEquals(35, user2.age)
    assertEquals("London", user2.address.city)
    assertEquals("UK", user2.address.country)
}

