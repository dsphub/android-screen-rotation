package com.dsp.androidsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsp.androidsample.Logger.d

open class RotatingViewModel : ViewModel() {
    private val _isProgressVisible = MutableLiveData<Boolean>()
    val isProgressVisible: LiveData<Boolean>
        get() = _isProgressVisible

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    init {
        d { "init hcode=${this.hashCode()}" }
        _isProgressVisible.value = true
        _name.value = "Bill"
    }

    public override fun onCleared() { //FIXIT public access
        d { "onCleared" }
        super.onCleared()
    }
}
