package com.example.project_english_app;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class QuestionNare
{
    public String ID;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;

}
public class Activity_quiz_play extends Activity
{
//    Activity_quiz_chooseQuestion Au = new Activity_quiz_chooseQuestion() ;
    int AA;
    private Button btn;
    TextView Cauhoi, Ketqua;
    RadioGroup RG;
    Button BT;
    Button Skip;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView textViewCountDown;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;




    RadioButton A, B, C, D;
    int pos = 0;//vị trí câu hỏi trong danh sách
    int kq = 0; //lưu số câu trả lời đúng
    ArrayList<QuestionNare> L = new ArrayList(); //chứa câu hỏi
    int HighScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        AA = packageFromCaller.getInt("SL1") ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_play);
        Cauhoi = (TextView) findViewById(R.id.txtCauhoi);
        Ketqua = (TextView)findViewById(R.id.txt_ketqua);
        RG = (RadioGroup) findViewById(R.id.radioGroup);
        BT = (Button) findViewById(R.id.btn_Answer);
        Skip = (Button) findViewById(R.id.btn_Skip) ;
        textViewCountDown = findViewById(R.id.textTimer);
        A = (RadioButton) findViewById(R.id.rb1);
        B = (RadioButton) findViewById(R.id.rb2);
        C = (RadioButton) findViewById(R.id.rb3);
        D = (RadioButton) findViewById(R.id.rb4);
        textColorDefaultCd = textViewCountDown.getTextColors();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
        LoadHighScore();
        ReadData();
        Display(pos);

        BT.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    boolean a = false ;
                    int idCheck = RG.getCheckedRadioButtonId();
//                    onDestroy();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//                    startCountDown();


                    switch (idCheck) {
                        case R.id.rb1:
                            if (L.get(pos).Answer.compareTo("A") == 0) kq = kq + 1;
                             a = true;
                            break;
                        case R.id.rb2:
                            if (L.get(pos).Answer.compareTo("B") == 0) kq = kq + 1;
                            a = true ;
                            break;
                        case R.id.rb3:
                            if (L.get(pos).Answer.compareTo("C") == 0) kq = kq + 1;
                            a = true ;
                            break;
                        case R.id.rb4:
                            if (L.get(pos).Answer.compareTo("D") == 0) kq = kq + 1;
                            a = true ;
                            break;

                    }
                    if(a == true){
                    pos++;
                        onDestroy();
                        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                        startCountDown();

                    if (pos >= L.size()) {
                        Intent intent = new Intent(Activity_quiz_play.this, Activity_Result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("Socau", pos);
                        intent.putExtra("MyPackage", bundle);
                        startActivity(intent);
                        if (kq > HighScore) {
                            HighScore = kq;
                            SaveHighScore();
                        }
                        finish();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;

                    } else {

//                    startCountDown();
                        Display(pos); //Hiển thị câu hỏi kế tiếp
                    }}

                }
            });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos++;
                onDestroy();
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();

                if (pos >= L.size()) {
                    Intent intent = new Intent(Activity_quiz_play.this, Activity_Result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("Socau", pos);
                    intent.putExtra("MyPackage", bundle);
                    startActivity(intent);
                    if (kq > HighScore) {
                        HighScore = kq;
                        SaveHighScore();
                    }
                    finish();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;

                } else {

//                    startCountDown();
                    Display(pos); //Hiển thị câu hỏi kế tiếp
                }}
        });

        btn = (Button) findViewById(R.id.btn_Quiz_goBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackAcivity_quiz_home();
            }
        });

    }

    private void startCountDown()
    {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish()
            {

                timeLeftInMillis = 0;
                Toast.makeText(Activity_quiz_play.this, "Het gio", Toast.LENGTH_SHORT).show();
                Display(pos); //Hiển thị câu hỏi kế tiếp
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText()
    {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000)
        {
            textViewCountDown.setTextColor(Color.RED);
        }
        else
        {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    public void gobackAcivity_quiz_home()
    {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }

    void Display(int i)
    {

        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        Ketqua.setText("Câu đúng:" + kq);
        RG.clearCheck(); //xóa checked
    }
    void ReadData()
    {
        try
        {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("data.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//lấy tag Root
            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//            double b = Au.A/1;
            for (int i = 0; i < AA*2; i++)
//                list.getLength()

//                list.getLength()
            {// duyệt từ node đầu tiên cho tới
                Random a ;
                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
                if (node instanceof Element)
                {
                    Element Item = (Element) node;// lấy được tag Item
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();//lấy nội dung của tag ID
                    listChild = Item.getElementsByTagName("Question");// lấy tag Question
                    String Question = listChild.item(0).getTextContent();//lấy nội dung Question
                    listChild = Item.getElementsByTagName("AnswerA");
                    String AnswerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String AnswerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String AnswerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerD");
                    String AnswerD = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    QuestionNare Q1 = new QuestionNare();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.AnswerA = AnswerA;
                    Q1.AnswerB = AnswerB;
                    Q1.AnswerC = AnswerC;
                    Q1.AnswerD = AnswerD;
                    Q1.Answer = Answer;
                    L.add(Q1);
                };
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void LoadHighScore()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        if (sharedPreferences !=null)
        {
            HighScore = sharedPreferences.getInt("H",0);
        }
    }
    void SaveHighScore()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H",HighScore);
        editor.apply();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }
}


