package com.nerd.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createAlarm("일할시간!", 11, 40);
//        openWebPage("https://www.naver.com/");
//        composeMmsMessage("안녕하세요.", null);

    }
    // 연락처 선택
    public void selectContact(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, 1);
        }
    }

    public void composeMmsMessage(String message, Uri attachment){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("smsto:012-3456-7890"));
        i.putExtra("sms_body", message);
        i.putExtra(Intent.EXTRA_STREAM, attachment);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

    public void openWebPage(String url){
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }
    // 원하는 시간:분 에 알람 메세지 나오도록 하는 코드
    public void createAlarm(String message, int hour, int minutes){
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_MESSAGE,hour).putExtra(AlarmClock.EXTRA_HOUR, minutes);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

    public void composeEmail(String[] address, String subject){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto"));
        i.putExtra(Intent.EXTRA_EMAIL,address);
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

}
