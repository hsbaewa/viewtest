package com.example.viewtest

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.viewtest.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.btnAdd.setOnClickListener {
            val view = MainView(this)
            view.id = 12
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            binding.contents.addChildView(view)
        }

        binding.btnRemove.setOnClickListener {
            binding.contents.removeAllViews()
        }

        binding.btnRemoveInLayout.setOnClickListener {
            binding.contents.removeAllViewsInLayout()
        }
    }

    private fun ConstraintLayout.addChildView(v: View) {

        addView(
            v, ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        )

        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        constraintSet.connect(v.id, ConstraintSet.TOP, id, ConstraintSet.TOP)
        constraintSet.connect(v.id, ConstraintSet.START, id, ConstraintSet.START)
        constraintSet.connect(v.id, ConstraintSet.END, id, ConstraintSet.END)
        constraintSet.connect(v.id, ConstraintSet.BOTTOM, R.id.guideForButton, ConstraintSet.TOP)

        constraintSet.applyTo(this)

    }
}