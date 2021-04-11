package com.laundrohem.bookingsystem.service

import com.laundrohem.bookingsystem.data.transfer.Bookable
import com.laundrohem.bookingsystem.data.transfer.Booking
import reactor.core.publisher.Flux
import java.time.ZonedDateTime

interface IBookableService {

    fun queryAvailabileBookableSlots(bookable: Bookable, start_time: ZonedDateTime, end_time: ZonedDateTime) : Flux<Booking>
}