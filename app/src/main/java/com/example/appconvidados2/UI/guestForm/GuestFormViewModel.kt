package com.example.appconvidados2.UI.guestForm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados2.service.model.GuestModel
import com.example.appconvidados2.service.repository.GuestRepository

                                                    //viewModel não tem contexo de android
class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    //o repositorio passa por aqui e fica salvo
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)
    //ao salvar o repositorifica salvo no Singleton
    private var mSaveGuest = MutableLiveData<Boolean>()
    // adiconar o LiveData á essa variaavel faz com que ela não possa ser alterada de fora, dando mais segurança
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence: Boolean,married: Boolean ) {
        val guest = GuestModel(name = name, presence = presence, married = married)
        mGuestRepository.save(guest)
    }
}