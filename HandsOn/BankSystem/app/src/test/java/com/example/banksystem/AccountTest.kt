package com.example.banksystem

import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AccountTest {
    private lateinit var account: Account

    @Before
    fun start() {
        account = Account(
            User(
                "Pedro",
                "111",
                "pedro@mail.com"
            ), 100f, "024-2", "001", 13
        )
    }

    @Test
    fun `when withdraw called - should decrease value from balance`() {
        account.withDraw(10f)

        Assert.assertEquals(90f, account.balance)
    }

    @Test
    fun `when deposition - should adding value in balance`() {
        account.deposit(50f)

        Assert.assertEquals(150f, account.balance)
    }
}