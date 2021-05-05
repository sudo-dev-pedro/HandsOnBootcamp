package br.com.handson5.workers

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.webkit.URLUtil
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.handson5.repository.MovieRepository

class DownloadWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val url = MovieRepository.URL

        Thread.sleep(1500)

        return if (URLUtil.isValidUrl(url)) {
            val downloadId: Long = downloadFile(url)

            if (downloadId == -1L) {
                return Result.failure()
            }

            val outputData: Data = Data.Builder()
                .putLong("key_download_id", downloadId)
                .build()

            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun downloadFile(url: String): Long {
        var result = -1L

        val downloadManager =
            applicationContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        downloadManager.let {
            val request = DownloadManager.Request(Uri.parse(url))
            result = downloadManager.enqueue(request)
        }

        return result
    }
}
