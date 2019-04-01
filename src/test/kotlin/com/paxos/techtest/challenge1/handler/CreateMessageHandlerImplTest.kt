package com.paxos.techtest.challenge1.handler

import com.paxos.techtest.challenge1.repository.MessageRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

object CreateMessageHandlerImplTest {

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var createMessageHanlder: CreateMessageHandler

    @BeforeEach
    fun setup() {
        createMessageHanlder = CreateMessageHandlerImpl(messageRepository)
    }

    @Test
    fun `Should store the message in encrypted format`() {
        // Given
//        val message;
//        when

        // When
//        val result = createMessageHanlder(message)

        // Then
//        assertThat(result.id).isNotNull()
//        assertThat(result.hash).isEqualTo(hash)
    }

}
