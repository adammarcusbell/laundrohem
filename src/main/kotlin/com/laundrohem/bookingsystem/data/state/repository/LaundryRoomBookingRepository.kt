package com.laundrohem.bookingsystem.data.state.repository

import com.laundrohem.bookingsystem.data.state.LaundryRoomBooking
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime
import java.util.*

@Repository
interface LaundryRoomBookingRepository : R2dbcRepository<LaundryRoomBooking, Long> {

    fun findBookingByUuid(uuid: UUID) : Mono<LaundryRoomBooking>

    fun findBookingsByStartTimeAfterAndEndTimeBeforeAndStatus(startTime: ZonedDateTime, endTime: ZonedDateTime, status: LaundryRoomBooking.BookingStatus) : Flux<LaundryRoomBooking>

    fun findBookingsByStartTimeBetweenOrEndTimeBetweenAndStatus(startTimeStart: ZonedDateTime, startTimeEnd: ZonedDateTime,
                                                                endTimeStart: ZonedDateTime, endTimeEnd: ZonedDateTime,status: LaundryRoomBooking.BookingStatus) : Flux<LaundryRoomBooking>
}