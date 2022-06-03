package com.ntl.guidelinesapp.modules.coroutines.multiplerequest.cocurrency


import androidx.lifecycle.viewModelScope
import com.ntl.guidelinesapp.modules.coroutines.base.BaseViewModel
import com.ntl.guidelinesapp.modules.coroutines.common.Logger
import kotlinx.coroutines.*
import java.lang.Exception


class ConcurrencyViewModel : BaseViewModel() {

    private var parentJob: Job? = null
    override fun fetchData() {

        parentJob = viewModelScope.launch(parentExceptionHandler) {
            Logger.log("Show loading")


            //todo request song song
            //todo chay ca 2 cai cung luc va khong doi nhau
            /*val job1 =  launch {
                repository.requestWithIndex(1)
            }

            val job2 = launch {
                repository.requestWithIndex(2)
            }*/
            //-------

            /*//todo doi ca 2 cung hoan thanh: dung async sau do awaitAll()
            //todo banner
            val job1 = async {
                repository.requestWithIndex(1, true)
            }

            //todo product
            val job2 = async {
                repository.requestWithIndex(2)

            }

            Logger.log("Start")
            val list = listOf(job1, job2).awaitAll()
            Logger.log("End")
            list.forEach {
                Logger.log("Result $it")
            }*/
            //-------

            //todo dung supervisorScope de then nao ok thi lay, then nao fail bo qua
            // doc lap voi nhau
            /*supervisorScope {
                //todo banner
                launch {
                    repository.requestWithIndex(1, true)
                }

                //todo product
                launch{
                    repository.requestWithIndex(2)

                }
            }*/
            //-------

            //todo try request
//            failAndReTry()

            //todo save to db
            val result = repository.requestWithIndex(1)

            withContext(NonCancellable){
                //bam back van tiep tuc save chu ko huy
                //ex: loa dong data nhung chua kip luu thi thoat man hinh
                //van tiep tuc save data du thoat man hinh
                repository.saveToDB(result *6)

            }
        }

        parentJob?.invokeOnCompletion {
            //todo neu co exception isThrowException = true, thi lay loi o day
            // job1 loi thi job2 se khong chay
            Logger.log("Hide loading")
        }
    }

    fun cancelAll() {
        parentJob?.cancel()
    }

    private suspend fun failAndReTry(): Int {
        repeat(3) {
            try {
                return repository.requestWithIndex(1, true)
            } catch (e: Exception) {
                Logger.log("Fail anh retry: ${e.message}")
            }
        }
        return repository.requestWithIndex(2)
    }
}