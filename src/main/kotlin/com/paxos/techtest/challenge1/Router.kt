package com.paxos.techtest.challenge1;

import com.paxos.techtest.challenge1.handler.CreateMessageHandler
import com.paxos.techtest.challenge1.handler.RetrieveMessageHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
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
        Response(OK).body(retrieveMessageHandler.toString())
    }

    private fun createMesssage() = {req: Request ->
        Response(OK).body(createMessageHandler.toString())
    }
}


