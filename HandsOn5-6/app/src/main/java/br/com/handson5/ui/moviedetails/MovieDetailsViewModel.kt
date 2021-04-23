package br.com.handson5.ui.moviedetails

import android.content.Context
import androidx.lifecycle.*
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import br.com.handson5.data.Movie
import br.com.handson5.database.repository.MovieEntityRepository
import br.com.handson5.workers.DownloadWorker
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val moviesEntityRepository: MovieEntityRepository
) : ViewModel() {
    // Não é bom ser global nesse caso, pela questão do escopo.
//    private lateinit var workManager: WorkManager
//    private lateinit var workRequest: OneTimeWorkRequest

    private val _workResultInfo = MutableLiveData<Boolean>()
    val workResultInfo: LiveData<Boolean>
        get() = _workResultInfo

    private val _movieData = MutableLiveData<Movie>()
    val movieData: LiveData<Movie>
        get() = _movieData

    fun startDownloadWorker(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ) {
        val workManager = WorkManager.getInstance(context)
        val workRequest = OneTimeWorkRequest
            .Builder(DownloadWorker::class.java)
            .addTag(MovieDetailActivity.DOWNLOAD_WORKKER)
            .build()

        workManager.enqueueUniqueWork(
            MovieDetailActivity.DOWNLOAD_WORKKER,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )

        handleWorkResult(workManager, workRequest, lifecycleOwner)
    }

    private fun handleWorkResult(
        workManager: WorkManager,
        workRequest: OneTimeWorkRequest,
        lifecycleOwner: LifecycleOwner
    ) {
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(lifecycleOwner) {
            if (it?.state == WorkInfo.State.SUCCEEDED) {
                _workResultInfo.postValue(true)
            } else if (it?.state == WorkInfo.State.FAILED) {
                _workResultInfo.postValue(false)
            }
        }
    }

    fun updateFavorite(id: Long, value: Int) {
        viewModelScope.launch {
            moviesEntityRepository.updateFavorite(id, value)
        }
    }

    fun getMovie(id: Long) {
        viewModelScope.launch {
            _movieData.postValue(moviesEntityRepository.getMovie(id))
        }
    }

}