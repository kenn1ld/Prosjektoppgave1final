package no.uia.ikt205.mybooks.books.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Huskeliste(val title:String, val checkBox:Boolean = false): Parcelable
