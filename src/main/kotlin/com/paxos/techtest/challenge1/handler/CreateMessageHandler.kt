package com.paxos.techtest.challenge1.handler

import com.paxos.techtest.challenge1.domain.Message
import com.paxos.techtest.challenge1.repository.MessageRepository

interface CreateMessageHandler {
    operator fun invoke(message: Message): String
}

class CreateMessageHandlerImpl(var repository: MessageRepository) : CreateMessageHandler {

    override fun invoke(message: Message): String {
        return "Create"
    }
}