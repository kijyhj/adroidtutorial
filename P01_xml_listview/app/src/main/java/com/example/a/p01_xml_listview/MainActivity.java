package com.example.a.p01_xml_listview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    private  String getElementText(Element data, String tag){
        NodeList hourList = data.getElementsByTagName(tag);
        Element hour = (Element) hourList.item(0);
        NodeList textNodeList = hour.getChildNodes();
        return textNodeList.item(0).getNodeValue();
    }

    class MyDomParser extends AsyncTask<String, Void, Document> {

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            //String str = "";
            ArrayList list = new ArrayList();

            NodeList dataList = document.getElementsByTagName("data");

            for (int i = 0; i < dataList.getLength(); i++) {
                Element data = (Element) dataList.item(i);

                String strHour = getElementText(data, "hour");
                String strDay = getElementText(data, "day");
                String strTemp = getElementText(data, "temp");
                String strWfKor = getElementText(data, "wfKor");

                list.add(strDay + "day " + strHour + "hour " + strTemp + "temp " + strWfKor + "wfKor ");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    list
            );

            listView.setAdapter(adapter);

        }

        @Override
        protected Document doInBackground(String... params) {
            Document doc = null;
            try {
                URL url = new URL(params[0]);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dbf.newDocumentBuilder();
                doc = builder.parse(url.openStream());
            }catch (Exception e){
                e.printStackTrace();
            }
            return doc;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        MyDomParser myDom = new MyDomParser();
        myDom.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");

        ImageView iv = (ImageView) findViewById(R.id.testImg);
        iv.setImageResource(R.drawable.rain);
    }
}
