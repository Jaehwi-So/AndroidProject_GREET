package create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import main.MainActivity;
import com.four.application_greet.R;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import io.NametagInputUtil;

public class SelectThemeActivity extends AppCompatActivity {

    TextView txt_company, txt_name, txt_job, txt_tel, txt_email, txt_address;
    ImageView image;

    public static String company, name, job, tel, email, address;
    public static byte[] byteArray;
    public static Bitmap bitmap;
    ViewPager pager;
    Button btn_ok;

    ArrayList<String> photoList = new ArrayList<>();

    //캡쳐할 레이아웃 : capture_layout
    //캡쳐버튼(누르면 캡쳐) : capture_button
    LinearLayout capture_layout;
    Button capture_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thema);

        txt_company = findViewById(R.id.txt_company);
        txt_name = findViewById(R.id.txt_name);
        txt_job = findViewById(R.id.txt_job);
        txt_tel = findViewById(R.id.txt_tel);
        txt_email = findViewById(R.id.txt_email);
        txt_address = findViewById(R.id.txt_address);
        image = findViewById(R.id.image);

        capture_layout = (LinearLayout) findViewById(R.id.capture_layout);
        capture_button = (Button) findViewById(R.id.btn_ok);

        //메인에서 전달받은 Intent
        Intent i = getIntent();

        //Intent 에서 값 추출하기 2
        Bundle bundle = i.getExtras(); //인텐트에서 번들 추출
        company = bundle.getString("company");
        name = bundle.getString("name");
        job = bundle.getString("job");
        tel = bundle.getString("tel");
        email = bundle.getString("email");
        address = bundle.getString("address");
        byteArray = bundle.getByteArray("image");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        //가져온 값 적용
        txt_company.setText((CharSequence) company);
        txt_name.setText((CharSequence) name);
        txt_job.setText((CharSequence) job);
        txt_tel.setText((CharSequence) tel);
        txt_email.setText((CharSequence) email);
        txt_address.setText((CharSequence) address);
        image.setImageBitmap(bitmap);

        pager = findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0); //시작할 페이지


        //뷰페이지에 밑줄을 그려주는 언더라인 인디케이터
        UnderlinePageIndicator underline = findViewById(R.id.underline);
        underline.setFades(false);
        underline.setViewPager(pager);


        //캡쳐버튼
        capture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String db_path = "";
                //파일을 저장한 시점을 파일이름,파일키값으로 설정
                long key_value = System.currentTimeMillis();
                String key = "" + key_value;

                createDirectoryAndSaveFile(key);


            }

        });//ClickListener


    }//onCreate()

    private void createDirectoryAndSaveFile(String Key) {

        String db_path = "";
        File direct = new File(Environment.getExternalStorageDirectory() + "/GREET_Folder");

        try {
                if (!direct.exists()) {
                    File Directory = new File(Environment.getExternalStorageDirectory() + "/GREET_Folder");
                    Directory.mkdirs();
                    Log.d("CAMERA_TEST!!!!!!!!!!!!", "Directory Created");
                } else {
                    Log.d("CAMERA_TEST!!!!!!!!!!!!", "Directory not Created");
                }
            //캡쳐
            capture_layout.buildDrawingCache();
            Bitmap captureView = capture_layout.getDrawingCache();
            String filename;

            try {
                filename = Environment.getExternalStorageDirectory().getPath() + "/GREET_Folder/" + Key + ".jpg";
                FileOutputStream out = new FileOutputStream(filename);
                captureView.compress(Bitmap.CompressFormat.JPEG, 100, out);



                // 미디어 스캐너를 통해 모든 미디어 리스트를 갱신
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + Environment.getExternalStorageDirectory())));

                db_path = filename;

            } catch (Exception e) {
                e.printStackTrace();
            }//try-catch
            finally {

                //캡쳐 토스트
                Toast.makeText(getApplicationContext(), Key + ".jpg 저장\n 매인화면으로 이동합니다",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SelectThemeActivity.this, MainActivity.class);
                startActivity(intent);
                NametagInputUtil.getDBinputUtil().input_database(Key, db_path);
                finish();

            }//finally
        } catch (Exception e) {

            // TODO: handle exception
            Log.e("Screen", "" + e.toString());

        }//try-catch
    }
}