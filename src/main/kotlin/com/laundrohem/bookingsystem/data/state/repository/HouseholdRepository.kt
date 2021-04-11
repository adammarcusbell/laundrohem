package com.laundrohem.bookingsystem.data.state.repository

import com.laundrohem.bookingsystem.data.state.Household
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface HouseholdRepository : R2dbcRepository<Household, Long> {

    fun findByUuid(uuid: UUID) : Mono<Household>
}