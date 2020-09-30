package com.hendra.movieapp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hendra.movieapp.MainActivity
import com.hendra.movieapp.R
import com.hendra.movieapp.utils.ErrorResource
import com.hendra.movieapp.utils.ResourceState
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), HasSupportFragmentInjector, View.OnClickListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginBtn: Button
    private lateinit var progress: ProgressBar
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn = findViewById(R.id.login)
        progress = findViewById(R.id.loading)
        emailEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)

        loginBtn.setOnClickListener(this)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.signInLiveData.observe(this, Observer {
            when (it?.status) {
                ResourceState.LOADING -> loading()
                ResourceState.ERROR -> it.errorResource?.let { error -> errorSignIn(error) }
                ResourceState.SUCCESS -> {
                    startActivity(MainActivity.startActivity(this))
                }
            }
        })
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onClick(view: View?) {
        loginViewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
    }

    private fun loading() {
        progress.visibility = View.VISIBLE
        loginBtn.isEnabled = false
    }

    private fun errorSignIn(errorType: ErrorResource) {
        progress.visibility = View.GONE
        loginBtn.isEnabled = true
        if (errorType == ValidationType.INVALID_EMAIL) {
            emailEditText.error = "Invalid Email"
        }
        if (errorType == ValidationType.INVALID_PASSWORD) {
            passwordEditText.error = "Invalid Password"
        }
    }
}