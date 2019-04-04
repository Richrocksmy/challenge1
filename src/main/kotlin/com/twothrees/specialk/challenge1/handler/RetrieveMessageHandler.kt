package com.twothrees.specialk.challenge1.handler

import com.twothrees.specialk.challenge1.filters.MessageNotFoundException
import com.twothrees.specialk.challenge1.repository.MessageRepository

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