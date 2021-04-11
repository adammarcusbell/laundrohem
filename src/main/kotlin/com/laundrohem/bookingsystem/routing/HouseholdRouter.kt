package com.laundrohem.bookingsystem.routing

import com.laundrohem.bookingsystem.handler.HouseholdHandler
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
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.router

@Configuration
class HouseholdRouter(val householdHandler: HouseholdHandler) {
    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/household",
            method = [RequestMethod.POST],
            operation = Operation(
                operationId = "create-household",
                summary = "Creates the Household",
                responses = [ApiResponse(responseCode = "201", description = "The Household has been successfully created."),
                    ApiResponse(responseCode = "401", description = "The Household submitted was not correctly formed.")],
                requestBody = RequestBody(description = "Household", content = [Content(mediaType = "application/json")], required = true)
            ),
            beanClass = IHouseholdService::class,
            beanMethod = "createHousehold"
        ),
        RouterOperation(
            path = "/household",
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-all-households",
                summary = "Gets all the Households",
                responses = [ApiResponse(responseCode = "200", description = "The Households have been successfully retrieved."),
                    ApiResponse(responseCode = "404", description = "No Households were found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false),
            ),
            beanClass = IHouseholdService::class,
            beanMethod = "getAllHouseholds"
        ),
        RouterOperation(
            path = "/household/{uuid}",
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-household",
                summary = "Gets the Household by UUID",
                parameters = [Parameter(name = "uuid", `in` = ParameterIn.PATH, required = true,
                    description = "The UUID of the Household to be retrieved.")],
                responses = [ApiResponse(responseCode = "200", description = "The Household has been successfully retrieved."),
                    ApiResponse(responseCode = "404", description = "The Household selected has not been found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false),
            ),
            beanClass = IHouseholdService::class,
            beanMethod = "getHousehold"
        ),
        RouterOperation(
            path = "/household/{uuid}",
            method = [RequestMethod.DELETE],
            operation = Operation(
                operationId = "delete-household",
                summary = "Removes the Household",
                parameters = [Parameter(name = "uuid", `in` = ParameterIn.PATH, required = true,
                    description = "The UUID of the Household to be removed.")],
                responses = [ApiResponse(responseCode = "204", description = "The Household has been successfully deleted."),
                    ApiResponse(responseCode = "404", description = "The Household selected for deletion has not been found.")],
                requestBody = RequestBody(description = "Not required", content = [Content()], required = false)
            ),
            beanClass = IHouseholdService::class,
            beanMethod = "deleteHousehold"
        )
    )
    fun householdRoutes() = router {
        ("/household").nest {
            (POST("").invoke(householdHandler::processCreateHousehold))
            (GET("/{uuid}").invoke(householdHandler::processGetHousehold))
            (GET("").invoke(householdHandler::processGetAllHouseholds))
            (DELETE("/{uuid}").invoke(householdHandler::processDeleteHousehold))
        }
    }
}