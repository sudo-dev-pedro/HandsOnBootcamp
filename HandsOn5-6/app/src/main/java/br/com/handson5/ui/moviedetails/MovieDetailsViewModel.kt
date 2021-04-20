package br.com.handson5.ui.moviedetails

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import br.com.handson5.workers.DownloadWorker

class MovieDetailsViewModel : ViewModel() {
    // Não é bom ser global nesse caso, pela questão do escopo.
//    private lateinit var workManager: WorkManager
//    private lateinit var workRequest: OneTimeWorkRequest

    private val _workResultInfo = MutableLiveData<Boolean>()
    val workResultInfo: LiveData<Boolean>
        get() = _workResultInfo

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

}