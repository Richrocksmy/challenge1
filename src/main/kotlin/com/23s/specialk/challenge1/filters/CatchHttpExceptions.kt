package com.paxos.techtest.challenge1.filters

import org.http4k.core.*
import org.http4k.format.Jackson.auto

data class GenericErrorModelBody(val body: List<String>)
data class GenericErrorModel(val errors: GenericErrorModelBody)

private val error = Body.auto<GenericErrorModel>().toLens()

fun createErrorResponse(status: Status, errorMessages: List<String>) =
    Response(status).with(error of GenericErrorModel(GenericErrorModelBody(errorMessages)))

open class HttpException(val status: Status, message: String = status.description) : RuntimeException(message)

class MessageAlreadyExistsException : HttpException(Status.CONFLICT, "The specified message already exists.")

class MessageNotFoundException(digest: String) :
    HttpException(Status.NOT_FOUND, "Message with digest $digest does not exist.")

object CatchHttpExceptions {

    operator fun invoke() = Filter { next ->
        {
            try {
                next(it)
            } catch (e: HttpException) {
                createErrorResponse(e.status, listOf(e.message ?: "Caught unhandled exception!"))
            }
        }
    }
}