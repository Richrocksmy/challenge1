package com.paxos.techtest.challenge1.handler

import com.google.common.hash.Hashing
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.paxos.techtest.challenge1.domain.Message
import com.paxos.techtest.challenge1.filters.MessageAlreadyExistsException
import com.paxos.techtest.challenge1.repository.MessageRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.nio.charset.StandardCharsets

@ExtendWith(MockitoExtension::class)
class CreateMessageHandlerImplTest {

    private val MESSAGE_CONTENT = "ThisIsAMessage"

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var createMessageHandler: CreateMessageHandler

    @BeforeEach
    fun setup() {
        createMessageHandler = CreateMessageHandlerImpl(messageRepository)
    }

    @Test
    fun `Should calculate the message digest from message content and store the message`() {
        // Given
        val messageDto = CreateMessageDto(MESSAGE_CONTENT)
        whenever(messageRepository.retrieve(any())).thenReturn(null)

        // When
        createMessageHandler(messageDto)

        // Then
        val expectedMessage =  Message(calculateDigest(MESSAGE_CONTENT), MESSAGE_CONTENT)
        verify(messageRepository).save(expectedMessage)
    }

    @Test
    fun `Should throw exception when attempting to store duplicate message`() {
        // Given
        val messageDto = CreateMessageDto(MESSAGE_CONTENT)
        whenever(messageRepository.retrieve(any()))
            .thenReturn(Message(calculateDigest(MESSAGE_CONTENT), MESSAGE_CONTENT))

        // When / Then
        assertThrows<MessageAlreadyExistsException> {  createMessageHandler(messageDto) }

        verify(messageRepository, never()).save(any())
    }

    private fun calculateDigest(content : String): String {
        return Hashing.sha256().hashString(content, StandardCharsets.UTF_8).toString()
    }

}
