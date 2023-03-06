package com.sngular.rgarciavital.data.model

import java.io.Serializable


class ImageDetails : Serializable {
    var imageName: String? = null
    var imageUrl: String? = null

    constructor(imageName: String?, imageUrl: String?) {
        this.imageName = imageName
        this.imageUrl = imageUrl
    }

    constructor() {}
}