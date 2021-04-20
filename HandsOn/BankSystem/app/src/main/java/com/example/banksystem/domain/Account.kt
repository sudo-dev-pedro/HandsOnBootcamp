package com.example.banksystem.domain

class Account(
        _user: User,
        _balance: Float,
        _number: String,
        _agency: String,
        _type: Int
) {
    var user = _user
    var balance: Float = _balance
    val number: String = _number
    val agency: String = _agency
    val type: Int = _type

    fun deposit(value: Float) {
        if (isBalancePositive(balance) && isAValidTransaction(balance, value)) {
            balance += value
        }
    }

    fun withDraw(value: Float) {
        if (isBalancePositive(balance)) {
            balance -= value
        }
    }

    private fun isBalancePositive(balance: Float): Boolean {
        return balance > 0
    }

    private fun isAValidTransaction(balance: Float, value: Float): Boolean {
        return value < balance
    }

    fun transferTo(destinationAccount: Account, transferValue: Float) {
        this.balance = this.balance.minus(transferValue)
        destinationAccount.balance.plus(transferValue)
    }
}