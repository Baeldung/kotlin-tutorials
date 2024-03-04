package com.baeldung.cloningobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Address(var street: String, var city: String) : Cloneable {

    public override fun clone(): Address = super.clone() as Address

//    constructor(address: Address) : this(address.street, address.city)
}

data class Person(var name: String, var address: Address) : Cloneable {

    public override fun clone() = Person(name, this.address.clone())

    fun deepCopy(name: String = this.name, address: Address = this.address.copy()): Person {
        return Person(name, address)
    }

    constructor(person: Person) : this(person.name, person.address.copy())
}

data class Company(var name: String, var industry: String, val ceo: Person, val employees: List<Person>) : Cloneable {

    fun deepCopy(name: String = this.name, industry: String = this.industry, ceo: Person = this.ceo.deepCopy(),
                 employees: List<Person> = this.employees.map { it.deepCopy() }): Company{
        return Company(name, industry, ceo, employees)
    }

    public override fun clone(): Company {
        return Company(name, industry, ceo.clone(), employees.map { it.clone() })
    }

    constructor(company: Company) : this(company.name, company.industry, Person(company.ceo),
        company.employees.map { Person(it) })
}

data class Organization(var name: String, val headquarters: Address, val companies: List<Company>) : Cloneable {

    fun deepCopy(name: String = this.name, headquarters: Address = this.headquarters.copy(),
                 companies: List<Company> = this.companies.map { it.deepCopy() }) : Organization{
        return Organization(name, headquarters, companies)
    }

    public override fun clone(): Organization {
        return Organization(name, headquarters.clone(), companies.map { it.clone() })
    }

    constructor(organization: Organization) : this(organization.name, organization.headquarters.copy(),
        organization.companies.map { Company(it) })
}

class CloningObjectUnitTest {

    @Test
    fun `when cloned object then proves that deep copy`(){
        val personHangga = Person("Hangga Aji Sayekti", Address("Jln. Kemasan No 53", "Yogyakarta"))
        val personRaihan = Person("Raihan Kusumo", Address("Jln. Cikapayang No. 508", "Medan"))
        val personLayla = Person("Layla Hinchcliffe", Address("Collins Street", "Melbourne"))
        val companyBasen = Company("Basen Software", "Tech", personHangga, listOf(personRaihan, personLayla))

        val personBima = Person("Bima Arya", Address("Jl. Colombo No. 7", "Yogyakarta"))
        val personDina = Person("Dina Fitriani", Address("Jl. Kaliurang No. 12", "Yogyakarta"))
        val personCindy = Person("Cindy Claudia", Address("Jl. Atmosukarto No. 1", "Yogyakarta"))
        val companyKotagede = Company("Kotagede Software", "Tech", personBima, listOf(personCindy, personDina))

        val organization = Organization("Bekraf", Address("Jalan Medan Merdeka Selatan", "Jakarta"), listOf(companyBasen, companyKotagede))

        val copiedOrganization = organization.copy(
            headquarters = organization.headquarters.copy(),
            companies = organization.companies.map { company ->
                company.copy(
                    ceo = company.ceo.copy(),
                    employees = company.employees.map { it.copy() }
                )
            }
        )

        val clonedOrganization = organization.clone()

        val clonedSecondaryOrganization = Organization(organization)

        val coustomDeepCopyOrganization = organization.deepCopy()

        // Modify the original object to verify deep copy
        organization.name = "New Org Name"
        organization.headquarters.city = "New City"
        organization.companies.first().name = "New Company Name"
        organization.companies.first().ceo.name = "New CEO Name"
        organization.companies.first().employees.first().name = "New Employee Name"

        // verify deep copy with copy()
        assertThat(copiedOrganization.headquarters.city)
            .isNotEqualTo("New City")

        assertThat(copiedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")

        assertThat(copiedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")

        assertThat(copiedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")

        // verify deep copy with clone()
        assertThat(clonedOrganization.headquarters.city)
            .isNotEqualTo("New City")

        assertThat(clonedOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")

        assertThat(clonedOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")

        assertThat(clonedOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")


        // verify deep copy with secondary()
        assertThat(clonedSecondaryOrganization.headquarters.city)
            .isNotEqualTo("New City")

        assertThat(clonedSecondaryOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")

        assertThat(clonedSecondaryOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")

        assertThat(clonedSecondaryOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")

        // deep copy
        assertThat(coustomDeepCopyOrganization.headquarters.city)
            .isNotEqualTo("New City")

        assertThat(coustomDeepCopyOrganization.companies.first().name)
            .isNotEqualTo("New Company Name")

        assertThat(coustomDeepCopyOrganization.companies.first().ceo.name)
            .isNotEqualTo("New CEO Name")

        assertThat(coustomDeepCopyOrganization.companies.first().employees.first().name)
            .isNotEqualTo("New Employee Name")
    }
}