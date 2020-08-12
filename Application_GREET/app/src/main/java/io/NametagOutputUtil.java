package io;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import util.Util;

//Map<String,String> map = NametagOutputUtil.getDBoutputUtil().output_database(param);
public class NametagOutputUtil {
    //싱글턴 생성
    static NametagOutputUtil DBoutput = null;

    public static NametagOutputUtil getDBoutputUtil(){
        if(DBoutput == null){
            DBoutput = new NametagOutputUtil();
        }
        return DBoutput;
    }

    public Map<String, String> output_database(String result) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result",  "error");
        try {
            resultMap = new LoadTask().execute(result, Util.TYPE_LOAD).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    class LoadTask extends AsyncTask<String, Void, Map<String, String>>{

        String ip = Util.IP; //서버의 ip
        String sendMsg, receiveMsg;
        String serverip = Util.SERVER_IP; //연결할 서버의 주소


        @Override
        protected Map<String, String> doInBackground(String... strings) {
            Map<String, String> resultMap = null;
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
                    String result = jObject.getString("result");
                    String key = jObject.getString("key");
                    String image = jObject.getString("image");

                    String imgPath = "http://" + Util.IP + ":9090/Mobile_Nametag/img/" + image;
                    if( result.equals("success") ){
                        resultMap = new HashMap();
                        resultMap.put("result", "success");
                        resultMap.put("key", key);
                        resultMap.put("image", Util.SERVER_IMG_PATH + image);
                    }else{
                        resultMap = new HashMap();
                        resultMap.put("result", "noResult");
                        resultMap.put("key", "noResult");
                        resultMap.put("image", "noResult");
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                resultMap = new HashMap();
                resultMap.put("result", "error");
                resultMap.put("key", "error");
                resultMap.put("image", "error");
            }
            return resultMap;
        }

        @Override
        protected void onPostExecute(Map<String, String> map) {
        }
    }//RegiTask*/


}
