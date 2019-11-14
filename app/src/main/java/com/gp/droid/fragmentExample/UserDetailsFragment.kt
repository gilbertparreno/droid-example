package com.gp.droid.fragmentExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gp.droid.R
import com.gp.droid.core.extensions.inflate
import kotlinx.android.synthetic.main.fragment_user_details.view.*

class UserDetailsFragment : Fragment() {

    lateinit var nameWithInitial: String
    lateinit var position: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_user_details)?.also { contentView ->
            contentView.labelNameWithInitial.text = nameWithInitial
            contentView.labelPosition.text = position
        }
    }

    companion object {
        val TAG = UserDetailsFragment::class.simpleName
    }
}