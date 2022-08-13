package com.wandika.outofthefridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    //Inisiasi Variable
    Button btn_gambar, btn_cari, btn_txt;
    RecyclerView recyclerView_bahan;
    ImageView imageView;
    EditText editText;
    TextView textView;

    ArrayList<Uri> arrayList = new ArrayList<Uri>();
    ArrayList<String> arrayListBahan = new ArrayList<String>();

//    V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(ClarifaiChannel.INSTANCE.getGrpcChannel())
//            .withCallCredentials(new ClarifaiCallCredentials("b6ece88203be4bfaa9ac2c3ae81db756"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Memasukkan Nilai Variable
        btn_gambar = findViewById(R.id.btn_gambar);
        btn_cari = findViewById(R.id.btn_cari);
        btn_txt = findViewById(R.id.btn_text);
        recyclerView_bahan = findViewById(R.id.recyclerview_bahan);
        imageView = findViewById(R.id.img_bahan);
        editText = findViewById(R.id.edit_bahan);
        //textView = findViewById(R.id.test);

        //Button Text On Click Listener
        btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                String s = editText.getText().toString();
                //arrayListBahan.add(s);
                //textView.setText("Bahan: " + s);

                Intent intent = new Intent(getApplicationContext(), ApiRequest.class);
                intent.putExtra("message_key", s);
                startActivity(intent);
            }
        });


        //Mengambil Input Gambar Dari User Dan Menampilkannya di Image View
        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();

                        if (intent != null) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        getContentResolver(), intent.getData()
                                );

                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        //Button Gambar On Click Listener
        btn_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Memanggil Method Untuk Mengambil Gambar
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                resultLauncher.launch(intent);

                //Menampilkan hasil pembacaan gambar dari API

                //Melakukan API request sesuai bahan yang ada pada gambar
//                MultiOutputResponse response = stub.postModelOutputs(
//                        PostModelOutputsRequest.newBuilder()
//                                .setModelId("bd367be194cf45149e75f01d59f77ba7")
//                                .addInputs(
//                                        Input.newBuilder().setData(
//                                                Data.newBuilder().setImage(
//                                                        intent
//                                                        //Image.newBuilder().setUrl("YOUR_IMAGE_URL")
//                                                )
//                                        )
//                                )
//                                .build()
//                );
//
//                if (response.getStatus().getCode() != StatusCode.SUCCESS) {
//                    throw new RuntimeException("Request failed, status: " + response.getStatus());
//                }
//
//                for (Concept c : response.getOutputs(0).getData().getConceptsList()) {
//                    System.out.println(String.format("%12s: %,.2f", c.getName(), c.getValue()));
//                }

            }
        });
    }

}