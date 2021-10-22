package com.example.myapplication.search.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Search
import com.example.myapplication.moviedetail.ui.MovieDetailActivity
import com.example.myapplication.search.adapter.InstantSearchAdapter
import com.example.myapplication.search.viewmodel.SearchViewModel
import com.example.myapplication.utils.NetworkUtil
import com.example.myapplication.utils.getViewModel
import com.example.myapplication.utils.EventObserver
import com.example.myapplication.utils.APIStatus


class SearchActivity : AppCompatActivity(), InstantSearchAdapter.InstantSearchListener {

    private var dataBinding: ActivityMainBinding? = null
    private var mKeyboardOpened = true
    private var instantSearchList = ArrayList<Search>()
    private var instantSearchAdapter: InstantSearchAdapter? = null
    private var shouldCallApi = true

    private val viewModel: SearchViewModel by lazy {
        getViewModel { SearchViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding!!.lifecycleOwner = this
        dataBinding!!.viewModel = viewModel
        dataBinding!!.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(search: Editable?) {
                if (search.toString().isNotEmpty()) {
                    viewModel.shouldInstantSearchShow(true)
                    if (shouldCallApi) {
                        shouldCallApi = false
                        Handler().postDelayed({
                            shouldCallApi = true
//                        if (isAdded) {
                            viewModel.getInstantSearch(search.toString())
                                .observe(this@SearchActivity, EventObserver { resource ->
                                    when (resource.status) {
                                        APIStatus.SUCCESS -> {
                                            resource.data?.let { searchModel ->
                                                searchModel.Search?.let {
                                                    if (it.isNotEmpty()) {
                                                        instantSearchList.clear()
                                                        instantSearchList.addAll(it)
                                                        instantSearchAdapter!!.notifyDataSetChanged()
                                                    } else {
                                                        instantSearchList.clear()
                                                        instantSearchAdapter!!.notifyDataSetChanged()
                                                    }
                                                } ?: run {
                                                    instantSearchList.clear()
                                                    instantSearchAdapter!!.notifyDataSetChanged()

                                                }
                                            } ?: run {
                                                instantSearchList.clear()
                                                instantSearchAdapter!!.notifyDataSetChanged()

                                            }
                                        }
                                        APIStatus.LOADING -> {

                                        }
                                        APIStatus.ERROR -> {
                                            if (NetworkUtil.isConnectionAvailable(this@SearchActivity)) {
                                                Toast.makeText(
                                                    this@SearchActivity,
                                                    "internal server error",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            } else {
                                                Toast.makeText(
                                                    this@SearchActivity,
                                                    "Please check your internet connection",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            }
                                        }
                                    }

                                })
//                        }
                        }, 300)
                    }
                } else {
                    viewModel.shouldInstantSearchShow(false)
                }
            }

        })
        dataBinding!!.etSearch.setOnFocusChangeListener { v, hasFocus ->
            mKeyboardOpened = hasFocus
        }

        instantSearchAdapter = InstantSearchAdapter(
            instantSearchList,
            this
        )
        with(dataBinding!!.rvInstantSearch) {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = instantSearchAdapter
        }
    }

    override fun movieDetail(item: Search) {
        val intent = Intent(this@SearchActivity, MovieDetailActivity::class.java)
        intent.putExtra("movieDetail", item)
        startActivity(intent)
//        Toast.makeText(this, item.Title, Toast.LENGTH_SHORT).show()
    }

}