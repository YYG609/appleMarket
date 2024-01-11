package com.android.applemarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private var isLike = false
    private val itemPosition: Int by lazy {
        intent.getIntExtra(Const.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 상품 가격 1000단위 콤마
        val dec = DecimalFormat("#,###")

        // MyItem에서 버전에 따른 getParcelableExtra<> if else문의 중복을 줄이기 위해
        // 데이터를 가지고 온 후, MyItem에서 버전에 따른 데이터 활용
        with(binding) {
            MyItem?.let { ivDetailItemImage.setImageResource(it.itemImage) }
            tvDetailItemSeller.text = MyItem?.itemSeller
            tvDetailItemAddress.text = MyItem?.itemAddress
            tvDetailItemName.text = MyItem?.itemName
            tvDetailItemExplain.text = MyItem?.itemExplain
            tvDetailItemPrice.text = "${dec.format(MyItem?.itemPrice)}원"
            isLike = MyItem?.isLike == true
            ivDetailLikeIcon.setImageResource(
                if (isLike) {
                    R.drawable.heart2
                } else {
                    R.drawable.heart
                }
            )

            // 상단 뒤로가기 버튼 클릭 시 이벤트 처리
            ibDetailBack.setOnClickListener {
                exit()
            }
            ivDetailLikeIcon.setOnClickListener {
                if (!isLike) {
                    binding.ivDetailLikeIcon.setImageResource(R.drawable.heart2)
                    Snackbar.make(binding.clDetailWhole, "관심 목록에 추가되었습니다", Snackbar.LENGTH_SHORT)
                        .show()
                    isLike = true
                } else {
                    binding.ivDetailLikeIcon.setImageResource(R.drawable.heart)
                    isLike = false
                }
            }
        }
    }

    // 23라인에서 가져온 데이터를 버전에 따라 나누는 작업은 여기서
    private val MyItem: MyItem? by lazy {
        // MainActivity에서 Intent로 넘겨준 데이터 DetailActivity에서 받기
        // getParcelableExtra<클래스타입>으로 Parcelable 객체를 전달 받는다
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Const.ITEM_OBJECT, com.android.applemarket.MyItem::class.java)
        } else {
            intent.getParcelableExtra<MyItem>(Const.ITEM_OBJECT)
        }
    }

    // 뒤로가기 버튼 exit() 정의
    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }
}