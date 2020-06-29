package com.demo.mvvmcleanarchitecture.ui

/**
 * Created by Safa NAOUI on 26/06/2020.
 */

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.demo.mvvmcleanarchitecture.R

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
