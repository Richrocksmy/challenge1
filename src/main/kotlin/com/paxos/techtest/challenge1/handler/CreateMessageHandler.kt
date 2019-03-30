package com.paxos.techtest.challenge1.handler

interface CreateMessageHandler {
    operator fun invoke(): String
}

class CreateMessageHandlerImpl : CreateMessageHandler {

    override fun invoke(): String {
        return "Create"
    }
}