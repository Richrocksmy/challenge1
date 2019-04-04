package com.twothrees.specialk.challenge1;

import com.twothrees.specialk.challenge1.filters.CatchHttpExceptions
import com.twothrees.specialk.challenge1.handler.*
import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

class Router (
    private val createMessageHandler: CreateMessageHandler,
    private val retrieveMessageHandler: RetrieveMessageHandler
) {

    private val DIGEST = "digest"

    private val digestLens = Path.string().of(DIGEST)
    private val createMessageLens = Body.auto<CreateMessageDto>().toLens()
    private val createdMessageLens = Body.auto<CreatedMessageDto>().toLens()
    private val retrieveMessageLens = Body.auto<MessageDto>().toLens()

    operator fun invoke(): RoutingHttpHandler = CatchHttpExceptions()
        .then(ServerFilters.CatchLensFailure()
        .then(routes(
            "/messages/{$DIGEST:.*}" bind Method.GET to retrieveMessage(),
            "/messages" bind Method.POST to createMessage())
        ))

    private fun retrieveMessage() = {req: Request ->
        val response = retrieveMessageHandler(digestLens(req))
        retrieveMessageLens(response, Response(OK))
    }

    private fun createMessage() = {req: Request ->
        val created = createMessageHandler(createMessageLens(req))
        createdMessageLens(created, Response(OK))
    }
}