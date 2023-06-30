package com.binar.projekakhir.model.checkout

data class PenumpangPost(

var AgeType: String,
var dateofbirth: String,
var expiredat: String,
var issuingcountry: String,
var ktp : String,
var name : String,
var nationality : String,
var passport : String
)

data class ticket(
    var id : Int
)

data class PenumpangRequest(
    val schedule : List<ticket>,
    val passenger:List<PenumpangPost>,

)


data class Penumpang(
    var penumpang:String,
)

