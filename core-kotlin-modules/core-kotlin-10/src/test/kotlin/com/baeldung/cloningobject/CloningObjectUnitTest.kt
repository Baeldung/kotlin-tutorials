package com.baeldung.cloningobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Address(var street: String, var city: String) : Cloneable {

    public override fun clone(): Address = super.clone() as Address

    constructor(address: Address) : this(address.street, address.city)

    fun deepCopy(
        street: String = this.street,
        city: String = this.city
    ) = Address(street, city)
}

data class Person(var name: String, var address: Address) : Cloneable {

    public override fun clone() = Person(name, this.address.clone())

    constructor(person: Person) : this(person.name, Address(person.address))

    fun deepCopy(
        name: String = this.name,
        address: Address = this.address.deepCopy()
    ) = Person(name, address)
}

data class Company(var name: String, var industry: String, val ceo: Person, val employees: List<Person>) : Cloneable {

    public override fun clone() = Company(name, industry, ceo.clone(), employees.map { it.clone() })

    constructor(company: Company) : this(
        company.name,
        company.industry,
        Person(company.ceo),
        company.employees.map { Person(it) })

    fun deepCopy(
        name: String = this.name,
        industry: String = this.industry,
        ceo: Person = this.ceo.deepCopy(),
        employees: List<Person> = this.employees.map { it.deepCopy() },
    ) = Company(name, industry, ceo, employees)
}

data class Organization(var name: String, val headquarters: Address, val companies: List<Company>) : Cloneable {

    public override fun clone()  = Organization(name, headquarters.clone(), companies.map { it.clone() })

    constructor(organization: Organization) : this(
        organization.name,
        Address(organization.headquarters),
        organization.companies.map { Company(it) })

    fun deepCopy(
        name: String = this.name,
        headquarters: Address = this.headquarters.deepCopy(),
        companies: List<Company> = this.companies.map { it.deepCopy() },
    ) = Organization(name, headquarters, companies)
}

class CloningObjectUnitTest {

    private val personHangga = Person("Hangga Aji Sayekti", Address("Jln. Kemasan No 53", "Yogyakarta"))
    private val personRaihan = Person("Raihan Kusumo", Address("Jln. Cikapayang No. 508", "Medan"))
    private val personLayla = Person("Layla Hinchcliffe", Address("Collins Street", "Melbourne"))
    private val companyBasen = Company("Basen Software", "Tech", personHangga, listOf(personRaihan, personLayla))

    private val personBima = Person("Bima Arya", Address("Jl. Colombo No. 7", "Yogyakarta"))
    private val personDina = Person("Dina Fitriani", Address("Jl. Kaliurang No. 12", "Yogyakarta"))
    private val personCindy = Person("Cindy Claudia", Address("Jl. Atmosukarto No. 1", "Yogyakarta"))
    private val companyKotagede = Company("Kotagede Software", "Tech", personBima, listOf(personCindy, personDina))

    private val organization = Organization("Bekraf",Address("Jalan Medan Merdeka Selatan",
        "Jakarta"),listOf(companyBasen, companyKotagede))

    private fun modifyOrganization() {
        organization.name = "New Org Name"
        organization.headquarters.city = "New City"
        organization.companies.first().name = "New Company Name"
        organization.companies.first().ceo.name = "New CEO Name"
        organization.companies.first().employees.first().name = "New Employee Name"
    }

    @Test
    fun `when cloned object with secondary constructor then proves that deep copy`() {
        val clonedOrganization = Organization(organization)

        modifyOrganization()

        assertThat(clonedOrganization)
            .isNotSameAs(organization)
        assertThat(clonedOrganization.headquarters.city)
            .isNotEqualTo("New City")
        assertThat(clonedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")
        assertThat(clonedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")
        assertThat(clonedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")
    }

    @Test
    fun `when cloned object with deepCopy() then proves that deep copy`() {
        val clonedOrganization = organization.deepCopy()

        modifyOrganization()

        assertThat(clonedOrganization)
            .isNotSameAs(organization)
        assertThat(clonedOrganization.headquarters.city)
            .isNotEqualTo("New City")
        assertThat(clonedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")
        assertThat(clonedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")
        assertThat(clonedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")
    }

    @Test
    fun `when cloned object with clone() then proves that deep copy`() {
        val clonedOrganization = organization.clone()

        modifyOrganization()

        assertThat(clonedOrganization)
            .isNotSameAs(organization)
        assertThat(clonedOrganization.headquarters.city)
            .isNotEqualTo("New City")
        assertThat(clonedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")
        assertThat(clonedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")
        assertThat(clonedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")
    }

    @Test
    fun `when cloned object with copy() then proves that deep copy`() {

        val clonedOrganization = organization.copy(headquarters = organization.headquarters.copy(),
            companies = organization.companies.map { company -> company.copy(
                ceo = company.ceo.copy(), employees = company.employees.map {it.copy()})
            })

        modifyOrganization()

        assertThat(clonedOrganization)
            .isNotSameAs(organization)
        assertThat(clonedOrganization.headquarters.city)
            .isNotEqualTo("New City")
        assertThat(clonedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")
        assertThat(clonedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")
        assertThat(clonedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")
    }
}