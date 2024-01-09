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
    val itemPrice: String,
    val chat: Int,
    val like: Int
): Parcelable
