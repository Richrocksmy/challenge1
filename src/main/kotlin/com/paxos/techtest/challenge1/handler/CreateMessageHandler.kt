package com.paxos.techtest.challenge1.handler

import com.google.common.hash.Hashing
import com.paxos.techtest.challenge1.domain.Message
import com.paxos.techtest.challenge1.filters.MessageAlreadyExistsException
import com.paxos.techtest.challenge1.repository.MessageRepository
import java.nio.charset.StandardCharsets

interface CreateMessageHandler {
    operator fun invoke(createMessage: CreateMessageDto): CreatedMessageDto
}

class CreateMessageHandlerImpl(var repository: MessageRepository) : CreateMessageHandler {

    override fun invoke(createMessage: CreateMessageDto): CreatedMessageDto {
        val message = Message(calculateDigest(createMessage.message), createMessage.message)

        repository.retrieve(message.digest)?.let { throw MessageAlreadyExistsException() }
        repository.save(message)

        return CreatedMessageDto(message.digest)
    }

    private fun calculateDigest(content: String): String {
        return Hashing.sha256()
            .hashString(content, StandardCharsets.UTF_8)
            .toString()
    }
}

data class CreateMessageDto(val message: String)

data class CreatedMessageDto(val digest: String)