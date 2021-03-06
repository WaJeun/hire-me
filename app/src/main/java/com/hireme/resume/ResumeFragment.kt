package com.hireme.resume

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hireme.R
import kotlinx.android.synthetic.main.fragment_resume.*

/**
 * A fragment that presents a list of resume items.
 */
class ResumeFragment : Fragment() {

    companion object {
        val TAG: String = ResumeFragment::class.java.name

        fun newInstance() = ResumeFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ResumeViewModel::class.java).apply { init() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadResumeItems().observe(this, Observer {
            recyclerView.adapter = ResumeAdapter(viewModel, it!!)
        })
        hireMeButton.setOnClickListener { viewModel.startYouAreHiredEmail() }
    }
}
