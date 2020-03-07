package com.dsp.androidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dsp.androidsample.Logger.i

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()
    }

    private fun showFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, RotatingFragment.newInstance(), TAG)
//                .addToBackStack(null)
                .commit()
        }
    }

    fun showFragment(fragment: Fragment) {
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, TAG)
//                .addToBackStack(null)
                .commit()
        }
    }

    fun removeFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG)
        if (fragment != null) {
            // don't work
//            supportFragmentManager.beginTransaction()
//                .remove(fragment)
//                .commitAllowingStateLoss()
            // ok
//            onBackPressed()
            // ok
            supportFragmentManager.popBackStack()
        }
    }

    override fun onResume() {
        i { "onResume f.size=${supportFragmentManager.fragments.size}" }
        super.onResume()
    }
}
