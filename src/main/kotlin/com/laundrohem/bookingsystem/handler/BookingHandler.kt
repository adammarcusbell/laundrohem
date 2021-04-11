package com.laundrohem.bookingsystem.handler

import com.laundrohem.bookingsystem.data.transfer.*
import com.laundrohem.bookingsystem.data.transfer.exception.InvalidInputException
import com.laundrohem.bookingsystem.data.validator.BookingValidator
import com.laundrohem.bookingsystem.service.IBookingService
import org.reactivestreams.Publisher
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*

@Component
class BookingHandler(val bookingService: IBookingService) {

    fun processBookingCreation(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val validator = BookingValidator()
        val requestToProcess = servletRequest.bodyToMono(Booking::class.java).flatMap { bookingToCreate ->
            val errors : Errors = BeanPropertyBindingResult(bookingToCreate, Booking::class.java.name)
            validator.validate(bookingToCreate, errors)

            if (errors == null || errors.allErrors.isEmpty()) {

                bookingService.createBooking(bookingToCreate)
            }
            else {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, errors.allErrors.toString())
            }
        }
        return defaultWriteResponse(requestToProcess)
    }

    fun processBookingDeletion(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = Mono.just(servletRequest.pathVariable("uuid")).flatMap {
            bookingService.deleteBooking(UUID.fromString(it))
        }

        return ServerResponse.status(204).body(requestToProcess)
    }

    fun processGetBooking(serverRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = serverRequest.pathVariable("uuid")?.let {
            bookingService.getBooking(UUID.fromString(it))
        }

        return defaultReadResponse(requestToProcess)
    }

    fun processGetAllBookings(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = bookingService.getAllBookings()
        return defaultReadResponse(requestToProcess)
    }

    fun processBookingUpdate(serverRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = serverRequest.pathVariable("uuid")?.let { uuidStrPath ->
            serverRequest.bodyToMono(Booking::class.java).flatMap {
                if(UUID.fromString(uuidStrPath) != it.uuid)
                    throw InvalidInputException("The value of the uuid to update did not match the payload.")
                bookingService.updateBooking(it)
            }
        }

        return defaultWriteResponse(requestToProcess)
    }

    fun processBookingsQuery(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess : Flux<Booking> =  servletRequest.bodyToFlux(Booking::class.java).flatMap {
            bookingService.queryConfirmedBookings(it)
        }

        return defaultReadResponse(requestToProcess)
    }

    fun defaultWriteResponse(publishedCreation: Publisher<Booking>): Mono<ServerResponse> {
        return Mono.from(publishedCreation).flatMap {
            ServerResponse.created(URI.create("/" + it.javaClass.simpleName.toLowerCase() + "/" + it.uuid)).contentType(
                MediaType.APPLICATION_JSON).body(publishedCreation)
        }
    }

    fun defaultReadResponse(outputToPublish: Publisher<Booking>): Mono<ServerResponse> {
        return Mono.from(outputToPublish).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(outputToPublish)
        }.switchIfEmpty(ServerResponse.status(404).build())
    }
}