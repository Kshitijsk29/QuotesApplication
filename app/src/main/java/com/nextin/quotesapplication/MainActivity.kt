package com.nextin.quotesapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var  mViewModel :MainViewModel

    private val quoteText :TextView
        get() = findViewById(R.id.tvQuote)

    private val quoteAuthor :TextView
        get() = findViewById(R.id.tvAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mViewModel = ViewModelProvider(this,MainViewModelFactory(application))[MainViewModel::class.java]

        setQuotes(mViewModel.getQuote())
    }

    private fun setQuotes(quotes: Quotes){
        quoteText.text = quotes.text
        quoteAuthor.text =quotes.author
    }

    fun onNext(view: View) {
        setQuotes(mViewModel.getNext())
    }
    fun onPrevious(view: View) {
        setQuotes(mViewModel.getPrevious())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mViewModel.getQuote().text)
        startActivity(intent)
    }
}