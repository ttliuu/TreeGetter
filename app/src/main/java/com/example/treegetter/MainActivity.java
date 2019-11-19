package com.example.treegetter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView textView;
    private LocationManager locationManager;
    private Double longitude, latitude;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mTreeRef = mRootRef.child("Tree");
    DatabaseReference mSingleTree;

    @Override
    protected void onStart() {
        super.onStart();
        mTreeRef.keepSynced(true);
        mTreeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                System.out.print(dataSnapshot.toString());
                for (DataSnapshot posSnapshot: dataSnapshot.getChildren()) {
                    System.out.print(posSnapshot);
                    mSingleTree = posSnapshot.getRef();
                    mSingleTree.keepSynced(true);
                    mSingleTree.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot postSnapshot) {
                            Tree a = new Tree();
                            for(DataSnapshot  ad: postSnapshot.getChildren()) {
                                System.out.println("asdf" + ad.toString());
                                if (ad.getKey().equals("COURSE::name")) {
                                    a.setType(ad.getValue().toString());
                                    System.out.println("ad value is" + ad.getValue().toString());
                                } else if (ad.getKey().equals("Latin")) {
                                    a.setLatin(ad.getValue().toString());
                                } else if (ad.getKey().equals("Longitude")) {
                                    a.setLongitude(Double.parseDouble(ad.getValue().toString()));
                                } else if (ad.getKey().equals("Latitude")) {
                                    a.setLatitude(Double.parseDouble(ad.getValue().toString()));
                                } else if (ad.getKey().equals("Description")) {
                                    a.setDescription(ad.getValue().toString());
                                }
                            }
                            System.out.println(a.toString());
                            Tree.treeArray.add(a);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        System.out.print(Tree.treeArray.get(0).toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.editText);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overridi   ng
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
    }

    @Override
    public void onLocationChanged(Location location) {

        longitude = location.getLongitude();
        latitude = location.getLatitude();
        textView.setText("" + longitude + "," + latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void onClick(View view){
        textView.setText("" + longitude + "," + latitude);
    }
}
