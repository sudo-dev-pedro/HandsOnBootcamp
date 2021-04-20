package com.example.banksystem.domain

class Account(
        owner: User,
        balance: Float = 0.0f
) {
    var balance: Float = balance

    var owner: User = owner

    fun transferTo(destinationAccount: Account, transferValue: Float) {
        this.balance = this.balance.minus(transferValue)
        destinationAccount.balance.plus(transferValue)
    }
}