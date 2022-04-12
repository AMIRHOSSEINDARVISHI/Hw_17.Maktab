package code.with.cal.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import code.with.cal.moviesapp.databinding.ActivityMainBinding
import code.with.cal.moviesapp.R


class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.myNavHostFragment) }

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavigationUI.setupWithNavController(binding.bottomNav,navController)



    }
}