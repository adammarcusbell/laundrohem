package com.laundrohem.test.integration.endpoint

import com.laundrohem.bookingsystem.BookingsystemApplication
import com.laundrohem.bookingsystem.data.transfer.Household
import com.laundrohem.bookingsystem.data.transfer.TestData_Household
import org.junit.BeforeClass
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.Assert
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [BookingsystemApplication::class])
@RunWith(SpringRunner::class)
@AutoConfigureWebTestClient
@ActiveProfiles("integrationtest")
class HouseholdIntegrationTest {

    @Autowired
    lateinit var webTestClient : WebTestClient

    @Test
    fun testGetAllHouseholds() {
        webTestClient.get()
            .uri("/household")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Household::class.java)
            .hasSize(20)
    }

    /**
     * Create a Household, Get it to confirm its creation, Delete it, then Confirm its Deletion with an empty Get
     */
    @Test
    fun testCreateGetAndDeleteHousehold() {
        val createdHouseholdLocation : URI = webTestClient.post()
            .uri("/household")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(TestData_Household.TEST_NEW_VALID_HOUSEHOLD)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().exists("location")
            .expectBody(Household::class.java)
            .returnResult().responseHeaders.location!!

        createdHouseholdLocation!!.let {
            webTestClient.get()
                .uri(it)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectBody(Household::class.java)
                .returnResult().responseBody.let {
                    Assert.isTrue(TestData_Household.TEST_NEW_VALID_HOUSEHOLD.name.equals(it!!.name),
                        "The Name submitted for creation did not match the name returned.")
                }
        }

        createdHouseholdLocation!!.let {
            webTestClient.delete()
                .uri(it)
                .exchange()
                .expectStatus().isEqualTo(204)
        }

        createdHouseholdLocation!!.let {
            webTestClient.get()
                .uri(it)
                .exchange()
                .expectStatus().isEqualTo(404)
        }
    }
}