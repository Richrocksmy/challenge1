package com.paxos.techtest.challenge1

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ComponentTest {

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
    fun `Should be able to retrieve message`() {
        // Given
//        val expectedMessage;
        client(Request(Method.POST, "http://localhost:8080/messages"))

        // When
        val response = client(Request(Method.GET, "http://localhost:8080/messages/health"))

        // Then
        assertThat(response).isEqualTo(OK)
//        assertThat(response.body).isEqualTo(expectedMessage)
    }

    @Test
    fun `Should be able to store message`() {
        // Given

        // When
        val response = client(Request(Method.GET, "http://localhost:8080/messages"))

        // Then
        assertThat(response.status).isEqualTo(OK)
    }

    @Test
    fun `Should return bad request 400 when no message body supplied`() {
        // Given
        client(Request(Method.POST, "http://localhost:8080/messages"))

        // When
        val response = client(Request(Method.GET, "http://localhost:8080/messages/health"))

        // Then
        assertThat(response).isEqualTo(BAD_REQUEST)
    }

}