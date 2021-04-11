package com.laundrohem.bookingsystem.data.transfer

import java.util.*

class TestData_Appliance {

    companion object {
        val ELECROTROLUX_WASHER_LR1 = Appliance(UUID.randomUUID(),
            "Front Load Perfect Steam™ Washer with LuxCare® Wash and SmartBoost®",
            listOf(TestData_ServiceFeature.NORMAL_WASH_60, TestData_ServiceFeature.ECO_WASH_120, TestData_ServiceFeature.QUICK_RINSE_20))

        val ELECTROLUX_DRYER_LR1 = Appliance(UUID.randomUUID(),
            "Front Load Perfect Steam™ Gas Dryer with PredictiveDry™ and Instant Refresh",
            listOf(TestData_ServiceFeature.SPIN_DRY_120))

        val ELECROTROLUX_WASHER_LR2 = Appliance(UUID.randomUUID(),
            "Front Load Perfect Steam™ Washer with LuxCare® Wash and SmartBoost®",
            listOf(TestData_ServiceFeature.NORMAL_WASH_60, TestData_ServiceFeature.ECO_WASH_120, TestData_ServiceFeature.QUICK_RINSE_20))

        val ELECTROLUX_DRYER_LR2 = Appliance(UUID.randomUUID(),
            "Front Load Perfect Steam™ Gas Dryer with PredictiveDry™ and Instant Refresh", listOf(TestData_ServiceFeature.SPIN_DRY_120))
    }
}