package com.android.applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyItem(
    val itemImage: Int,
    val itemName: String,
    val itemExplain: String,
    val itemSeller: String,
    val itemAddress: String,
    val itemPrice: Int,
    val chat: Int,
    var like: Int,
    var isLike : Boolean
) : Parcelable
