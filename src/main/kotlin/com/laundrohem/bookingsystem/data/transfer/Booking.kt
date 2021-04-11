package com.laundrohem.bookingsystem.data.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime
import java.util.*

class Booking(uuid: UUID?= null,
              var household: Household?= null,
              @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC") val start_time: ZonedDateTime?=null,
              @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC") val end_time: ZonedDateTime?=null,
              var bookable_item : Bookable?= null) : Transferrable(uuid) {
}