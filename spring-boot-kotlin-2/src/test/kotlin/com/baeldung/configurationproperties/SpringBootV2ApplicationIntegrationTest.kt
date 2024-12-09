package com.baeldung.configurationproperties

import com.baeldung.autowired.InventoryController
import com.baeldung.autowired.PersonController
import com.baeldung.configurationproperties.config.ApiConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringBootV2ApplicationIntegrationTest {

    @Autowired
    lateinit var apiConfiguration: ApiConfiguration

    @Autowired
    lateinit var personController: PersonController

    @Autowired
    lateinit var inventoryController: InventoryController

    @Test
    fun givenExternalConfigProps_whenUsedConstructorBinding_thenBindExternalProperties() {
        assertThat(apiConfiguration).isNotNull
        assertThat(apiConfiguration.url).isNotBlank
        assertThat(apiConfiguration.clientId).isNotBlank
        assertThat(apiConfiguration.key).isNotBlank
        print("test")
    }

    @Test
    fun givenAConstructor_whenComponentPassedToConstructorWithoutAutowiredAnnotation_thenComponentIsAutowiredImplicitly() {
        assertNotNull( personController.personService)
    }

    @Test
    fun givenTargetBeanHavingMultipleConstructorsWithoutAutowired_whenComponentPassedToConstructor_thenTargetBeanWithDefaultConstructorIsAutowiredImplicitly() {
        assertNotNull( personController.locationService)
        assertEquals(109.344550, personController.locationService.lat)
        assertEquals(133.973849, personController.locationService.lon)
    }

    @Test
    fun givenAConstructor_whenComponentPassedToConstructorWithExplicitAutowiredAnnotated_thenComponentIsAutowired() {
        assertNotNull( personController.personService)
        assertNotNull(personController.addressService.address)
        assertEquals("101 st, abc city, xyz state 12345", personController.addressService.address.fullAddress)
    }

    @Test
    fun givenMultipleConstructors_whenOneConstructorAutowiredAnnotated_thenComponentIsAutowired() {
        assertNotNull(personController.personService)
        assertNotNull(personController.personService.person)
        assertNotNull(personController.personService.address)
    }

    @Test
    fun givenAMemberField_whenFieldIsAutowiredAnnotated_thenComponentIsAutowired() {
        assertNotNull(inventoryController.inventoryService)
        assertNotNull(inventoryController.inventoryService.dealerDao)
    }

    @Test
    fun givenAMemberField_whenSetterFieldIsAutowiredAnnotated_thenComponentIsAutowired() {
        assertNotNull(inventoryController.inventoryService)
        assertNotNull(inventoryController.inventoryService.dealerDao)
        assertNotNull(inventoryController.inventoryService.dealerDao.reviews)
    }

    @Test
    fun givenMemberFields_whenArbitraryMethodUsedToInjectDependencies_thenComponentsAreAutowired() {
        assertNotNull(inventoryController.inventoryService)
        assertNotNull(inventoryController.inventoryService.vehicleDao)
        assertNotNull(inventoryController.inventoryService.vehicleDao.vehicleValueFinder)
        assertNotNull(inventoryController.inventoryService.vehicleDao.vehicleReviewDao)
    }
}