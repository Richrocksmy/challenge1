package com.paxos.techtest.challenge1.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MessageRepositoryImplTest {

    private val MESSAGE_CONTENT = "ThisIsAMessage"
    private val MESSAGE_DIGEST = "ThisIsAMessageDigest"

    private lateinit var messageRepository: MessageRepository

    @BeforeEach
    fun setup() {
        messageRepository = MessageRepositoryImpl()
    }

    @Test
    fun `Should return saved message when message exists in db`() {
        // Given
        val message = Message(MESSAGE_DIGEST, MESSAGE_CONTENT)
        messageRepository.save(message)

        // When
        val retrievedMessage = messageRepository.retrieve(MESSAGE_DIGEST)

        // Then
        assertThat(retrievedMessage).isEqualTo(message)
    }

    @Test
    fun `Should return empty when message does not exist in db`() {
        // When
        val retrievedMessage = messageRepository.retrieve(MESSAGE_DIGEST)

        // Then
        assertThat(retrievedMessage).isNull()
    }

    @Test
    fun `Should save message in db`() {
        // Given
        val message = Message(MESSAGE_DIGEST, MESSAGE_CONTENT)

        // When
        messageRepository.save(message)

        // Then
        assertThat(messageRepository.retrieve(message.digest)).isEqualTo(message)
    }

    @Test
    fun `Should overwrite existing message in db when saving duplicate`() {
        // Given
        val message1 = Message(MESSAGE_DIGEST, MESSAGE_CONTENT)
        messageRepository.save(message1)

        val message2 = Message(MESSAGE_DIGEST, MESSAGE_CONTENT + "2")

        // When
        messageRepository.save(message2)

        // Then
        assertThat(messageRepository.retrieve(message2.digest)).isEqualTo(message2)
    }
}