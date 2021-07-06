package com.vinoth.interview.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinoth.interview.PointsAdapter
import com.vinoth.interview.model.PointsViewModel
import com.vinoth.interview.R
import com.vinoth.interview.dao.FinalResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: PointsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility =  View.VISIBLE

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView?.context,
            LinearLayout.VERTICAL
        )
        recyclerView?.addItemDecoration(dividerItemDecoration)

        val heroesViewModel = ViewModelProvider.NewInstanceFactory().create(
            PointsViewModel::class.java
        )
        heroesViewModel.liveData.observe(this, { finalResponse: FinalResponse? ->

            Toast.makeText(this, finalResponse.toString(), Toast.LENGTH_SHORT).show()
            timeTaken.text = "TimeTaken to fetch both API : ${finalResponse?.timeTaken}ms"
            characterIn2API.text = "Number of characters in the 2nd API response : ${finalResponse?.users.toString().toCharArray().size}"

            progressBar.visibility =  View.GONE



            adapter = PointsAdapter(this, finalResponse?.posts ?: listOf())
            recyclerView?.adapter = adapter
        })
    }
}