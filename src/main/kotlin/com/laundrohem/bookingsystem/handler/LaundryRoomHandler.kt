package com.laundrohem.bookingsystem.handler

import com.laundrohem.bookingsystem.data.transfer.Bookable
import com.laundrohem.bookingsystem.data.transfer.Booking
import com.laundrohem.bookingsystem.data.transfer.LaundryRoom
import com.laundrohem.bookingsystem.service.IBookableService
import com.laundrohem.bookingsystem.service.ILaundryRoomService
import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.time.ZonedDateTime

@Component
class LaundryRoomHandler(val bookableService : IBookableService, val laundryRoomService : ILaundryRoomService) {

    fun processGetAllLaundryRooms(servletRequest: ServerRequest) : Mono<ServerResponse> {
        return defaultReadResponse(laundryRoomService.getAllLaundryRooms())
    }

    fun defaultReadResponse(outputToPublish: Publisher<LaundryRoom>): Mono<ServerResponse> {
        return Mono.from(outputToPublish).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(outputToPublish)
        }.switchIfEmpty(ServerResponse.status(404).build())
    }
}