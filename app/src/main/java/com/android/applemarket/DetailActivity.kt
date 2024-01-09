package com.android.applemarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.applemarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 상단 뒤로가기 버튼 클릭 시 이벤트 처리
        // 현재 DetailActivity를 finish() 함
        // 어차피 MainActivity에서 뒤로가기 버튼 누르면 종료 다이얼로그가 뜨게 해놨으므로
        // startActivity로 넘어가지 않게 함
        binding.ibDetailBack.setOnClickListener {
            finish()
        }


    }
}