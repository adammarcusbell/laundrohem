package com.laundrohem.bookingsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

/**
 * This is the Booking Systems Application - it is intended to provide the following functionality - with additions that ensure
 * flexibility for foreseeable requirements.
 *
 * Define a set of
 * @see com.laundrohem.bookingsystem.data.transfer.LaundryRoom with
 * @see com.laundrohem.bookingsystem.data.transfer.Appliance that have
 * @see com.laundrohem.bookingsystem.data.transfer.ServiceFeature
 * capable of processing laundry
 *
 * Define a set of
 * @see com.laundrohem.bookingsystem.data.transfer.Household that require access to these
 * @see com.laundrohem.bookingsystem.data.transfer.LaundryRoom
 *
 * Allow the
 * @see com.laundrohem.bookingsystem.data.transfer.Household to book or cancel the
 * @see com.laundrohem.bookingsystem.data.transfer.LaundryRoom for a required period
 *
 * Allow for the potential to book at a more granular level, so the system can provide a more effective service
 * to all the residents and make maximum use of the facilities.
 *
 * @author Adam Bell
 */
@SpringBootApplication
@EnableWebFlux
class BookingsystemApplication

fun main(args: Array<String>) {
	runApplication<BookingsystemApplication>(*args)
}
