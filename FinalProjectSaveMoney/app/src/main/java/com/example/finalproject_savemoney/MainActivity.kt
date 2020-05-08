package com.example.finalproject_savemoney

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject_savemoney.accounts.AccountsFragment
import com.example.finalproject_savemoney.categories.CategoriesFragment
import com.example.finalproject_savemoney.fragments.OnFragmentActionListener
import com.example.finalproject_savemoney.operations.OperationsFragment
import com.example.finalproject_savemoney.statistics.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), OnFragmentActionListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstVisit: Boolean = sharedPreferences.getBoolean(Companion.SAVED_STATE, false)
        if (!isFirstVisit) {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            true.saveFirstVisit()
        }
        setContentView(R.layout.activity_main)
        onOpenOperationsFragment()
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_categories -> onOpenCategoriesFragment()
                R.id.action_accounts -> onOpenAccountsFragment()
                R.id.action_operations -> onOpenOperationsFragment()
                R.id.action_statistics -> onOpenStatisticsFragment()
            }
            true
        })
    }

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(baseContext, R.string.backPressed_ru,
                    Toast.LENGTH_SHORT).show()
            back_pressed = System.currentTimeMillis()
        }
    }

    private fun Boolean.saveFirstVisit() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(Companion.SAVED_STATE, this)
        editor.apply()
    }

    override fun onOpenOperationsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, OperationsFragment.newInstance(), OperationsFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenAccountsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, AccountsFragment.newInstance(), AccountsFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenCategoriesFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CategoriesFragment.newInstance(), CategoriesFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenStatisticsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, StatisticsFragment.newInstance(), StatisticsFragment::class.java.simpleName)
                .commit()
    }

    companion object {
        private var back_pressed: Long = 0
        const val SAVED_STATE = "SAVED_STATE"
    }
}