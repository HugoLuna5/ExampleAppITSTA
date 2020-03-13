package lunainc.com.mx.exampleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    Intent i;
    final static int cons =0;
    Bitmap bmp;

    private int CAMERA_REQUEST_STATUS = 255;

    @BindView(R.id.btnCaptura)
    Button btn;

    @BindView(R.id.imagen)
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    private Boolean verifyPermission() {

        boolean aux = false;

        if (ActivityCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED) {
            aux = true;

        } else {
            Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show();
        }

        return aux;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, CAMERA_REQUEST_STATUS);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (verifyPermission()) {
            requestPermission();

            Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
        } else {
            events();
        }
    }




    public void events(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, cons);


            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK){

            Bundle ext = data.getExtras();
            bmp = (Bitmap) ext.get("data");

            Toast.makeText(this, R.string.message_toast, Toast.LENGTH_SHORT).show();
            imagen.setImageBitmap(bmp);

        }

    }
}
