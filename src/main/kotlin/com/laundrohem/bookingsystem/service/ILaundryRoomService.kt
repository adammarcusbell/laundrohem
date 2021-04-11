package com.laundrohem.bookingsystem.service

import com.laundrohem.bookingsystem.data.transfer.LaundryRoom
import reactor.core.publisher.Flux

interface ILaundryRoomService {

    fun getAllLaundryRooms() : Flux<LaundryRoom>
}