package com.sngular.rgarciavital.view.mapa

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sngular.rgarciavital.R
import com.sngular.rgarciavital.data.model.UserData

class MapsFragmentAPI : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val db = Firebase.firestore
        db.collection("UserData")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document != null) {
                        val doc = document.toObject(UserData::class.java)
                        if (doc != null) {
                            val user = doc
                            if (user != null) {

                                val person = LatLng(user.latitude!!.toDouble(), user.longitude!!.toDouble())
                                googleMap.addMarker(
                                    MarkerOptions().position(person).title(user.name)
                                )
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(person))

                            }
                        }
                    }
                    Log.d(" Firebase", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error Firebase", "Error getting documents: ", exception)
            }

        googleMap.isMyLocationEnabled()
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_a_p_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}