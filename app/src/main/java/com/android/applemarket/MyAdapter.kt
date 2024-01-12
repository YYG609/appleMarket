package com.android.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.ItemRecyclerviewBinding
import java.text.DecimalFormat

// MyAdapter가 생성될 때 mItems에 <MyItem>타입의 데이터리스트가 들어오게 함
class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    // 클릭 이벤트 추가 부분
    // 인터페이스는 함수에 대한 구현체는 없지만 이런 함수가 있다는 것을 정의해둔다, 실제 함수는 MainActivity에 있고 그 함수가 콜백되는 것
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    // 롱클릭 이벤트 추가 부분
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    // 인터페이스 타입의 itemClick 생성
    var itemClick: ItemClick? = null

    // 인터페이스 타입의 itemLongClick 생성
    var itemLongClick: ItemLongClick? = null

    // 리사이클러 뷰가 자동으로 호출시키는 메서드
    // viewHolder가 생성되는 함수, 여기서 veiwHolder 객체를 반환한다
    // 화면에서 보이는 뷰 개수만큼만 최초 생성된다.
    // 여기서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder() 함수의 매개변수로 전달된다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // inflate = xml 레이아웃 파일을 실제 View로 변환시켜준다 (xml 레이아웃 파일을 바로 넣는건 불가능해서 .kt로 바꿔주고 해야함)
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        Log.d("MyAdapter", "onCreateViewHolder()")
        return Holder(binding)
    }

    // 중요 //
    // 화면에 보여질 때만 실행된다
    // 재사용 될 때마다 불리는 메서드
    // 매개변수로 전달된 뷰 홀더 객체의 뷰에 데이터를 출력하거나 필요한 이벤트를 등록한다
    // 매개변수로 있는 position은 아이템 중 지금 아이템이 몇번째 아이템인지 알려준다
    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 상품 가격 1000단위 콤마(,)
        val dec = DecimalFormat("#,###")
        // 인터페이스
        // 클릭 이벤트 추가 부분(여기서 해도 됨) / 지금 이 코드에선 클릭이벤트 받아서 매인엑티비티에 보내주고 메인액티비티에서 처리함
        // 메인 액티비티로 보내주려면 메인액티비티랑 어뎁터 사이에 통신가능한 인터페이스를 생성해줘야 한다 = 16라인
//        Log.d("MyAdapter", "onBindViewHolder() position = $position")
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        // 롱클릭
        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }
        holder.itemImage.setImageResource(mItems[position].itemImage)
        holder.itemName.text = mItems[position].itemName
        holder.itemAddress.text = mItems[position].itemAddress
        holder.itemPrice.text = "${dec.format(mItems[position].itemPrice)}원"
        holder.chat.text = mItems[position].chat.toString()
        holder.like.text = mItems[position].like.toString()

        if (mItems[position].isLike) {
            holder.AdapterLike.setImageResource(R.drawable.heart2)
        } else {
            holder.AdapterLike.setImageResource(R.drawable.heart)
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    // 전체 Item의 개수를 묻는 메서드라 사이즈만큼 불러와야 한다
    override fun getItemCount(): Int {
        return mItems.size
    }

    // ItemRecyclerviewBinding = item_recyclerview.xml 뷰의 홀더, Holder가 계속해서 재활용 된다
    // 고정 // 리사이클러 뷰에 무조건 있어야되는 inner class
    inner class Holder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.ivItemImage
        val itemName = binding.tvItemName
        val itemAddress = binding.tvItemAddress
        val itemPrice = binding.tvItemPrice
        val chat = binding.tvChat
        val like = binding.tvHeart
        val AdapterLike = binding.ivHeart
    }
}