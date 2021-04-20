package com.example.banksystem.usecase

import com.example.banksystem.domain.Account

class ValidateAccountUseCase {
    fun checkBalanceForTransaction(account: Account, value: Float): Boolean {
        return account.balance - value > 0
    }

    fun accountExists(accounts: List<Account>, cpf: String): Account? {
        return accounts.firstOrNull {
            it.user.cpf == cpf
        }
    }
}