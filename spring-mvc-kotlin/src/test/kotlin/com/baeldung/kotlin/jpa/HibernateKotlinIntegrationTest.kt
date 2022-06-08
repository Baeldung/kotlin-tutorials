package com.baeldung.kotlin.jpa

import org.hibernate.Hibernate
import org.hibernate.cfg.Configuration
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.junit.Assert.assertTrue
import org.junit.Test
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*
import kotlin.test.assertFalse

class HibernateKotlinIntegrationTest : BaseCoreFunctionalTestCase() {

    private val logger = LoggerFactory.getLogger(HibernateKotlinIntegrationTest::class.java)

    private val properties: Properties
        @Throws(IOException::class)
        get() {
            val properties = Properties()
            properties.load(javaClass.classLoader.getResourceAsStream("hibernate.properties"))
            return properties
        }

    override fun getAnnotatedClasses(): Array<Class<*>> {
        return arrayOf(Person::class.java, Address::class.java, PhoneNumber::class.java)
    }

    override fun configure(configuration: Configuration) {
        super.configure(configuration)
        configuration.properties = properties
    }

    @Test
    fun givenPersonWithFullData_whenSaved_thenFound() {
        doInHibernate(({ this.sessionFactory() })) { session ->
            val personToSave = Person(
                "John",
                "jhon@test.com",
                listOf(PhoneNumber("202-555-0171"), PhoneNumber("202-555-0102"))
            )
            session.persist(personToSave)
            val personFound = session.find(Person::class.java, personToSave.id)
            session.refresh(personFound)
            assertTrue(personToSave.name == personFound.name)
        }
    }

    @Test
    fun givenPerson_whenSaved_thenFound() {
        doInHibernate(({ this.sessionFactory() })) { session ->
            val personToSave = Person( "John")
            session.persist(personToSave)
            val personFound = session.find(Person::class.java, personToSave.id)
            session.refresh(personFound)

            assertTrue(personToSave.name == personFound.name)
        }
    }

    @Test
    fun givenPersonWithNullFields_whenSaved_thenFound() {
        doInHibernate(({ this.sessionFactory() })) { session ->
            val personToSave = Person("John", null, null)
            session.persist(personToSave)
            val personFound = session.find(Person::class.java, personToSave.id)
            session.refresh(personFound)

            assertTrue(personToSave.name == personFound.name)
        }
    }

    @Test
    fun givenAddressWithDefaultEquals_whenAddedToSet_thenNotFound() {
        doInHibernate({ sessionFactory() }) { session ->
            val addresses = mutableSetOf<Address>()
            val address = Address(name = "Berlin", phoneNumbers = listOf(PhoneNumber( "42")))
            addresses.add(address)

            assertTrue(address in addresses)
            session.persist(address)
            assertFalse { address in addresses }
        }
    }

    @Test
    fun givenAddress_whenLogging_thenFetchesLazyAssociations() {
        doInHibernate({ this.sessionFactory() }) { session ->
            val addressToSave = Address(name = "Berlin", phoneNumbers = listOf(PhoneNumber("42")))
            session.persist(addressToSave)
            session.clear()

            val addressFound = session.find(Address::class.java, addressToSave.id)

            assertFalse { Hibernate.isInitialized(addressFound.phoneNumbers) }
            logger.info("found the entity {}", addressFound)
            assertTrue(Hibernate.isInitialized(addressFound.phoneNumbers))
        }
    }
}