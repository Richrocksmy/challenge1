package com.twothrees.specialk.challenge1

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.twothrees.specialk.challenge1.handler.CreateMessageDto
import com.twothrees.specialk.challenge1.handler.CreatedMessageDto
import com.twothrees.specialk.challenge1.handler.MessageDto
import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.format.Jackson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ComponentTest {

    private val MESSAGE = "message"
    private val BASE_URL = "http://localhost:8080"
    private val ROUTE = "/messages"

    private val client = OkHttp()
    private val server = Application()

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test
    fun `Should be able to store and retrieve message`() {
        // Given
        val createMessageDto = CreateMessageDto(MESSAGE)

        // When
        val response = client(Request(Method.POST, "$BASE_URL$ROUTE")
            .body(Jackson.asJsonString(createMessageDto)))

        assertThat(response.status).isEqualTo(Status.OK)

        // Then
        val digest = Jackson.asA(response.bodyString(), CreatedMessageDto::class).digest
        val result = client(Request(Method.GET, "$BASE_URL$ROUTE/$digest"))

        assertThat(result.status).isEqualTo(Status.OK)
        assertThat(Jackson.asA(result.bodyString(), MessageDto::class).message).isEqualTo(MESSAGE)
    }

    @Test
    fun `Should return bad request 400 when no message body supplied`() {
        assertThat(client(Request(Method.POST, "$BASE_URL$ROUTE")
            .body("")).status).isEqualTo(BAD_REQUEST)
    }

    @Test
    fun `Should return not found 404 when no message found`() {
       assertThat(client(Request(Method.GET, "$BASE_URL/$ROUTE/1234")).status).isEqualTo(NOT_FOUND)
    }
}