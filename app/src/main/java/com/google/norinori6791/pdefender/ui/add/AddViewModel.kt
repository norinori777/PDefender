package com.google.norinori6791.pdefender.ui.add

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import com.google.norinori6791.pdefender.model.repository.AuthInfoItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AddViewModel : BaseObservable() {
    val title = ObservableField<String>("")
    val category = ObservableField<Int>()
    val url = ObservableField<String>("")
    val uid = ObservableField<String>("")
    val password = ObservableField<String>("")
    val memo = ObservableField<String>("")

    val onCancel = MutableLiveData<Boolean>()
    val onAdd = MutableLiveData<Boolean>()

    fun validate(){

    }

    fun addAuthInfo(){
        val authInfo = AuthInfo(
            null,
            1,
            title.get().toString(),
            url.get().toString(),
            uid.get().toString(),
            password.get().toString(),
            memo.get().toString()
        )
        Observable.create<AuthInfo>{
            try {
                it.onNext(authInfo)
                it.onComplete()
            } catch(e:Exception){
                it.onError(Throwable("エラーになりました"))
            }

        }
            .subscribeOn(Schedulers.newThread())
            .doOnNext{
                val addInstance = AuthInfoItem(it)
                addInstance.addAuthInfo()
            }
            .doOnError {
                Log.v("null", "onError")
            }
            .doOnComplete {
                afterAddAuthInfo()
            }
            .subscribe({}, {})
    }

    private fun afterAddAuthInfo(){
        onAdd.postValue(true)

    }
    fun clickCancel(){
        onCancel.postValue(true)
    }
}
