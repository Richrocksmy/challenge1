package com.paxos.techtest.challenge1.handler

import com.paxos.techtest.challenge1.repository.MessageRepository

interface RetrieveMessageHandler {
    operator fun invoke(): String
}

class RetrieveMessageHandlerImpl(messageRepository: MessageRepository) : RetrieveMessageHandler {

    override fun invoke(): String {
        return "retrieve"
    }
}