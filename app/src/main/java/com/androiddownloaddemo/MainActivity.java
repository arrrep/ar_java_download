package com.androiddownloaddemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button downloadPdf, downloadDoc, downloadZip, downloadVideo, downloadMp3, openDownloadedFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }

// кнопочки для скачивания 
    private void initViews() {
        downloadPdf = (Button) findViewById(R.id.downloadPdf);
        downloadDoc = (Button) findViewById(R.id.downloadDoc);
        downloadZip = (Button) findViewById(R.id.downloadZip);
        downloadVideo = (Button) findViewById(R.id.downloadVideo);
        downloadMp3 = (Button) findViewById(R.id.downloadMp3);
        openDownloadedFolder = (Button) findViewById(R.id.openDownloadedFolder);

    }

  
    private void setListeners() {
        downloadPdf.setOnClickListener(this);
        downloadDoc.setOnClickListener(this);
        downloadZip.setOnClickListener(this);
        downloadVideo.setOnClickListener(this);
        downloadMp3.setOnClickListener(this);
        openDownloadedFolder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //чек инета перед любой загрузкой
        switch (view.getId()) {
            case R.id.downloadPdf:
                if (isConnectingToInternet())
                    new DownloadTask(MainActivity.this, downloadPdf, Utils.downloadPdfUrl);
                else
                    Toast.makeText(MainActivity.this, "Проблемы с интернетом, попробуйте снова чуть позже.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.downloadDoc:
                if (isConnectingToInternet())
                    new DownloadTask(MainActivity.this, downloadDoc, Utils.downloadDocUrl);
                else
                    Toast.makeText(MainActivity.this, "Проблемы с интернетом, попробуйте снова чуть позже.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.downloadZip:
                if (isConnectingToInternet())
                    new DownloadTask(MainActivity.this, downloadZip, Utils.downloadZipUrl);
                else
                    Toast.makeText(MainActivity.this, "Проблемы с интернетом, попробуйте снова чуть позже.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.downloadVideo:
                if (isConnectingToInternet())
                    new DownloadTask(MainActivity.this, downloadVideo, Utils.downloadVideoUrl);
                else
                    Toast.makeText(MainActivity.this, "Проблемы с интернетом, попробуйте снова чуть позже.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.downloadMp3:
                if (isConnectingToInternet())
                    new DownloadTask(MainActivity.this, downloadMp3, Utils.downloadMp3Url);
                else
                    Toast.makeText(MainActivity.this, "Проблемы с интернетом, попробуйте снова чуть позже.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.openDownloadedFolder:
                openDownloadedFolder();
                break;

        }

    }

    //Открытие папки куда пошел файл, чисто для чека
    private void openDownloadedFolder() {
     
        if (new CheckForSDCard().isSDCardPresent()) {

           
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + Utils.downloadDirectory);

          
            if (!apkStorage.exists())
                Toast.makeText(MainActivity.this, ".", Toast.LENGTH_SHORT).show();

            else {

              

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/" + Utils.downloadDirectory);
                intent.setDataAndType(uri, "file/*");
                startActivity(Intent.createChooser(intent, "Открыть папку с файлами"));
            }

        } else
            Toast.makeText(MainActivity.this, "Проблемы с открытием файла.", Toast.LENGTH_SHORT).show();

    }

    //проверка инета
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}
