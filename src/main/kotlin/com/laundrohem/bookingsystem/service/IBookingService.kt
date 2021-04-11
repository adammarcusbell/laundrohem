package com.laundrohem.bookingsystem.service

import com.laundrohem.bookingsystem.data.transfer.Booking
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

/**
 * The purpose of this service is to provide access to whichever underlying implementation is responsible for
 * managing bookings.
 *
 * @author Adam Bell
 */
interface IBookingService {
    fun createBooking(booking: Booking) : Mono<Booking>

    fun getBooking(uuid: UUID) : Mono<Booking>

    fun getAllBookings() : Flux<Booking>

    fun updateBooking(booking: Booking) : Mono<Booking>

    fun deleteBooking(uuid: UUID) : Mono<Void>

    fun queryConfirmedBookings(bookingQuery: Booking) : Flux<Booking>
}