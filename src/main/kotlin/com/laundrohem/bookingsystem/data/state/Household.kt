package com.laundrohem.bookingsystem.data.state

import com.laundrohem.bookingsystem.data.transfer.Transferrable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("household")
data class Household(@Id val id: Long?= null, var uuid: UUID?= null, var name : String?= null) {
    fun convertToTransferObject(): com.laundrohem.bookingsystem.data.transfer.Household {
        return com.laundrohem.bookingsystem.data.transfer.Household(uuid = this.uuid, name = this.name)
    }

    fun populateWithTransferObject(transferToConvert: com.laundrohem.bookingsystem.data.transfer.Household): Household {
        this.uuid = transferToConvert.uuid
        this.name = transferToConvert.name
        return this
    }
}



