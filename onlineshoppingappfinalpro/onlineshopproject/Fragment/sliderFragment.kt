package com.example.onlineshopproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.onlineshopproject.R


class sliderFragment : Fragment() {

    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
        {
            position = requireArguments().getInt(ARG_POSITION)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set page background

        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.des)
        val image = view.findViewById<ImageView>(R.id.imageview)

        // set page title
        title.setText(PAGE_TITLE[position])

        //set page title description
        desc.setText(PAGE_DESCRIPTION[position])

        //set page image
        image.setImageResource(PAGE_IMAGE[position])
    }

    companion object {
        private const val ARG_POSITION = "slider-position"

        // prepare all title ids arrays
        private val PAGE_TITLE =
            intArrayOf(R.string.online_shopping, R.string.add_to_cart, R.string.payment_successful)
        private val PAGE_DESCRIPTION = intArrayOf(
            R.string.first_statement,
            R.string.add_to_cart_content,
            R.string.payment_content
        )
        private val PAGE_IMAGE = intArrayOf(R.drawable.online, R.drawable.cart, R.drawable.payment)


        /**
         * Use this factory method to create a new instance of
         *
         * @return A new instance of fragment SliderItemFragment.
         */

        fun newInstance(position: Int): sliderFragment {
            val fragment = sliderFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }


    }

}