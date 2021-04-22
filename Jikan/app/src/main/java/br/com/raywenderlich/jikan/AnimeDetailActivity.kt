package br.com.raywenderlich.jikan

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.raywenderlich.jikan.adapters.CharacterAdapter
import br.com.raywenderlich.jikan.databinding.ActivityAnimeDetailBinding
import br.com.raywenderlich.jikan.extensions.toRequestResult
import br.com.raywenderlich.jikan.handlers.RequestResultError
import br.com.raywenderlich.jikan.handlers.RequestResultNotFound
import br.com.raywenderlich.jikan.handlers.RequestResultSuccess
import br.com.raywenderlich.jikan.models.Anime
import br.com.raywenderlich.jikan.models.CharacterResult
import br.com.raywenderlich.jikan.services.AnimeService
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var animeDetailActivityBinding: ActivityAnimeDetailBinding
    private lateinit var view: View
    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var characterList: MutableList<CharacterResult>

    private val animeService: AnimeService by inject()
    private val characterAdapter: CharacterAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeDetailActivityBinding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        view = animeDetailActivityBinding.root

        setContentView(view)

        characterList = mutableListOf()
        characterList.clear()

        initViews()

        val anime = intent.getSerializableExtra("anime") as Anime
        showAnimeDetails(anime)

        dispatcherRequestStaff(anime.id)

    }

    private fun dispatcherRequestStaff(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            requestAnimeDetails()
        }
    }

    private suspend fun requestAnimeDetails() {

        val anime = intent.getSerializableExtra("anime") as Anime
        val animeId = anime.id

        when (val animeDetails = animeService.getDetails(animeId).toRequestResult()) {
            is RequestResultSuccess -> {

                showAnimeSinopsis(animeDetails.data.synopsis)

                requestAnimeCharactersAndStaff(animeId)
            }

            is RequestResultNotFound -> {
                animeDetailActivityBinding.txtCharacterStaff.text = animeDetails.errorMessage
            }

            is RequestResultError -> {
                animeDetailActivityBinding.txtCharacterStaff.text = "Erro genérico!"
            }
        }
    }

    private suspend fun requestAnimeCharactersAndStaff(id: Int) {

        when (val foundStaffData = animeService.getCharacterAndStaff(id).toRequestResult()) {
            is RequestResultSuccess -> {

                foundStaffData.data.staff.forEach {
                    characterList.add(it)
                }

                initAdapter()
                characterAdapter.updateList(characterList)
                animeDetailActivityBinding.txtCharacterStaff.visibility = View.VISIBLE
            }

            is RequestResultNotFound -> {
                animeDetailActivityBinding.txtCharacterStaff.text = foundStaffData.errorMessage
            }

            is RequestResultError -> {
                animeDetailActivityBinding.txtCharacterStaff.text = "Erro genérico!"
            }
        }
    }

    private fun showAnimeSinopsis(animeSinopsis: String) {
        animeDetailActivityBinding.txtSynopsis.text = animeSinopsis
    }

    private fun showAnimeDetails(anime: Anime) {
        title = anime.title

        Glide
            .with(this)
            .load(anime.imageUrl)
            .into(animeDetailActivityBinding.thumbnail)

        animeDetailActivityBinding.animeTitleSeparator.visibility = View.VISIBLE

        animeDetailActivityBinding.txtName.text = anime.title

        animeDetailActivityBinding.txtEpisodes.text = "Episode: ${anime.episode}"
        animeDetailActivityBinding.txtScore.text = " | Score: ${anime.score}"

        animeDetailActivityBinding.txtAiring.text = if (anime.airing) "| Yes" else "| No"
    }

    private fun initAdapter() {
        characterRecyclerView.adapter = characterAdapter
        characterRecyclerView.setHasFixedSize(true)
    }

    private fun initViews() {
        characterRecyclerView = animeDetailActivityBinding.rvCharacterOrStaff
    }
}