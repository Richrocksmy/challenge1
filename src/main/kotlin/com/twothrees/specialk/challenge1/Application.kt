package com.twothrees.specialk.challenge1

import com.twothrees.specialk.challenge1.handler.CreateMessageHandlerImpl
import com.twothrees.specialk.challenge1.handler.RetrieveMessageHandlerImpl
import com.twothrees.specialk.challenge1.repository.MessageRepositoryImpl
import org.http4k.server.ApacheServer
import org.http4k.server.Http4kServer
import org.http4k.server.asServer


fun main(args : Array<String>) {
    Application().start().block()
}

object Application {

    operator fun invoke(): Http4kServer {
        val messageRepository = MessageRepositoryImpl()

        return Router(CreateMessageHandlerImpl(messageRepository), RetrieveMessageHandlerImpl(messageRepository))()
            .asServer(ApacheServer(8080))
    }
}