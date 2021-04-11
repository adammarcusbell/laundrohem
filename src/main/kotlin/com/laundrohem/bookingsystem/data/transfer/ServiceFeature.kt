package com.laundrohem.bookingsystem.data.transfer

import java.time.Duration
import java.util.*

class ServiceFeature(uuid : UUID?= null, val type : FeatureType?= null, val duration: Duration) : Transferrable(uuid) {

    enum class FeatureType {
        NORMAL_WASH,
        ECO_WASH,
        QUICK_RINSE,
        SPIN_DRY
    }
}