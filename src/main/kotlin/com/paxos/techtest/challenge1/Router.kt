package com.paxos.techtest.challenge1;

import com.paxos.techtest.challenge1.handler.CreateMessageHandler
import com.paxos.techtest.challenge1.handler.RetrieveMessageHandler
import org.http4k.core.*
import org.http4k.lens.string
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

class Router (
    val createMessageHandler: CreateMessageHandler,
    val retrieveMessageHandler: RetrieveMessageHandler) {

    operator fun invoke(): RoutingHttpHandler = routes(
        "/messages/health" bind Method.GET to retrieveMesssage(),
        "/messages" bind Method.GET to createMesssage()
    )

    private fun retrieveMesssage() = {req: Request ->
        Body.string(ContentType.APPLICATION_JSON)
            .toLens()(retrieveMessageHandler(), Response(Status.OK))
    }

    private fun createMesssage() = {req: Request ->
        Body.string(ContentType.APPLICATION_JSON)
            .toLens()(createMessageHandler(), Response(Status.OK))
    }
}


