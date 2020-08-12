package main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.four.application_greet.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.four.application_greet.BuildConfig;
import com.four.application_greet.R;

import java.io.File;
import java.util.ArrayList;

import create.MakeCardActivity;
import io.NametagDeleteUtil;
import taglistother.ShowListActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_new, btn_save, btn_list, btn_delete, btn_share;
    ViewPager pager;
    TextView txt_key;

    final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GREET_Folder";
    final File directory = new File(path);
    File[] files = directory.listFiles();
    final ArrayList<String> fileList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            if (!directory.exists()) {
                File Directory = new File(Environment.getExternalStorageDirectory() + "/GREET_Folder");
                Directory.mkdirs();
                files = Directory.listFiles();
                Log.d("CAMERA_TEST!!!!!!!!!!!!", "Directory Created");

            } else {
                Log.d("CAMERA_TEST!!!!!!!!!!!!", "Directory not Created");
            }
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        btn_new = findViewById(R.id.btn_new);
        btn_list = findViewById(R.id.btn_list);
        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);
        btn_share = findViewById(R.id.btn_share);
        pager = findViewById(R.id.pager);
        txt_key = findViewById(R.id.txt_key);

        //전화걸기 권한에 대한 수락 여부 확인
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            setPermission();
            return;
        }

        //리스트 보여주기로 이동
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowListActivity.class);
                startActivity(intent);
            }
        });

        //명함 편집하기
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MakeCardActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });



        MainPageAdapter mainPageAdapter = new MainPageAdapter(MainActivity.this, path);
        pager.setAdapter(mainPageAdapter);

        //초기 명함유무 판단
        if (files.length==0) {
            txt_key.setText("  명함을 생성해주세요");
        }else{
            String totkey = files[0].getName().replaceAll(".jpg", "");
            txt_key.setText("검색키 " + totkey  );
        }





        //페이지 스크롤 감지 후 키값 set
        pager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                for (int z = 0; z < files.length; z++) {

                    fileList.add(files[z].getName());
                }
                for (int z = 0; z < fileList.size(); z++) {
                    if (z == pager.getCurrentItem()) {

                        String key =   fileList.get(z);

                        String totkey = key.replaceAll(".jpg", "");


                        txt_key.setText("검색키 "+ totkey);
                    }


                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("안내");
                builder.setMessage("명함을 삭제하시겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = String.valueOf(files[pager.getCurrentItem()]);

                        String key_1 = key.replaceAll(".jpg", "");
                        String totkey = key_1.replaceAll("/storage/emulated/0/GREET_Folder/", "");
                        Log.i("msg!!!!!!!!!!", totkey);

                        files[pager.getCurrentItem()].delete();
                        String result = NametagDeleteUtil.getDBdeleteUtil().delete_database(totkey);
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                //pager.getCurrentItem();
            }
        });
        //키값 복사 이벤트
        txt_key.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작

                    String key = files[pager.getCurrentItem()].getName();
                    String totKey = key.replaceAll(".jpg", "");
                    //클립보드 사용 코드
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Key", totKey); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(), "키값 복사 완료",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (files.length==0) {
                    Toast.makeText(getApplicationContext(), "명함이 없습니다",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Uri uri;

                    String share_path = files[pager.getCurrentItem()].getPath();
                    File imageFileToShare = new File(share_path);
                    
                    // File 객체의 URI 를 얻는다.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// API 24 이상 일경우..

                        uri = FileProvider.getUriForFile(getApplicationContext(),
                                BuildConfig.APPLICATION_ID, imageFileToShare);

                    } else {// API 24 미만 일경우..
                        uri = Uri.fromFile(imageFileToShare);
                    }


                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("image/jpeg");


                    sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(sharingIntent, "Share image using")); // 변경가능

                }


            }
        });


    }//Oncreate

    private void setPermission() {
        TedPermission.with(this)
                .setPermissionListener(permissionListener)//권한 수락 여부에 대한 감지자
                //여러개의 권한 중 수락되지 않은 권한이 있을경우 출력되는 메시지
                .setDeniedMessage("이 앱에서 요구하는 권한이 있습니다\n[설정]->[권한]에서 활성화 해주세요")
                .setPermissions(Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CONTACTS)//설정하고픈 권한들 다중추가 가능!!
                .check();

    }//setPermission()

    //앱 권한설정 감지자
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //모든 권한의 수락이 완료된 경우
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            //한가지라도 허용되지 않은 권한이 있는 경우
            finish();
        }

    };


}