package com.laundrohem.bookingsystem.service

import com.laundrohem.bookingsystem.data.transfer.Household
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface IHouseholdService {

    fun createHousehold(household: Household) : Mono<Household>

    fun getHousehold(uuid: UUID) : Mono<Household>

    fun deleteHousehold(uuid: UUID) : Mono<Boolean>

    fun getAllHouseholds() : Flux<Household>
}