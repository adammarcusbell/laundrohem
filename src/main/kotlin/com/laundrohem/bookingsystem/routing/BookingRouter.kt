package com.laundrohem.bookingsystem.routing

import com.laundrohem.bookingsystem.handler.BookingHandler
import com.laundrohem.bookingsystem.service.IBookingService
import com.laundrohem.bookingsystem.service.IHouseholdService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.router

@Configuration
class BookingRouter(val bookingHandler: BookingHandler) {


    @Bean
    @RouterOperations (
        RouterOperation(
            path = "/booking",
            method = [RequestMethod.POST],
            operation = Operation(
                operationId = "create-booking",
                summary = "Creates a Booking",
                responses = [ApiResponse(responseCode = "201", description = "The Booking has been successfully created."),
                    ApiResponse(responseCode = "401", description = "The Booking submitted was not correctly formed.")],
                requestBody = RequestBody(description = "Booking", content = [Content(mediaType = "application/json")], required = true)
            ),
            beanClass = IBookingService::class,
            beanMethod = "createBooking"
        ),
        RouterOperation(
            path = "/booking",
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-all-bookings",
                summary = "Gets all the Bookings",
                responses = [ApiResponse(responseCode = "200", description = "The Bookings have been successfully retrieved."),
                    ApiResponse(responseCode = "404", description = "No Bookings were found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false),
            ),
            beanClass = IBookingService::class,
            beanMethod = "getAllBookings"
        ),
        RouterOperation(
            path = "/booking/{uuid}",
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-booking",
                summary = "Gets the Booking by UUID",
                parameters = [Parameter(name = "uuid", `in` = ParameterIn.PATH, required = true,
                    description = "The UUID of the Booking to be retrieved.")],
                responses = [ApiResponse(responseCode = "200", description = "The Booking has been successfully retrieved."),
                    ApiResponse(responseCode = "404", description = "The Booking selected has not been found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false),
            ),
            beanClass = IBookingService::class,
            beanMethod = "getBooking"
        ),
        RouterOperation(
            path = "/booking/{uuid}",
            method = [RequestMethod.PUT],
            operation = Operation(
                operationId = "update-booking",
                summary = "Updates an existing booking",
                parameters = [Parameter(name = "uuid", `in` = ParameterIn.PATH, required = true,
                    description = "UUID of the Booking to be updated")],
                responses = [ApiResponse(responseCode = "200", description = "The Booking has been successfully updated."),
                    ApiResponse(responseCode = "404", description = "The Booking selected for update has not been found.")],
                requestBody = RequestBody(description = "The updated Booking", content = [Content(mediaType = "application/json")], required = true)
            ),
            beanClass = IBookingService::class,
            beanMethod = "updateBooking"
        ),
        RouterOperation(
            path = "/booking/query",
            method = [RequestMethod.POST],
            operation = Operation(
                operationId = "query-bookings",
                summary = "Queries the existing Bookings based on the Booking details submitted",
                responses = [ApiResponse(responseCode = "200", description = "The Query produced results."),
                    ApiResponse(responseCode = "404", description = "Based on the query, no Bookings have been found.")],
                requestBody = RequestBody(description = "The example Booking query", content = [Content(mediaType = "application/json")], required = true)
            ),
            beanClass = IBookingService::class,
            beanMethod = "queryConfirmedBookings"
        ),
        RouterOperation(
            path = "/booking/{uuid}",
            method = [RequestMethod.DELETE],
            operation = Operation(
                operationId = "delete-booking",
                summary = "Removes the Booking",
                parameters = [Parameter(name = "uuid", `in` = ParameterIn.PATH, required = true,
                    description = "The UUID of the Booking to be removed.")],
                responses = [ApiResponse(responseCode = "204", description = "The Booking has been successfully deleted."),
                    ApiResponse(responseCode = "404", description = "The Booking selected for deletion has not been found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false)
            ),
            beanClass = IBookingService::class,
            beanMethod = "deleteBooking"
        ),

    )
    fun bookingRoutes() = router {
        ("/booking").nest {
            (POST("").invoke(bookingHandler::processBookingCreation))
            (GET("").invoke(bookingHandler::processGetAllBookings))
            (GET("/{uuid}").invoke(bookingHandler::processGetBooking))
            (POST("/query").invoke(bookingHandler::processBookingsQuery))
            (PUT("/{uuid}").invoke(bookingHandler::processBookingUpdate))
            (DELETE("/{uuid}").invoke(bookingHandler::processBookingDeletion))
        }
    }
}