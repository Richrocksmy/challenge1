package com.paxos.techtest.challenge1.handler

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.paxos.techtest.challenge1.filters.MessageNotFoundException
import com.paxos.techtest.challenge1.repository.Message
import com.paxos.techtest.challenge1.repository.MessageRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RetrieveMessageHandlerImplTest {

    private val MESSAGE_CONTENT = "ThisIsAMessage"
    private val MESSAGE_DIGEST = "ThisIsAMessageDigest"

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var retrieveMessageHandler: RetrieveMessageHandler

    @BeforeEach
    fun setup() {
        retrieveMessageHandler = RetrieveMessageHandlerImpl(messageRepository)
    }

    @Test
    fun `Should return the message when digest is provided and the message exists`() {
        // Given
        var message = Message(MESSAGE_DIGEST, MESSAGE_CONTENT)
        whenever(messageRepository.retrieve(MESSAGE_DIGEST)).thenReturn(message)

        // When
        var returnedDto = retrieveMessageHandler(MESSAGE_DIGEST)

        // Then
        assertThat(returnedDto.message).isEqualTo(MESSAGE_CONTENT)

        verify(messageRepository).retrieve(MESSAGE_DIGEST)
    }

    @Test
    fun `Should throw not found exception the message does not exist`() {
        // Given
        whenever(messageRepository.retrieve(MESSAGE_DIGEST)).thenReturn(null)

        // When
        assertThrows<MessageNotFoundException> { retrieveMessageHandler(MESSAGE_DIGEST) }

        // Then
        verify(messageRepository).retrieve(MESSAGE_DIGEST)
    }
}
