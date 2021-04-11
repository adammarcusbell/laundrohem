package com.laundrohem.bookingsystem.service.impl

import com.laundrohem.bookingsystem.data.state.repository.LaundryRoomBookingRepository
import com.laundrohem.bookingsystem.data.state.LaundryRoomBooking
import com.laundrohem.bookingsystem.data.state.repository.HouseholdRepository
import com.laundrohem.bookingsystem.data.state.repository.LaundryRoomRepository
import com.laundrohem.bookingsystem.data.transfer.Bookable
import com.laundrohem.bookingsystem.data.transfer.Booking
import com.laundrohem.bookingsystem.data.transfer.Household
import com.laundrohem.bookingsystem.data.transfer.LaundryRoom
import com.laundrohem.bookingsystem.service.IBookingService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.zip
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.core.publisher.zip
import reactor.util.function.Tuple2
import java.util.*

@Service
class PostgresBookingService(val laundryRoomBookingRepository: LaundryRoomBookingRepository, val laundryRoomRepository: LaundryRoomRepository,
                             val householdRepository: HouseholdRepository) : IBookingService {

    override fun createBooking(booking: com.laundrohem.bookingsystem.data.transfer.Booking):
            Mono<com.laundrohem.bookingsystem.data.transfer.Booking> {
        return Mono.just(booking).flatMap {
            getDependentDataForLaundryBooking(booking).flatMap {
                it.status = LaundryRoomBooking.BookingStatus.CONFIRMED
                it.uuid = UUID.randomUUID()
                laundryRoomBookingRepository.save(it)
            }.map {
                it.convertToTransferObject()
            }
        }
    }

    private fun getDependentDataForLaundryBooking(booking: com.laundrohem.bookingsystem.data.transfer.Booking) : Mono<LaundryRoomBooking> {
        return Mono.just(booking).flatMap { inputBooking ->
            laundryRoomRepository.findByUuid(inputBooking.bookable_item!!.uuid!!).zipWith(householdRepository.findByUuid(inputBooking.household!!.uuid!!))
        }
        .map { foundLaundryRoomAndHousehold ->
            LaundryRoomBooking().populateWithTransferObject(booking).apply {
                laundry_room_id = foundLaundryRoomAndHousehold.t1.id
                household_id = foundLaundryRoomAndHousehold.t2.id
            }
        }
    }

    private fun populateLaundryRoomAndHouseholdForBooking(laundryRoomBooking: LaundryRoomBooking, booking: Booking) :
            Mono<Booking> {
        return laundryRoomRepository.findById(laundryRoomBooking.laundry_room_id!!).map {
                booking.bookable_item = it.convertToTransferObject()
                booking
            }.flatMap { booking ->
                householdRepository.findById(laundryRoomBooking.household_id!!).map {
                    booking.household = it.convertToTransferObject()
                    booking
                }
            }
        .map {
            booking
        }
    }

    override fun getBooking(uuid: UUID): Mono<com.laundrohem.bookingsystem.data.transfer.Booking> {
        return laundryRoomBookingRepository.findBookingByUuid(uuid).flatMap { foundBooking ->
            populateLaundryRoomAndHouseholdForBooking(foundBooking, foundBooking.convertToTransferObject())
        }.map {
            it
        }
    }

    override fun getAllBookings(): Flux<com.laundrohem.bookingsystem.data.transfer.Booking> {
        return laundryRoomBookingRepository.findAll().flatMap { foundBooking ->
            populateLaundryRoomAndHouseholdForBooking(foundBooking, foundBooking.convertToTransferObject())
        }.map {
            it
        }
    }

    override fun updateBooking(booking: com.laundrohem.bookingsystem.data.transfer.Booking): Mono<com.laundrohem.bookingsystem.data.transfer.Booking> {
        TODO("Not yet implemented")
    }

    override fun deleteBooking(uuid: UUID): Mono<Void> {
        return laundryRoomBookingRepository.findBookingByUuid(uuid).flatMap {
            laundryRoomBookingRepository.deleteById(it.id!!)
        }.map {
            it
        }
    }

    override fun queryConfirmedBookings(bookingQuery: com.laundrohem.bookingsystem.data.transfer.Booking) :
            Flux<com.laundrohem.bookingsystem.data.transfer.Booking> {
        TODO("Not yet implemented")
    }

    private fun queryForOverlappingConfirmedBooking(bookingQuery: LaundryRoomBooking) : Flux<LaundryRoomBooking> {
        return laundryRoomBookingRepository.findBookingsByStartTimeBetweenOrEndTimeBetweenAndStatus(bookingQuery.startTime!!, bookingQuery.endTime!!,
            bookingQuery.startTime!!, bookingQuery.endTime!!, LaundryRoomBooking.BookingStatus.CONFIRMED)
    }
}
