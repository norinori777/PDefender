package com.google.norinori6791.pdefender

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.biometric.BiometricManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.norinori6791.pdefender.databinding.ActivityMainBinding
import com.google.norinori6791.pdefender.ui.add.AddFragment
import kotlinx.android.synthetic.main.app_bar_main.view.*

class MainActivity : AppCompatActivity(), MainNavigator  {

    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.drawerLayout.toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_top, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // 登録画面への遷移
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            viewModel.onFabClicked()
        }
        viewModel.moveAdd.observe(this, Observer<Boolean>{
            this.addNewAuthInfo()
        })

        biometricAuthentication()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // 登録画面
    override fun addNewAuthInfo() {
        Navigation.createNavigateOnClickListener(R.id.nav_add, null)
//        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, AddFragment()).commit()
//
//        if(supportFragmentManager.findFragmentById(R.id.nav_host_fragment) != null){
//            supportFragmentManager.beginTransaction()
//                .remove(supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!).commit()
//        }
////        val transaction = Fragment().fragmentManager?.beginTransaction()
//        val transaction = supportFragmentManager.beginTransaction()
//        val addFragment = AddFragment()
//
//        transaction?.replace(R.id.nav_host_fragment, addFragment)
//        transaction?.commit()
    }

    private fun biometricAuthentication(){
        // 生体認証
        val biometricManager = BiometricManager.from(this)
        when(biometricManager.canAuthenticate()){
            BiometricManager.BIOMETRIC_SUCCESS->
                Log.d("BIOMETRIC", "アプリは生体認証を使用して認証できます。")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->
                Log.d( "BIOMETRIC","このデバイスで、利用可能な生体認証機能はありません")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE->
                Log.d("BIOMETRIC", "バイオメトリック機能は現在利用できません。")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->
                Log.d("BIOMETTIC", "バイオメトリック認証情報を登録していません。")
        }
//        val executor = Executors.newSingleThreadExecutor()
//
//        val biometricPrompt = BiometricPrompt(this, executor,
//            object : BiometricPrompt.AuthenticationCallback() {
//                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                    super.onAuthenticationError(errorCode, errString)
//                    Log.d("Biometric", "Error")
//                    finish()
//                }
//
//                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    Log.d("Biometric", "Succeeded")
//                }
//
//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//                    Log.d("Biometric", "Failed")
//                }
//            })
//
//        val promptInfo = BiometricPrompt.PromptInfo.Builder()
//            .setTitle(getString(R.string.app_name))
//            .setSubtitle(getString(R.string.biometric_subtitle))
//            .setDescription(getString(R.string.biometric_description))
//            .setNegativeButtonText("Cancel")
//            .build()
//
//        biometricPrompt.authenticate(promptInfo)

    }
}
