package com.dsp.androidsample

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dsp.androidsample.Logger.i
import com.dsp.androidsample.MainActivity.Companion.TAG
import kotlinx.android.synthetic.main.rotating_fragment.*
import org.jetbrains.annotations.TestOnly

class RotatingViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance()
    }
}

class RotatingFragment : Fragment() {
    companion object {
        fun newInstance() = RotatingFragment()
    }

    var viewModelFactory: ViewModelProvider.Factory? = RotatingViewModelFactory()
    lateinit var viewModel: RotatingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        i { "onCreate" }
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RotatingViewModel::class.java)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        i { "onCreateView" }
        return inflater.inflate(R.layout.rotating_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        i { "onViewCreated orientation=${resources.configuration.orientation}" }
        super.onViewCreated(view, savedInstanceState)
        textView_state.text =
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> "Portrait"
                Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
                else -> "Undefined"
            }
        button_finish.setOnClickListener {
            removeFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        i { "onActivityCreated" }
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        i { "onStart" }
        super.onStart()
    }

    override fun onResume() {
        i { "onResume" }
        super.onResume()
    }

    override fun onPause() {
        i { "onPause" }
        super.onPause()
    }

    override fun onStop() {
        i { "onStop" }
        super.onStop()
    }

    override fun onDestroy() {
        i { "onDestroy" }
        super.onDestroy()
    }

    private fun removeFragment() {
        activity?.let {
            it.supportFragmentManager.run {
                val fragment = findFragmentByTag(TAG)
                if (fragment != null) {
                    beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            }
        }
    }

    @TestOnly
    fun setTestViewModel(testViewModel: RotatingViewModel) {
        viewModel = testViewModel
    }

    @TestOnly
    fun getViewModel(): ViewModel = viewModel
}
