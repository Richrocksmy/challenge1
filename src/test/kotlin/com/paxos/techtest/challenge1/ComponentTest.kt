package com.paxos.techtest.challenge1

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ComponentTest {

//    private val client = OkHttp()
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
//        assertThat(client(Request(Method.GET, "http://localhost:8080/ping")), hasStatus(OK))
    }

    @Test
    fun `Should be able to store message`() {
//        assertThat(client(Request(Method.GET, "http://localhost:8080/ping")), hasStatus(OK))
    }

}