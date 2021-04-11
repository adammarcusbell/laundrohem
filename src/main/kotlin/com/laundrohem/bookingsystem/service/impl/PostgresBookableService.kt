package com.laundrohem.bookingsystem.service.impl

import com.laundrohem.bookingsystem.data.transfer.Bookable
import com.laundrohem.bookingsystem.data.transfer.Booking
import com.laundrohem.bookingsystem.service.IBookableService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Period
import java.time.ZonedDateTime

/**
 * The purpose of this service is to provide access to whichever underlying implementation is responsible for accessing
 * Bookable resources within the application.
 *
 * @author Adam Bell
 */
@Service
class PostgresBookableService : IBookableService{
    override fun queryAvailabileBookableSlots(bookable: Bookable, start_time: ZonedDateTime, end_time: ZonedDateTime): Flux<Booking> {
        TODO("Not yet implemented")
    }
}