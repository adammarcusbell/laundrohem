package com.laundrohem.bookingsystem.data.state.repository

import com.laundrohem.bookingsystem.data.state.Appliance
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface ApplianceRepository : R2dbcRepository<Appliance, Long> {

    fun findApplianceByUuid(uuid: UUID) : Mono<Appliance>
}