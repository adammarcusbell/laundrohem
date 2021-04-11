package com.laundrohem.bookingsystem.service.impl

import com.laundrohem.bookingsystem.data.state.repository.HouseholdRepository
import com.laundrohem.bookingsystem.data.state.Household
import com.laundrohem.bookingsystem.data.state.exception.ItemNotFoundException
import com.laundrohem.bookingsystem.data.transfer.exception.InvalidInputException
import com.laundrohem.bookingsystem.service.IHouseholdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Service
class PostgresHouseholdService(val householdRepository: HouseholdRepository) : IHouseholdService {

    override fun createHousehold(household: com.laundrohem.bookingsystem.data.transfer.Household):
            Mono<com.laundrohem.bookingsystem.data.transfer.Household> {
        return Mono.just(household).flatMap {
            householdRepository.save(Household().populateWithTransferObject(it).apply {
                this.uuid = UUID.randomUUID()
            })
        }.map {
            it.convertToTransferObject()
        }
    }

    override fun getHousehold(uuid: UUID): Mono<com.laundrohem.bookingsystem.data.transfer.Household> {
        return householdRepository.findByUuid(uuid).map {
            it.convertToTransferObject()
        }
    }

    override fun getAllHouseholds(): Flux<com.laundrohem.bookingsystem.data.transfer.Household> {
        return householdRepository.findAll().map {
            it.convertToTransferObject()
        }
    }

    override fun deleteHousehold(uuid: UUID): Mono<Boolean> {
        return householdRepository.findByUuid(uuid).map {
            householdRepository.deleteById(it.id!!).subscribe()
            true
        }.defaultIfEmpty(false)
    }
}