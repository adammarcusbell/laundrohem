package com.laundrohem.bookingsystem.data.state

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("APPLIANCE")
data class Appliance(@Id val id: Long?= null, val uuid: UUID?= null, val name: String?= null,
                     val serviceFeatures : List<ServiceFeature>?= null) {

    fun convertToTransferObject(): com.laundrohem.bookingsystem.data.transfer.Appliance {
        TODO("Not yet implemented")
    }

    fun populateWithTransferObject(transferToConvert: com.laundrohem.bookingsystem.data.transfer.Appliance): Appliance {
        TODO("Not yet implemented")
    }
}