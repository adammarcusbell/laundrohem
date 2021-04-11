package com.laundrohem.bookingsystem.handler

import com.laundrohem.bookingsystem.data.transfer.Household
import com.laundrohem.bookingsystem.service.IHouseholdService
import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@Component
class HouseholdHandler(val householdService: IHouseholdService) {
    fun processCreateHousehold(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val creationTask = servletRequest.bodyToMono(Household::class.java).flatMap {
            householdService.createHousehold(it)
        }

        return defaultWriteResponse(creationTask)
    }

    fun processGetHousehold(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = householdService.getHousehold(
            UUID.fromString(servletRequest.pathVariable("uuid")))
        return defaultReadResponse(requestToProcess)
    }

    fun processDeleteHousehold(servletRequest: ServerRequest) : Mono<ServerResponse> {
        return householdService.deleteHousehold(UUID.fromString(servletRequest.pathVariable("uuid"))).flatMap {
            it.let {
                val status = if(it) 204 else 404
                ServerResponse.status(status).build()
            }
        }
    }

    fun processGetAllHouseholds(servletRequest: ServerRequest) : Mono<ServerResponse> {
        val requestToProcess = householdService.getAllHouseholds()
        return defaultReadResponse(requestToProcess)
    }

    fun defaultWriteResponse(publishedCreation: Publisher<Household>): Mono<ServerResponse> {
        return Mono.from(publishedCreation).flatMap {
            ServerResponse.created(URI.create("/" + it.javaClass.simpleName.toLowerCase() + "/" + it.uuid)).contentType(MediaType.APPLICATION_JSON).body(publishedCreation)
        }
    }

    fun defaultReadResponse(outputToPublish: Publisher<Household>): Mono<ServerResponse> {
        return Mono.from(outputToPublish).flatMap {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(outputToPublish)
        }.switchIfEmpty(ServerResponse.status(404).build())
    }
}