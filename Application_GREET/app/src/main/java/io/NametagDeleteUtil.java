package io;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import util.Util;

//String result = NametagDeleteUtil.getDBdeleteUtil().delete_database(key);
//result값이 success인 경우 삭제성공 else일시 실패
public class NametagDeleteUtil {
    //싱글턴 생성
    static NametagDeleteUtil DBdelete = null;

    public static NametagDeleteUtil getDBdeleteUtil(){
        if(DBdelete == null){
            DBdelete = new NametagDeleteUtil();
        }
        return DBdelete;
    }

    public String delete_database(String key) {
        String result = "fail";
        try {
            result = new LoadTask().execute(key).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    class LoadTask extends AsyncTask<String, Void, String>{

        String sendMsg, receiveMsg;
        String serverip = Util.SERVER_URL; //연결할 서버의 주소


        @Override
        protected String doInBackground(String... strings) {
            String result = "fail";
            String url_path = serverip + "/" + strings[0];
            try {

                String str = "";
                URL url = new URL( url_path );

                //서버 연결
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("DELETE");

                //전송이 완료되면 서버에서 처리한 결과값을 받는다
                if( conn.getResponseCode() == conn.HTTP_OK ){

                    //서버의 데이터 읽기
                    InputStreamReader tmp = new InputStreamReader(
                            conn.getInputStream(), "utf-8" );

                    BufferedReader reader = new BufferedReader( tmp );
                    StringBuffer buffer = new StringBuffer();

                    while( ( str = reader.readLine() ) != null ){

                        buffer.append(str);

                    }
                    receiveMsg = buffer.toString();
                    JSONObject jObject = new JSONObject(receiveMsg);
                    result = jObject.getString("result");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.e("del", result);
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }//RegiTask*/
}
