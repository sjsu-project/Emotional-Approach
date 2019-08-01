package com.example.android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//
//public class UploadFile extends AsyncTask<Object, String, String> {
//    String file_name = "";
//    @Override
//    protected String doInBackground(Object[] params) {
//        try {
//            String lineEnd = "\r\n";
//            String twoHyphens = "--";
//            String boundary = "*****";
//            int bytesRead, bytesAvailable, bufferSize;
//            byte[] buffer;
//            int maxBufferSize = 1024 * 1024;
//            //todo change URL as per client ( MOST IMPORTANT )
//            URL url = new URL("http://webkul.com/upload");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Allow Inputs &amp; Outputs.
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setUseCaches(false);
//
//            // Set HTTP method to POST.
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//            FileInputStream fileInputStream;
//            DataOutputStream outputStream;
//            outputStream = new DataOutputStream(connection.getOutputStream());
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//
//            outputStream.writeBytes("Content-Disposition: form-data; name=\"reference\""+ lineEnd);
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes("my_refrence_text");
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//
//            outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadFile\";filename=\"" + uri.getLastPathSegment() +"\"" + lineEnd);
//            outputStream.writeBytes(lineEnd);
//
//            //uri였음...
//            fileInputStream = new FileInputStream(url.getPath());
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//
//            // Read file
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//            while (bytesRead > 0) {
//                outputStream.write(buffer, 0, bufferSize);
//                bytesAvailable = fileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//            }
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//            // Responses from the server (code and message)
//            int serverResponseCode = connection.getResponseCode();
//            String result = null;
//            if (serverResponseCode == 200) {
//                StringBuilder s_buffer = new StringBuilder();
//                InputStream is = new BufferedInputStream(connection.getInputStream());
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String inputLine;
//                while ((inputLine = br.readLine()) != null) {
//                    s_buffer.append(inputLine);
//                }
//                result = s_buffer.toString();
//            }
//            fileInputStream.close();
//            outputStream.flush();
//            outputStream.close();
//            if (result != null) {
//                Log.d("result_for upload", result);
//                //file_name = getDataFromInputStream(result, "file_name");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file_name;
//    }
//
//}

