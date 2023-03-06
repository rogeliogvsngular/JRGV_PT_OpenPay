package com.sngular.rgarciavital.data.repositoty

import androidx.lifecycle.LiveData
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase
import com.sngular.rgarciavital.data.model.ImageDetails


class ImageRepository {}/*{
    private var storageReference: StorageReference? = null
    private var databaseReference: DatabaseReference? = null
    private var imageDetailsList: MutableList<ImageDetails>? = null
    fun setupFirebase() {
        storageReference = FirebaseStorage.getInstance().getReference("uploads")
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads")
    }

    val imagesList: LiveData<Any>
        get() {
            imageDetailsList = ArrayList()
            return LiveDataReactiveStreams.fromPublisher(
                Flowable.create({ emitter: FlowableEmitter<Any?> ->
                    databaseReference.addValueEventListener(object : ValueEventListener() {
                        fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                            imageDetailsList!!.clear()
                            Log.e("Snap", "onDataChange: $dataSnapshot")
                            for (imagesSnapshot in dataSnapshot.getChildren()) {
                                val imageDetails: ImageDetails =
                                    imagesSnapshot.getValue(ImageDetails::class.java)
                                imageDetailsList!!.add(imageDetails)
                                Log.e("Repository", imageDetails.getImageUrl())
                            }
                            emitter.onNext(imageDetailsList!!)
                            emitter.onComplete()
                        }

                        fun onCancelled(@NonNull databaseError: DatabaseError?) {
                            emitter.isCancelled
                        }
                    })
                }, BackpressureStrategy.BUFFER)
                    .subscribeOn(Schedulers.io())
            )
        }

    fun insertImage(selectedImageUri: Uri?): Single<String?> {
        return Single.create { emitter: SingleEmitter<String?> ->
            if (selectedImageUri != null) {
                val imageName =
                    System.currentTimeMillis().toString() + "uploadedImage"
                val fileRef = storageReference!!.child(imageName)
                fileRef.putFile(selectedImageUri).addOnSuccessListener {
                    fileRef.downloadUrl
                        .addOnSuccessListener { uri ->
                            val imageDetails =
                                ImageDetails(imageName, uri.toString())
                            val uploadId: String = databaseReference.push().getKey()
                            databaseReference.child(uploadId).setValue(imageDetails)
                            emitter.onSuccess("Image Uploaded")
                        }
                }
                    .addOnFailureListener { e: Exception? ->
                        emitter.onError(
                            Throwable("Error uploading image")
                        )
                    }
            } else {
                emitter.onError(Throwable("Error uploading image"))
            }
        }
    }

    companion object {
        val instance: ImageRepository?
            get() {
                if (ImageRepository.Companion.instance == null) {
                    ImageRepository.Companion.instance = ImageRepository()
                }
                return ImageRepository.Companion.instance
            }
    }
}*/