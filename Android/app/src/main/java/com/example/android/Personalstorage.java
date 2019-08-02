package com.example.android;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Personalstorage extends Activity {

    LinearLayout storage1;
    ArrayList<WritingVO> writing = null;
    private static final String TAG = "Personalstorage";
    public String basePath = null;
    public GridView mGridView;

    private final int GALLERY_REQUEST_CODE = 1001;

    private ImageButton pic;
    private ImageView imageView;

    private TextView tv_outPut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_storage);
        //Intent intent = getIntent(); /*데이터 수신*/

//        퍼센트 추가
//        writing = new ArrayList<WritingVO>();
//        Canvas canvas = new Canvas();
//        WritingVO wVO1 = new WritingVO((float) 100, (float)100);
//        WritingVO wVO2 = new WritingVO((float) 1, (float)10);
//
//        writing.add(wVO1);
//        writing.add(wVO2);
//
//        CircleChart cc = new CircleChart(this, writing, 100, 500);
//        setContentView(cc);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        pic = (ImageButton) findViewById(R.id.pic);     // 이미지 버튼을 누르면 앨범에서 사진을 선택할 수 있습니다.
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click pic");
                //Intent 생성
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
                intent.setType("image/*"); //이미지만 보이게
                //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
                //finish();

            }
        });
//        imageView = (ImageView)findViewById(R.id.img);
//
//
//
//        // 위젯에 대한 참조.
//        tv_outPut = (TextView) findViewById(R.id.tv_outPut);

        // URL 설정.
        String url = "http://ec2-13-52-97-164.us-west-1.compute.amazonaws.com:3000/ping";

        Log.d(TAG,"1");
        //uploadToServer(url);

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);
        //networkTask.execute();
        Log.d(TAG,"2");


    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            Log.d(TAG,"3");
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            Log.d(TAG,"4");
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            Log.d(TAG,"5");
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
//            Log.d(TAG,s);

            tv_outPut.setText(s);
        }
    }


    public void onActivityResult(int requestCode,int resultCode,Intent data){

         //Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
//                    //data.getData return the content URI for the selected Image
                    Log.d(TAG,"6");
                    Uri selectedImage = data.getData();
                    Log.d(TAG,"7");
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    Log.d(TAG,"8  ");
//                    imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));


//                    Uri selPhotoUri = data.getData();
//
//                    //나중에 이미지뷰에 뿌려주기 위해 담아놓음.
//                    Bitmap selPhoto = MediaStore.Images.Media.getBitmap( getContentResolver(), selPhotoUri );
//                    Log.e("전송","시~~작 ~~~~~!");
//
//                    String urlString = "서버에 전송할 API URL을 넣는다.";
//
////절대경로를 획득한다!!! 중요~
//                    Cursor c = getContentResolver().query(Uri.parse(selPhotoUri.toString()), null,null,null,null);
//                    c.moveToNext();
//                    String absolutePath = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
//
////파일 업로드 시작!
//                    DoFileUpload(urlString , absolutePath);

                    break;

            }
    }

//    public void DoFileUpload(String apiUrl, String absolutePath) {
//        HttpFileUpload(apiUrl, "", absolutePath);
//    }
//
//    String lineEnd = "\r\n";
//    String twoHyphens = "--";
//    String boundary = "*****";

//    public void HttpFileUpload(String urlString, String params, String fileName) {
//        try {
//
//            mFileInputStream = new FileInputStream(fileName);
//            connectUrl = new URL(urlString);
//            Log.d("Test", "mFileInputStream  is " + mFileInputStream);
//
//            // open connection
//            HttpURLConnection conn = (HttpURLConnection)connectUrl.openConnection();
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//
//            // write data
//            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//            dos.writeBytes(twoHyphens + boundary + lineEnd);
//            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + fileName+"\"" + lineEnd);
//            dos.writeBytes(lineEnd);
//
//            int bytesAvailable = mFileInputStream.available();
//            int maxBufferSize = 1024;
//            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//
//            byte[] buffer = new byte[bufferSize];
//            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
//
//            Log.d("Test", "image byte is " + bytesRead);
//
//            // read image
//            while (bytesRead > 0) {
//                dos.write(buffer, 0, bufferSize);
//                bytesAvailable = mFileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
//            }
//
//            dos.writeBytes(lineEnd);
//            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//            // close streams
//            Log.e("Test" , "File is written");
//            mFileInputStream.close();
//            dos.flush(); // finish upload...
//
//            // get response
//            int ch;
//            InputStream is = conn.getInputStream();
//            StringBuffer b =new StringBuffer();
//            while( ( ch = is.read() ) != -1 ){
//                b.append( (char)ch );
//            }
//            String s=b.toString();
//            Log.e("Test", "result = " + s);
//            mEdityEntry.setText(s);
//            dos.close();
//
//        } catch (Exception e) {
//            Log.d("Test", "exception " + e.getMessage());
//            // TODO: handle exception
//        }
//    }

//    public void uploadFile(ArrayList<String> imgPaths) {
//
//        String charset = "UTF-8";
//        //File uploadFile1 = new File("e:/Test/PIC1.JPG");
//        //File uploadFile2 = new File("e:/Test/PIC2.JPG");
//
//        File sourceFile[] = new File[imgPaths.size()];
//        for (int i=0;i<imgPaths.size();i++){
//            sourceFile[i] = new File(imgPaths.get(i));
//            // Toast.makeText(getApplicationContext(),imgPaths.get(i),Toast.LENGTH_SHORT).show();
//        }
//
//        String requestURL = "http://13.52.97.164:3000/ping";
//
//        try {
//            FileUploader multipart = new FileUploader(requestURL, charset);
//
//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");
//
//            multipart.addFormField("description", "Cool Pictures");
//            multipart.addFormField("keywords", "Java,upload,Spring");
//
//            for (int i=0;i<imgPaths.size();i++){
//                multipart.addFilePart("uploaded_file[]", sourceFile[i]);
//            }
//
//            /*multipart.addFilePart("fileUpload", uploadFile1);
//            multipart.addFilePart("fileUpload", uploadFile2);*/
//
//            List<String> response = multipart.finish();
//
//            System.out.println("SERVER REPLIED:");
//
//            for (String line : response) {
//                System.out.println(line);
//            }
//        } catch (IOException ex) {
//            System.err.println(ex);
//        }
//    }


    private void uploadToServer(String filePath) {
        Log.d(TAG,"uploadtoServer");
        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        UploadAPIs uploadAPIs = retrofit.create(UploadAPIs.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        //
        Call call = uploadAPIs.uploadImage(part, description);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }


}
