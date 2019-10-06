package com.google.norinori6791.pdefender

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.norinori6791.pdefender.databinding.ActivityMainBinding
import com.google.norinori6791.pdefender.ui.add.AddFragment
import com.google.norinori6791.pdefender.util.MainViewModel
import kotlinx.android.synthetic.main.app_bar_main.view.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.drawerLayout.toolbar)

        viewModel.moveAdd.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
            }
        })
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }



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

        // 生体認証
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
//    private fun moveFragment(viewid: Int){
//        val transaction = FragmentManager.fragmentManager?.beginTransaction()
//        val addFragment = AddFragment()
//
//        transaction?.replace(viewid, addFragment)
//        transaction?.commit()
//    }
}
