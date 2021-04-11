package com.laundrohem.bookingsystem.data.validator

import com.laundrohem.bookingsystem.data.transfer.Booking
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import java.time.ZonedDateTime

class BookingValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return (clazz.isInstance(Booking::class.java))
    }

    /**
     * This is being used as a validation for bookings that are being updated.  It is therefore assumed
     * that we are not interested in editing Bookings from the past.
     */
    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmpty(errors, "start_time", "field.required")
        ValidationUtils.rejectIfEmpty(errors, "end_time", "field.required")
        ValidationUtils.rejectIfEmpty(errors, "household.uuid", "field.required")
        ValidationUtils.rejectIfEmpty(errors, "bookable_item.uuid", "field.required")

        val bookingToValidate = target as Booking
        if (!ZonedDateTime.now().isBefore(bookingToValidate.start_time)) {
            errors.rejectValue("start_time", "field.date.before_now")
        }
        if (bookingToValidate.start_time!!.isAfter(bookingToValidate.end_time)) {
            errors.rejectValue("end_time", "field.date.before_start")
        }
    }
}