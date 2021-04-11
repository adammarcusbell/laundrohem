package com.laundrohem.bookingsystem.data.state.repository

import com.laundrohem.bookingsystem.data.state.LaundryRoom
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface LaundryRoomRepository : R2dbcRepository<LaundryRoom, Long> {

    fun findLaundryRoomByUuid(uuid: UUID) : Mono<LaundryRoom>
    fun findByUuid(uuid: UUID) : Mono<LaundryRoom>
}