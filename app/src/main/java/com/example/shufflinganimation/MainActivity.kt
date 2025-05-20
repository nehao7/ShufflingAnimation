package com.example.shufflinganimation

import BounceItemAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shufflinganimation.databinding.ActivityMainBinding
import java.util.Collections

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var arrayList=arrayListOf<String>("One","Two","Three","Four","Six","Seven","Eight","Nine","Ten","Eleven",)
    lateinit var shuffleAdapter: ShuffleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.image.setOnClickListener {
            shuffleAdapter= ShuffleAdapter(arrayList)
            binding.rvShuffle.layoutManager= GridLayoutManager(this,4)
            binding.rvShuffle.adapter= shuffleAdapter
            binding.rvShuffle.layoutAnimation= AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)

        }

        binding.btnshuffle.setOnClickListener {
            binding.rvShuffle.postDelayed({ shuffleWithAnimation() }, 1000)
            binding.rvShuffle.itemAnimator= BounceItemAnimator()
        }
        binding.btnSlideIn.setOnClickListener {
            arrayList.shuffle()
            shuffleWithAnimation()
            val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation)
            binding.rvShuffle.layoutAnimation = controller
            binding.rvShuffle.scheduleLayoutAnimation()
            shuffleAdapter.notifyDataSetChanged()
        }
        binding.btnFallDown.setOnClickListener {
            arrayList.shuffle()
            shuffleWithAnimation()
            val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
            binding.rvShuffle.layoutAnimation = controller
            binding.rvShuffle.scheduleLayoutAnimation()
            shuffleAdapter.notifyDataSetChanged()
        }
        binding.btnRotateIn.setOnClickListener {
            arrayList.shuffle()
            shuffleWithAnimation()
            val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_rotate_in)
            binding.rvShuffle.layoutAnimation = controller
            binding.rvShuffle.scheduleLayoutAnimation()
            shuffleAdapter.notifyDataSetChanged()
        }
        binding.btnScaleUP.setOnClickListener {
            arrayList.shuffle()
            shuffleWithAnimation()
            val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_scale_up)
            binding.rvShuffle.layoutAnimation = controller
            binding.rvShuffle.scheduleLayoutAnimation()
            shuffleAdapter.notifyDataSetChanged()
        }
    }

    fun shuffleWithAnimation() {
        val oldList = arrayList.toMutableList()
        val newList = oldList.shuffled()

        for (i in oldList.indices) {
            val fromIndex = i
            val toIndex = newList.indexOf(oldList[i])
            if (fromIndex != toIndex) {
                Collections.swap(arrayList, fromIndex, toIndex)
                shuffleAdapter.notifyItemMoved(fromIndex, toIndex)
            }
        }
    }

}