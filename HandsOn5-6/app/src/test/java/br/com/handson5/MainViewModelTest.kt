package br.com.handson5

import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import androidx.lifecycle.Observer
import br.com.handson5.data.Movie
import br.com.handson5.di.*
import br.com.handson5.repository.MovieApi
import br.com.handson5.repository.MovieRepository
import com.squareup.moshi.Moshi
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection.HTTP_OK

class MainViewModelTest : KoinTest {

    //    private val mainViewModel: MainViewModel by inject()
    private val movieApi: MovieApi by inject()

    private lateinit var mockWebServer: MockWebServer

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
            listOf(
                appModule
            )
        )
    }

    @MockK
    private lateinit var moviesObserver: Observer<List<Movie>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

//        mainViewModel.moviesLiveData.observeForever(moviesObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()

        val module = module(createdAtStart = true, override = true) {
            single(named(MOSHI)) {
                Moshi
                    .Builder()
                    .build()
            }

            single(named(RETROFIT)) {
                Retrofit.Builder()
                    .addConverterFactory(
                        MoshiConverterFactory
                            .create(get(named(MOSHI)))
                    )
                    .baseUrl(MovieRepository.URL)
                    .build()
            }

            single {
                get<Retrofit>(named(RETROFIT))
                    .create(MovieApi::class.java)
            }

        }

        loadKoinModules(module)
    }

    @Test
    fun `teste`() {
        val response = MockResponse()
            .setResponseCode(HTTP_OK)
            .setBody(
                JsonReader.getJson(path = "E:\\Projetos\\HandsOnBootcamp\\HandsOnBootcamp\\HandsOn5-6\\app\\src\\test\\java\\br\\com\\handson5\\movieResponse.json")
            )

        mockWebServer.enqueue(response)

        val actualResponse = movieApi.getMovies().execute()

        assertEquals(
            response
                .toString()
                .contains("200"),
            actualResponse
                .code()
                .toString()
                .contains("200")
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}