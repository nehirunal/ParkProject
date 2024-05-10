package com.example.smarton_streetparkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

  //  private GoogleMap gMap;
    FirebaseAuth auth;
    Button profile_btn,map_btn,scan_btn,logout_btn;
    TextView textView;
    FirebaseUser user;

    /*
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

     */

    // sayfa değiştiğinde mesaj yazdırma gereksiz
  /*  public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_map:
                Toast.makeText(getApplicationContext(),"Map",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_qr:
                Toast.makeText(getApplicationContext(),"QR Scanner",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_profile:
                Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_btn = findViewById(R.id.profile);
        map_btn = findViewById(R.id.map);
        scan_btn = findViewById(R.id.scanner);
        logout_btn = findViewById(R.id.logout);
       // textView = findViewById(R.id.text);

        map_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // map_btn butonuna tıkladığında ne yapsın
                startActivity(new Intent(MainActivity.this,Map.class));
               // IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity);
               // intentIntegrator.setPrompt("Scan a QR Code");
               // intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.QR_CODE);
               // intentIntegrator.initialScan();
            }
        });


       // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
       // mapFragment.getMapAsync(this);

        auth = FirebaseAuth.getInstance();
        logout_btn = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
        }

        logout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    /*
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location = new LatLng(38.46169459870305, 27.213280941150565);
        googleMap.addMarker(new MarkerOptions().position(location).title("Spot 1"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));

    }
    */
}