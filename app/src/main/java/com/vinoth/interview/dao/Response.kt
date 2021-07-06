package com.vinoth.interview.dao

data class Posts(
    var userId: Int,
    var id: Int,
    var title: String?,
    var body: String?
)

data class Root(
    var id: Int,
    var name: String?,
    var username: String?,
    var email: String?,
    var address: Address?,
    var phone: String?,
    var website: String?,
    var company: Company?
)

data class Company(
    var name: String?,
    var catchPhrase: String?,
    var bs: String?
)

class Address(
    var street: String?,
    var suite: String?,
    var city: String?,
    var zipcode: String?,
    var geo: Geo?
)

class Geo(
    var lat: String?,
    var lng: String?
)

data class FinalResponse(
    val posts: List<Posts>?,
    val users: List<Root>?,
    var timeTaken: Long? = 0
)