package com.paxos.techtest.challenge1.handler

import com.paxos.techtest.challenge1.filters.MessageNotFoundException
import com.paxos.techtest.challenge1.repository.MessageRepository

interface RetrieveMessageHandler {
    operator fun invoke(digest: String): MessageDto
}

class RetrieveMessageHandlerImpl(var repository: MessageRepository) : RetrieveMessageHandler {

    override fun invoke(digest: String): MessageDto {
        val message = repository.retrieve(digest) ?: throw MessageNotFoundException(digest)

        return MessageDto(message.content)
    }
}

data class MessageDto(val message: String)