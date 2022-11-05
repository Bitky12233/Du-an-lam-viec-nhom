package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
class QuestionNare {
    public String ID;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;

}
public class Activity_quiz_play extends AppCompatActivity {
    private Button btn;
    TextView Cauhoi,Ketqua;
    RadioGroup RG;
    Button BT;
    RadioButton A,B,C,D;
    int pos=0;//vị trí câu hỏi trong danh sách
    int kq=0; //lưu số câu trả lời đúng
    ArrayList <QuestionNare> L = new ArrayList();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_play);
        Cauhoi = (TextView) findViewById(R.id.txt_question);
//        Ketqua = (TextView)findViewById(R.id.TxtKetqua);
        RG = (RadioGroup)findViewById(R.id.RadioGroupTraloi);
        BT = (Button) findViewById(R.id.btn_Answer);
        A = (RadioButton)findViewById(R.id.rb1);
        B = (RadioButton)findViewById(R.id.rb2);
        C = (RadioButton)findViewById(R.id.rb3);
        D = (RadioButton)findViewById(R.id.rb4);
        ReadData();
        Display(pos);
        btn = (Button) findViewById(R.id.btn_Quiz_goBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idCheck = RG.getCheckedRadioButtonId();
                switch (idCheck) {
                    case R.id.rb1:
                        //Nếu đáp án là câu A thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("A")==0) kq = kq+1;
                        break;
                    case R.id.rb2:
                        //Nếu đáp án là câu B thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("B")==0) kq = kq+1;
                        break;
                    case R.id.rb3:
                        //Nếu đáp án là câu C thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("C")==0) kq = kq+1;
                        break;
                    case R.id.rb4:
                        //Nếu đáp án là câu D thì cộng kq lên 1
                        if (L.get(pos).Answer.compareTo("D")==0) kq = kq+1;
                        break;
                }
                pos++;
                if (pos >= L.size()) {
                    Intent intent = new Intent(Activity_quiz_play.this,activity_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ",kq);
                    bundle.putInt("Socau",pos);
                    intent.putExtra("MyPackage",bundle);
                    startActivity(intent);
                    pos =0; //Cho vị trí pos về câu hỏi đầu tiên
                    kq =0; //cho số câu hỏi đúng bằng 0, để làm lại
                    Display(pos); // Hiển thị lại nội dung
                }
                else Display(pos);
                gobackAcivity_quiz_home();
            }
        });
    }
    public void gobackAcivity_quiz_home() {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }

    void Display(int i){
        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        Ketqua.setText("Câu đúng:" + kq);
        RG.clearCheck(); //xóa checked
    }
    void ReadData() {
        try {
            //Tạo đối tượng DocumentBuilder (builder )
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            //Tạo FileInputStream từ tập tin XML nguồn
            InputStream in = getAssets().open("data.xml");
            //Dùng phương thức parse của đối tượng builder để tạo Document
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//lấy tag Root
            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
            for (int i = 0; i < list.getLength(); i++) {
// duyệt từ node đầu tiên cho tới node cuối cùng
                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
// kiểm tra xem node đó có phải là Element hay không
                if (node instanceof Element) {
                    Element Item = (Element) node;// lấy được tag Item
                    // lấy tag ID bên trong của tag Item
                    NodeList listChild = Item.getElementsByTagName("ID");
                    //lấy nội dung của tag ID
                    String ID = listChild.item(0).getTextContent();
                    // lấy tag Question
                    listChild = Item.getElementsByTagName("Question");
                    // lấy nội dung Question
                    String Question = listChild.item(0).getTextContent();
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
                    //lưu vào list
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
}
