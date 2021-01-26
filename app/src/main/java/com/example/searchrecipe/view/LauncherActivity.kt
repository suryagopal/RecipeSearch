package com.example.searchrecipe.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.searchrecipe.MyApplication
import com.example.searchrecipe.R
import com.example.searchrecipe.databinding.ActivityLacuncherBinding
import com.example.searchrecipe.di.component.AppComponent
import com.example.searchrecipe.util.ProgressViewHelper

/**
 *  Launcher Activity contains fragments
 *  to show Food Recipes and its information
 */
class LauncherActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent
    private lateinit var dialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding = ActivityLacuncherBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchRecipeFragment.newInstance())
                .commitNow()
        }

        dialog = ProgressViewHelper.progressDialog(this@LauncherActivity)
    }

    fun showProgress() {
        dialog.show()
    }
    fun hideProgress() {
        dialog.dismiss()
    }

}

