package com.example.sbma_project.internetConnection

sealed class ConnectionStatus {
    object Available : ConnectionStatus()
    object Unavailable: ConnectionStatus()
}