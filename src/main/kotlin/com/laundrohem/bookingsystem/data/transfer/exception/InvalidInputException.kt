package com.laundrohem.bookingsystem.data.transfer.exception

class InvalidInputException(override val message: String?) : Throwable(message = message) {
}