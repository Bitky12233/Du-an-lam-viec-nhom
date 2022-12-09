package com.example.project_english_app;

//import static com.example.project_english_app.member.getCurrentTime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class English_CorrectWord {
    public String hint, question, answer;
}

public class Activity_correct_play extends AppCompatActivity {
    private Button btnBack, btnAnswer, btnSkip;
    GridView GvChuCai;
    TextView txtHint, txtAnswer, txtTimer, txtCounterStar, txtCorrectAnswer;
    ArrayList<ChuCai_CorrectWord> ChuCai;
    ProgressBar Pgb;
    ChuCai_Adapter ChuCaiAdapter;
    ArrayList<English_CorrectWord> L = new ArrayList();
    ArrayList<Integer> Arr_MaintainAnswer;
    String FullChuCai = "";
    ArrayList<String> question;
    ArrayList<member> MemberList;
    int Level = 0, pos = 0, current, StartTimeInt = 30, Check_Maintain_Count_Star = 0, posIndex = 0, High_Star = 0, CorrectAnswer = 0, temp = 0, total_Time = 0, count;
    Random random = new Random();
    boolean Frag_Easy[] = new boolean[1000], Frag_Medium[] = new boolean[1000], Frag_Hard[] = new boolean[1000], CheckAnswer = false, page = false;
    CountDownTimer Count_Down_Timer;
    private static final String FILE_NAME = "rankUpdate.txt";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correct_play);
        AnhXa();
        ReadData(CheckAnswer);
        System.gc();
//        CountDown();
        GvChuCai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setVisibility(View.INVISIBLE);
                FullChuCai += ChuCai.get(i).getChuCai();
                txtAnswer.setText(FullChuCai);
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Arr_MaintainAnswer.add(-1);
                if (Arr_MaintainAnswer.size() > 2) {
                    Check_Maintain_Answer();
                }
                if (!page) {
                    CheckAnswer = false;
                    StartTimeInt = 30;
                    pos++;
                    ReadData(CheckAnswer);
//                    Count_Down_Timer.cancel();
//                    CountDown();
                    FullChuCai = "";
                    txtAnswer.setText(FullChuCai);
                    current = Pgb.getProgress();
                    Pgb.setProgress(current + 10);
                }

            }
        });
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtAnswer.getText().toString().trim().equals(L.get(pos).answer.trim())) {
                    Arr_MaintainAnswer.add(1);
                    if (Arr_MaintainAnswer.size() > 2) {
                        Check_Maintain_Answer();
                    }
                    if (!page) {
                        CheckAnswer = true;
                        StartTimeInt = 30;
                        current = Pgb.getProgress();
                        Pgb.setProgress(current + 10);
                        Toast.makeText(Activity_correct_play.this, "Right answer ", Toast.LENGTH_LONG).show();
                        FullChuCai = "";
                        txtAnswer.setText("");
                        pos++;
                        ReadData(CheckAnswer);
//                        Count_Down_Timer.cancel();
//                        CountDown();
                        CorrectAnswer++;
                        txtCorrectAnswer.setText("Correct answers : " + String.valueOf(CorrectAnswer));
                    }

//                    pos++;
//                    Display(pos);
                } else {
//                    Log.e(txtAnswer.getText().toString().trim() + " " + L.get(pos).answer, "asd");
                    Toast.makeText(Activity_correct_play.this, "Wrong " + FullChuCai, Toast.LENGTH_LONG).show();
                    FullChuCai = "";
                    txtAnswer.setText(FullChuCai);
                    GvChuCai.setAdapter(ChuCaiAdapter);

                }
            }
        });
//            if(L.size()<10)
//            {
//                break;
//            }
//        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count_Down_Timer.cancel();
                NextPage();
            }
        });
    }


    public void AnhXa() {
        total_Time = 0;
        MemberList = new ArrayList<>();
        txtCorrectAnswer = (TextView) findViewById(R.id.CorrectAnswer);
        Arr_MaintainAnswer = new ArrayList<Integer>();
        txtCounterStar = (TextView) findViewById(R.id.CounterStar);
        txtTimer = (TextView) findViewById(R.id.textTimer);
        Arrays.fill(Frag_Easy, false);
        Arrays.fill(Frag_Medium, false);
        Arrays.fill(Frag_Hard, false);
        GvChuCai = (GridView) findViewById(R.id.Gv_Question);
        btnBack = (Button) findViewById(R.id.btn_Correct_goBack);
        txtAnswer = (TextView) findViewById(R.id.TxtAnswerCorrectWord);
        txtHint = (TextView) findViewById(R.id.HintCorrectWord);
        btnAnswer = (Button) findViewById(R.id.btn_Answer);
        btnSkip = (Button) findViewById(R.id.BtnSkip);
        Pgb = (ProgressBar) findViewById(R.id.progressBar2);

    }

    void Display(int i) {
        question = new ArrayList<>();
        ChuCai = new ArrayList<>();
        String test = "";
        if (L.get(i).question != null) {
            String[] Split_Ques = L.get(i).question.split(" ");
//        for(int a=0;a<Split_Ques.length;a++)
//        {
//            Log.e(Split_Ques[a]+"","asd");
//        }
            for (int k = 0; k < Split_Ques.length; k++) {
                question.add(Split_Ques[k]);
            }
//        Log.e("origin "+question,"1");
            Collections.shuffle(question);
//        Log.e("shuffer "+question,"1");
            while (true) {
                for (int j = 0; j < question.size(); j++) {
                    test += question.get(j);
                }
                if (!test.equals(L.get(i).answer.trim())) {
                    break;
                } else test = "";
            }
//        Log.e("test "+test,"1");

            for (int j = 0; j < question.size(); j++) {
                ChuCai.add(new ChuCai_CorrectWord(question.get(j)));
            }
            ChuCaiAdapter = new ChuCai_Adapter(ChuCai, this, R.layout.dong_correct_word);
            GvChuCai.setAdapter(ChuCaiAdapter);
            txtHint.setText(L.get(i).hint);
//        B.setText(L.get(i).AnswerB);
//        C.setText(L.get(i).AnswerC);
//        D.setText(L.get(i).AnswerD);
//        Ketqua.setText("Câu đúng:" + kq);
//        RG.clearCheck(); //xóa checked
        }

    }

    int CheckLevel(boolean CheckAnswer) {

        if (CheckAnswer)//Dúng
        {
            Level += 1;//TB
            if (Level > 2) {
                Level = 2;
            }
        } else//Sai
        {
            Level = Level - 1;
            if (Level < 0) {
                Level = 0;//De~
            }
        }
//        Log.e("CheckAnswer " + CheckAnswer + "Level " + Level, "asd");
        return Level;
    }


    void ReadData(boolean CheckAnswer) {
        int level = CheckLevel(CheckAnswer);
        English_CorrectWord Q1 = new English_CorrectWord();
        if (L.size() < 10) {
//            Log.e("Size " + L.size(), "asd");
            if (level == 0) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_easy.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
                    int Cauhoi_Easy = 0;
//                    while (true) {
//                        Cauhoi_Easy = random.nextInt(list.getLength() - 1);
//
//                        if (Cauhoi_Easy % 2 != 0) {
//                            if (Frag_Easy[Cauhoi_Easy] == false) {
//                                break;
//                            }
//                        }
//                    }
//                    for (int i = 0; i < list.getLength(); i++) {
                    while (true) {
                        Cauhoi_Easy = random.nextInt(list.getLength() - 1);
                        Node node = list.item(Cauhoi_Easy);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
                            if (Frag_Easy[Cauhoi_Easy] == false) {
//                                Log.e("i1 Cauhoi_Easy" + Cauhoi_Easy, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list
                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
//                                Log.e("i2 Cauhoi_Easy " + Cauhoi_Easy, "Asd");
                                Frag_Easy[Cauhoi_Easy] = true;
                                break;
                            }
                        }
                    }

//                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (level == 1) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_medium.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
                    int Cauhoi_Medium = 0;
                    while (true) {
                        Cauhoi_Medium = random.nextInt(list.getLength() - 1);
                        Node node = list.item(Cauhoi_Medium);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
                            if (Frag_Medium[Cauhoi_Medium] == false) {
//                                Log.e("i1 Cauhoi_Easy" + Cauhoi_Easy, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list
                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
//                                Log.e("i2 Cauhoi_Easy " + Cauhoi_Easy, "Asd");
                                Frag_Medium[Cauhoi_Medium] = true;
                                break;
                            }
                        }
                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (level == 2) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_hard.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root

//                    Log.e("list length Easy " + list.getLength(), "asd");
//                Frag_Easy[Cauhoi_Easy] = true;
//                Log.e("Frag Easy "+Cauhoi_Easy,"asd");
                    int Cauhoi_Hard = 0;
                    while (true) {
                        Cauhoi_Hard = random.nextInt(list.getLength() - 1);
                        Node node = list.item(Cauhoi_Hard);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
                            if (Frag_Hard[Cauhoi_Hard] == false) {
//                                Log.e("i1 Cauhoi_Easy" + Cauhoi_Easy, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list
                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
//                                Log.e("i2 Cauhoi_Easy " + Cauhoi_Easy, "Asd");
                                Frag_Hard[Cauhoi_Hard] = true;
                                break;
                            }
                        }
                    }
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            L.add(Q1);
            Log.e("pos " + pos, "asd");
            if (pos == 0) {
                Display(pos);
                CountDown();
            } else {
                Count_Down_Timer.cancel();
                Display(pos);
                CountDown();
            }
            ;//Hiển thị câu hỏi kế tiếp
//            if (pos >= L.size()) {
////            Intent intent = new Intent(MainActivity.this,activity_ketqua.class);
////            Bundle bundle = new Bundle();
////            bundle.putInt("KQ",kq);
////            bundle.putInt("Socau",pos);
////            intent.putExtra("MyPackage",bundle);
////            startActivity(intent);
//                pos =0; //Cho vị trí pos về câu hỏi đầu tiên
////            kq =0; //cho số câu hỏi đúng bằng 0, để làm lại
//                Display(pos); // Hiển thị lại nội dung
//            }
//            else {
//
//
//            }
        } else {
            if (page == false) {
//                Log.e("5","6");
                finish();
                Count_Down_Timer.cancel();
                Intent intent = new Intent(this, Activity_result_Correct.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CorrectAnswer", CorrectAnswer);
                save();
                Collections.sort(MemberList);
                Log.e("test" + MemberList, "");
                intent.putExtra("correct_play", bundle);
                startActivity(intent);
            }
        }
    }

    void CountDown() {

        Count_Down_Timer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                count = 0;
                count++;
                total_Time = total_Time + count;
                Log.e("Time : " + total_Time, "");
//                Log.e("1", "2");
                String StartTimeString = String.valueOf(StartTimeInt);
                if (StartTimeInt >= 10) {
                    txtTimer.setText("00:" + StartTimeString);
                } else {
                    txtTimer.setText("00:0" + StartTimeString);
                }
                StartTimeInt--;


            }

            @Override
            public void onFinish() {
                total_Time += count;
                StartTimeInt = 30;
                Arr_MaintainAnswer.add(-1);
                if (Arr_MaintainAnswer.size() > 2) {
                    Check_Maintain_Answer();
                }
                if (!page) {
                    pos++;
                    CheckAnswer = false;
                    ReadData(CheckAnswer);
                    FullChuCai = "";
                    txtAnswer.setText(FullChuCai);
                    current = Pgb.getProgress();
                    Pgb.setProgress(current + 10);
                }
            }
        }.start();

    }

    void Check_Maintain_Answer() {
//        Log.e("pos :" + posIndex, "ArrCheck :" + Arr_MaintainAnswer.size());
        String Str_HighStar;
        while (posIndex < Arr_MaintainAnswer.size()) {
//            Log.e("i " + posIndex, "1");
            if (Arr_MaintainAnswer.get(posIndex) != Arr_MaintainAnswer.get(posIndex + 1)) {
                temp = Arr_MaintainAnswer.get(Arr_MaintainAnswer.size() - 1);
                Arr_MaintainAnswer = new ArrayList<>();
                Arr_MaintainAnswer.add(temp);
                posIndex = 0;
                Check_Maintain_Count_Star = 0;
                break;
            } else {
                Check_Maintain_Count_Star++;
//                Log.e("asdjklasdjlkasjdk ","1");
                if (Check_Maintain_Count_Star == 2) {
                    if (Arr_MaintainAnswer.get(posIndex) == 1) {
                        High_Star++;
                        Str_HighStar = String.valueOf(High_Star);
                        txtCounterStar.setText(Str_HighStar);
                        Check_Maintain_Count_Star = 0;
                        Arr_MaintainAnswer = new ArrayList<>();
                        posIndex = 0;
                        break;
                    } else if (Arr_MaintainAnswer.get(posIndex) == -1) {
                        if (txtCounterStar.getText().toString().equals("0")) {
//                            Log.e("3","4");
                            page = true;
                            Count_Down_Timer.cancel();
                            finish();
                            Intent intent = new Intent(Activity_correct_play.this, Activity_playAgain.class);
                            startActivity(intent);
//                            onRestart();
//                            finish();
                            break;
                        }
                        High_Star--;
                        Str_HighStar = String.valueOf(High_Star);
                        txtCounterStar.setText(Str_HighStar);
                        Check_Maintain_Count_Star = 0;
                        Arr_MaintainAnswer = new ArrayList<>();
                        posIndex = 0;
                        break;
                    }
                }
                posIndex = posIndex + 1;
            }
        }
    }

    void NextPage() {
        Intent intent = new Intent(this, Activity_correct_home.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.nextpage,R.anim.backpage);
    }

    void save() {
        boolean check = false;
        SharedPreferences myPrefs;
        String email = "";
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        email = myPrefs.getString("Email1", "");

//        MemberList = new ArrayList<>();
        if (MemberList.size() > 0) {
            Log.e("not null", "asdasd");
            MemberList = MemberList();
            for (int i = 0; i < MemberList.size(); i++) {
                Log.e("for", "");
                Log.e("membername", "" + MemberList.get(i).getName());
                Log.e("email", "" + email);
                if (MemberList.get(i).getName().trim().compareTo(email.trim()) == 0) {
                    Log.e("name", "");
                    MemberList.remove(i);
                    MemberList.add(i, new member(email, CorrectAnswer, High_Star, total_Time));
                    check = true;
                    break;
                }
            }
            Log.e("" + MemberList.size(), "size");
        }
        if (MemberList.size() == 0) {//Neu File Rong thi Add 1 Member
            MemberList.add(new member(email, CorrectAnswer, High_Star, total_Time));
            check = true;
        }
        if (MemberList.size() > 10)//Loc file cho gon gang
        {
            for (int i = MemberList().size() - 1; i > 5; i--)//5 : 0 -> 4
            {
                MemberList.remove(i);
            }
        }
        if (!check) {
            MemberList.add(new member(email, CorrectAnswer, High_Star, total_Time));
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fos);
            for (member mb : MemberList) {
                pw.println(mb.toStringCorrect());
            }
//            edtID.getText().clear();
            Toast.makeText(Activity_correct_play.this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ArrayList<member> MemberList() {
        Log.e("asdjklasdjlasdjk", "asdasdasdasdasdsad");
        ArrayList<member> getMemberList = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
            String text = null;
            while (true) {
                if ((text = br.readLine()) != null) {
                    if (text.trim() == " ") continue;
                    String[] list = text.split(" ");
                    getMemberList.add(new member(list[0], Integer.parseInt(list[1]), Integer.parseInt(list[2]), Integer.parseInt(list[3])));
//                    sb.append(text).append("\n");
                    Log.e("size", "" + getMemberList.size());
                } else {
                    return getMemberList;
                }

            }

//            Log.e("test",""+sb.toString());
//            for(member mb:MemberList)
//            {
//                Log.e("in ",""+mb.toString());
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}