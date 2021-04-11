package com.laundrohem.bookingsystem.service.impl

import com.laundrohem.bookingsystem.data.state.repository.LaundryRoomRepository
import com.laundrohem.bookingsystem.data.transfer.LaundryRoom
import com.laundrohem.bookingsystem.service.ILaundryRoomService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PostgresLaundryRoomService(val laundryRoomRepository: LaundryRoomRepository) : ILaundryRoomService {

    override fun getAllLaundryRooms(): Flux<LaundryRoom> {
        return laundryRoomRepository.findAll().map {
            it.convertToTransferObject()
        }
    }
}