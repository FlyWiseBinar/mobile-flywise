package com.binar.projekakhir.model.checkout

data class PenumpangPost(

var ktppaspor: String,
var dateofbirth: String,
var citizenship: String,
var name: String,
var title: String,
)

data class PenumpangRequest(
    var ticketsId:String,
    var passengers:List<PenumpangPost>,
    var total_passenger:Int
)


data class Penumpang(
    var penumpang:String
)

