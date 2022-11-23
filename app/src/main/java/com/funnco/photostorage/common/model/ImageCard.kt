package com.funnco.photostorage.common.model

class ImageCard() {

    var authorUUID : String? = null
    lateinit var imageURI: String
    lateinit var imageName: String

    constructor(
        authorUUID: String?,
        imageURI: String,
        imageName: String
    ) : this(){
        this.authorUUID = authorUUID
        this.imageName = imageName
        this.imageURI = imageURI
    }
}