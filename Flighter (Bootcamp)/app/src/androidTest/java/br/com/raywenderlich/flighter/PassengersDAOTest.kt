package br.com.raywenderlich.flighter

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.AppDatabase
import br.com.raywenderlich.flighter.database.entity.Passenger
import br.com.raywenderlich.flighter.database.data.PassengersProvider
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PassengersDAOTest {
    @Rule
    @JvmField
            /*
            Tudo será executado na Main Thread para que sejam executados de forma síncrona e sequencial.
            */
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var passengerDAO: PassengerDAO

    // Before significa dizer que essa fun deverá ser executada antes de QUALQUER teste!
    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        try {
            /*
            Criação do banco de dados em memória, sendo assim TODOS os dados serão
            apagados de forma segura ao final do teste!
            */
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                // Sem essa permissão o Room lança um erro!
                .allowMainThreadQueries()
                .build()

        } catch (e: Exception) {
            e.message?.let { errorMessage ->
                Log.i(this.javaClass.simpleName, errorMessage)
            }
        }
        passengerDAO = database.passengerDAO()
    }

    @Test
    fun testInsertQuestion() {
        val passenger = Passenger(
            234,
            "222",
            "ph@email.com",
            "12345",
            "P",
            "H",
            "12/01/1990",
            null
        )

        passengerDAO.insert(passenger)

        val numberOfPassengers = passengerDAO.getAllPassengers().size

        Assert.assertEquals(1, numberOfPassengers)
    }

    @Test
    fun testClearPassengers() {
        for (passenger in PassengersProvider.passengersList) {
            runBlocking {
                passengerDAO.insert(passenger)
            }

        }
        Assert.assertTrue(passengerDAO.getAllPassengers().isNotEmpty())
        Log.d("testData", passengerDAO.getAllPassengers().toString())

        passengerDAO.clearTable()
        Assert.assertTrue(passengerDAO.getAllPassengers().isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }
}