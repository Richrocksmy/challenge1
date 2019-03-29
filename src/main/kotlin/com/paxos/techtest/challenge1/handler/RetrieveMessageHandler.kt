package com.paxos.techtest.challenge1.handler

interface RetrieveMessageHandler {
    operator fun invoke(): String
}

class RetrieveMessageHandlerImpl() : RetrieveMessageHandler {

    override fun invoke(): String {
        return "retrieve"
    }
}