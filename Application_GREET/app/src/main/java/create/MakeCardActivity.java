package create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.four.application_greet.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MakeCardActivity extends AppCompatActivity {

    Button btn_next, btn_image,btn_rotate;
    ImageView image;
    EditText et_company, et_name, et_job, et_tel, et_email, et_address;
    Dialog dialog;
    final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/NameCard_Folder";
    final File directory = new File(path);
    int mDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_card);
        btn_rotate=findViewById(R.id.btn_rotate);
        btn_next = findViewById(R.id.btn_next);
        btn_image = findViewById(R.id.btn_image);
        image = findViewById(R.id.image);
        et_company = findViewById(R.id.et_company);
        et_name = findViewById(R.id.et_name);
        et_job = findViewById(R.id.et_job);
        et_tel = findViewById(R.id.et_tel);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        btn_next.setOnClickListener(click);


    }//onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    final Bitmap img = BitmapFactory.decodeStream(in);


                    btn_rotate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDegree = mDegree + 90;

                            image.setImageBitmap(rotateImage(
                                    img, mDegree));
                        }
                    });


                    in.close();

                    // 이미지 표시
                    image.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//onActivityResult()



    View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (et_company.getText().toString().length() == 0 || et_name.getText().toString().length() == 0 || et_job.getText().toString().length() == 0
                    || et_tel.getText().toString().length() == 0 || et_email.getText().toString().length() == 0 || et_address.getText().toString().length() == 0
                    || image.getDrawable() == null) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MakeCardActivity.this);
                dialog.setTitle("정보입력");
                dialog.setMessage("정보를 모두 입력하세요");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dialog.show();

            } else {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                // float scale = (float) (500/);//(float)bitmap.getWidth()
                int image_w = 500;
                int image_h = 500;
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                //subActivity로 전달할 애들
                String company = et_company.getText().toString();
                String name = et_name.getText().toString();
                String job = et_job.getText().toString();
                String tel = et_tel.getText().toString();
                String email = et_email.getText().toString();
                String address = et_address.getText().toString();


                Intent i = new Intent(MakeCardActivity.this, SelectThemeActivity.class);

                //타 액티비티로 값을 전달하기 2 (Bundle 에 담아서 전달하기)
                Bundle bundle = new Bundle();
                bundle.putString("company", company);
                bundle.putString("name", name);
                bundle.putString("job", job);
                bundle.putString("tel", tel);
                bundle.putString("email", email);
                bundle.putString("address", address);
                bundle.putByteArray("image", byteArray);
                i.putExtras(bundle);

                startActivity(i);

            }
        }
    };
    // 이미지 회전 함수
    public Bitmap rotateImage(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }
}