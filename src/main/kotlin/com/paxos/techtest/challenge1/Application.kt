package com.paxos.techtest.challenge1

import com.paxos.techtest.challenge1.handler.CreateMessageHandlerImpl
import com.paxos.techtest.challenge1.handler.RetrieveMessageHandlerImpl
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import org.http4k.server.asServer


fun main() {
    Application().start().block()
}

object Application {

    operator fun invoke(): Http4kServer {
        return Router(CreateMessageHandlerImpl(), RetrieveMessageHandlerImpl())()
            .asServer(Jetty(8080))
    }
}