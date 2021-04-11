package com.laundrohem.bookingsystem.data.transfer

import java.time.Duration
import java.util.*

class TestData_ServiceFeature {

    companion object {
        val QUICK_RINSE_20 = ServiceFeature(UUID.randomUUID(), ServiceFeature.FeatureType.QUICK_RINSE, Duration.ofMinutes(20))
        val ECO_WASH_120 = ServiceFeature(UUID.randomUUID(), ServiceFeature.FeatureType.ECO_WASH, Duration.ofMinutes(120))
        val NORMAL_WASH_60 = ServiceFeature(UUID.randomUUID(), ServiceFeature.FeatureType.NORMAL_WASH, Duration.ofMinutes(60))
        val SPIN_DRY_120 = ServiceFeature(UUID.randomUUID(), ServiceFeature.FeatureType.SPIN_DRY, Duration.ofMinutes(120))
    }
}