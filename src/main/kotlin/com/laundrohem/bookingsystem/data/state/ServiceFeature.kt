package com.laundrohem.bookingsystem.data.state

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Duration
import java.util.*

@Table("SERVICEFEATURE")
data class ServiceFeature(@Id val id: Long?= null, val uuid: UUID?= null, val type : FeatureType?= null, val duration: Duration) {

    enum class FeatureType {
        NORMAL_WASH,
        ECO_WASH,
        QUICK_RINSE,
        SPIN_DRY
    }

    fun convertToTransferObject(): com.laundrohem.bookingsystem.data.transfer.ServiceFeature {
        TODO("Not yet implemented")
    }

    fun populateWithTransferObject(transferToConvert: com.laundrohem.bookingsystem.data.transfer.ServiceFeature): ServiceFeature {
        TODO("Not yet implemented")
    }
}