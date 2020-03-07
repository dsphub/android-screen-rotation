package com.dsp.androidsample

import androidx.lifecycle.ViewModel
import com.dsp.androidsample.Logger.d

open class RotatingViewModel : ViewModel() {
    init {
        d { "init hcode=${this.hashCode()}" }
    }

    public override fun onCleared() { //FIXIT public access
        d { "onCleared" }
        super.onCleared()
    }
}
