package com.laundrohem.bookingsystem.data.state

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("laundryroom")
data class LaundryRoom(@Id val id: Long?= null, var uuid: UUID?= null, var name: String?= null) {

    fun convertToTransferObject(): com.laundrohem.bookingsystem.data.transfer.LaundryRoom {
        return com.laundrohem.bookingsystem.data.transfer.LaundryRoom(
            uuid = this.uuid, name = this.name
        )
    }

    fun populateWithTransferObject(transferToConvert: com.laundrohem.bookingsystem.data.transfer.LaundryRoom): LaundryRoom {
        this.uuid = transferToConvert.uuid
        this.name = transferToConvert.name
        return this
    }
}