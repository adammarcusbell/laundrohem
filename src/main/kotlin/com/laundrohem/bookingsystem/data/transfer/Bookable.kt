package com.laundrohem.bookingsystem.data.transfer

import java.util.*
import kotlin.reflect.KClass

open class Bookable(uuid: UUID?= null, val type: TYPE) : Transferrable(uuid) {
    enum class TYPE(var classType: KClass<*>) {
        LaundryRoom(com.laundrohem.bookingsystem.data.state.LaundryRoom::class)
    }
}