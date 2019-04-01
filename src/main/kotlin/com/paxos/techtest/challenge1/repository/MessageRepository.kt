package com.paxos.techtest.challenge1.repository

import com.paxos.techtest.challenge1.domain.Message

interface MessageRepository {
    fun retrieve(digest: String): Message?

    fun save(message: Message)
}

class MessageRepositoryImpl : MessageRepository {

    private val persistence = HashMap<String, Message>()

    override fun retrieve(digest: String): Message? {
        return persistence[digest]
    }

    override fun save(message: Message) {
        persistence[message.digest] = message
    }
}