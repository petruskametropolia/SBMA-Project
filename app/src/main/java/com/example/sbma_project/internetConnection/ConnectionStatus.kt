package com.example.sbma_project.internetConnection

sealed class ConnectionStatus {
    data object Available : ConnectionStatus()
    data object Unavailable: ConnectionStatus()
}