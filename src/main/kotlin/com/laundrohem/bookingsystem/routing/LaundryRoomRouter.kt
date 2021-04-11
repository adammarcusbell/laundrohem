package com.laundrohem.bookingsystem.routing

import com.laundrohem.bookingsystem.handler.LaundryRoomHandler
import com.laundrohem.bookingsystem.service.ILaundryRoomService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.router

@Component
class LaundryRoomRouter(val laundryRoomHandler: LaundryRoomHandler) {

    @Bean
    @RouterOperations (
       RouterOperation(
           path = "/laundryroom",
           method = [RequestMethod.GET],
           operation = Operation(
               operationId = "get-all-laundryrooms",
               summary = "Gets all the Laundry Rooms",
               responses = [ApiResponse(responseCode = "200", description = "The Laundry Rooms have been successfully retrieved."),
                   ApiResponse(responseCode = "404", description = "No Laundry Rooms were found.")],
               requestBody = RequestBody(description = "Not required", content = [Content()], required = false),
           ),
           beanClass = ILaundryRoomService::class,
           beanMethod = "getAllLaundryRooms"
       )
    )
    fun laundryRoomRoutes() = router {
        ("/laundryroom").nest {
            (GET("").invoke(laundryRoomHandler::processGetAllLaundryRooms))
        }
    }
}