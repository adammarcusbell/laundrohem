package com.laundrohem.bookingsystem.data.state

import com.laundrohem.bookingsystem.data.state.exception.InvalidBookingException
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime
import java.util.*

@Table("booking")
data class LaundryRoomBooking(@Id val id: Long?= null, var uuid: UUID?= null, var household_id: Long?= null,
                   var startTime: ZonedDateTime?=null, var endTime: ZonedDateTime?=null,
                   var laundry_room_id : Long?= null, var status: BookingStatus?= BookingStatus.UNKNOWN) {

    enum class BookingStatus {
        UNKNOWN,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    fun convertToTransferObject(): com.laundrohem.bookingsystem.data.transfer.Booking {
        return com.laundrohem.bookingsystem.data.transfer.Booking(uuid = this.uuid, start_time = this.startTime, end_time = this.endTime)
    }

    fun populateWithTransferObject(transferToConvert: com.laundrohem.bookingsystem.data.transfer.Booking) : LaundryRoomBooking {
        if(!transferToConvert.bookable_item!!.type.equals(com.laundrohem.bookingsystem.data.transfer.Bookable.TYPE.LaundryRoom)) {
            throw InvalidBookingException("The booking was for an incorrect Bookable type.")
        }
        return LaundryRoomBooking(uuid = transferToConvert.uuid, startTime = transferToConvert.start_time, endTime = transferToConvert.end_time)
    }

    fun timeBookableHashcode() : Int {
        return startTime.hashCode() + endTime.hashCode() + household_id.hashCode() + laundry_room_id.hashCode()
    }
}