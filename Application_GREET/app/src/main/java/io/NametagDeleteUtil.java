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
        String key_param = "key=" + key;
        try {
            result = new LoadTask().execute(key_param, Util.TYPE_DELETE).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    class LoadTask extends AsyncTask<String, Void, String>{

        String ip = Util.IP; //서버의 ip
        String sendMsg, receiveMsg;
        String serverip = Util.SERVER_IP; //연결할 서버의 주소


        @Override
        protected String doInBackground(String... strings) {
            String result = "fail";
            try {

                String str = "";
                URL url = new URL( serverip );

                //서버 연결
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter( conn.getOutputStream() );

                //List.jsp?id=aa&pwd=1111&type=type_regi
                sendMsg = strings[0] + "&type=" + strings[1];

                //서버로 파라미터 전달
                osw.write(sendMsg);
                osw.flush();

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
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }//RegiTask*/
}
