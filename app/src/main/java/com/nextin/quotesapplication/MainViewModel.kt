package com.nextin.quotesapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MainViewModel(private val  context: Context):ViewModel() {

    private var quoteList: Array<Quotes> = emptyArray()

    private var index :Int = 0

    init {
        quoteList = getQuotesFromAssets()
    }

    private fun getQuotesFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer , Charsets.UTF_8)
        val gson = Gson()
        return  gson.fromJson(json,Array<Quotes>::class.java)
    }
    fun getQuote() = quoteList[index]

    fun getNext()=quoteList[++index % quoteList.size]

    fun getPrevious() =quoteList[(--index + quoteList.size) % quoteList.size]

}