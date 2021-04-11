package com.laundrohem.test.integration.endpoint

import com.laundrohem.bookingsystem.BookingsystemApplication
import com.laundrohem.bookingsystem.data.transfer.Booking
import com.laundrohem.bookingsystem.data.transfer.Household
import com.laundrohem.bookingsystem.data.transfer.LaundryRoom
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import java.net.URI
import java.time.ZonedDateTime
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [BookingsystemApplication::class])
@RunWith(SpringRunner::class)
@ActiveProfiles("integrationtest")
class BookingIntegrationTest {

    @Autowired
    lateinit var webTestClient : WebTestClient

    var laundryRooms = mutableListOf<LaundryRoom>()
    var households = mutableListOf<Household>()

    @PostConstruct
    fun setup() {
        // get the Laundry Rooms
        laundryRooms = webTestClient.get()
            .uri("/laundryroom")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(LaundryRoom::class.java).returnResult().responseBody!!

        households = webTestClient.get()
            .uri("/household")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<Household>().returnResult().responseBody!!
    }


    @Test
    fun makeGetAndGetAllAndCancelBooking() {
        val validBooking = Booking(start_time = ZonedDateTime.now().plusHours(1),
            end_time = ZonedDateTime.now().plusHours(2), bookable_item = laundryRooms.get(1), household = households.get(0))

        val bookingURI : URI = webTestClient.post()
            .uri("/booking")
            .bodyValue(validBooking)
            .exchange()
            .expectStatus().isCreated
            .expectBody().isEmpty.responseHeaders.location!!

        /**bookingURI.let {
            webTestClient.get().uri("" + bookingURI)
                .exchange()
                .expectStatus().isOk
                .expectBody(Booking::class.java)
                .returnResult().responseBody!!
        }

        bookingURI.let {
            webTestClient.delete()
                .uri("" + bookingURI)
                .exchange()
                .expectStatus().isEqualTo(204)
                .expectBody().isEmpty
        }**/
    }
}