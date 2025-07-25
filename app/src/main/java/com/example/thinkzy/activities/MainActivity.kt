package com.example.thinkzy.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.thinkzy.R
import com.example.thinkzy.databinding.ActivityMainBinding
import com.example.thinkzy.fragments.HistoryFragment
import com.example.thinkzy.fragments.HomeFragment
import com.example.thinkzy.fragments.ProfileFragment
import com.example.thinkzy.fragments.SpinFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set default fragment (optional)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, HomeFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment())
                        .commit()
                    true
                }
                R.id.menu_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ProfileFragment())
                        .commit()
                    true
                }
                R.id.menu_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HistoryFragment())
                        .commit()
                    true
                }
                R.id.menu_spin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, SpinFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}