package com.paxos.techtest.challenge1

import com.paxos.techtest.challenge1.handler.CreateMessageHandlerImpl
import com.paxos.techtest.challenge1.handler.RetrieveMessageHandlerImpl
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    Router(CreateMessageHandlerImpl(), RetrieveMessageHandlerImpl())()
        .asServer(Jetty(8080))
        .start()
        .block()
}

