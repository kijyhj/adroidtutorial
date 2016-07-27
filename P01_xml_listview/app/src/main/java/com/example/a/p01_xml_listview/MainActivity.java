package com.example.a.p01_xml_listview;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<MyData> myList = new ArrayList();

    class MyData{
        int imgId;
        String strTitle;

        public MyData(int imgId, String strTitle) {
            this.imgId = imgId;
            this.strTitle = strTitle;
        }
    }

    private  String getElementText(Element data, String tag){
        NodeList hourList = data.getElementsByTagName(tag);
        Element hour = (Element) hourList.item(0);
        NodeList textNodeList = hour.getChildNodes();
        return textNodeList.item(0).getNodeValue();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public Object getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inf = getLayoutInflater();
                convertView = inf.inflate(R.layout.list_item, null);
            }

            MyData data = myList.get(position);
            TextView itemTitle = (TextView)convertView.findViewById(R.id.listText);
            ImageView itemImg = (ImageView)convertView.findViewById(R.id.listIcon);

            itemTitle.setText(data.strTitle);
            itemImg.setImageResource(data.imgId);

            return convertView;
        }
    }

    class MyDomParser extends AsyncTask<String, Void, Document> {

        @TargetApi(Build.VERSION_CODES.KITKAT)
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
                String strWfEng = getElementText(data, "wfEn");
                int img = R.drawable.sunny;

                String str = strDay + "day " + strHour + "hour " + strTemp + "temp " + strWfKor + "wfKor ";
                if(strWfEng.indexOf("Cloudy") > -1){
                    img = R.drawable.cloud;
                }else if(strWfEng.indexOf("Rain") > -1){
                    img = R.drawable.rain;
                }
                MyData myData = new MyData(img, str);
                list.add(str);
                myList.add(myData);
            }

            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    list
            );*/

            MyAdapter adapter = new MyAdapter();

            listView.setAdapter(adapter);

            //listView.setAdapter(adapter);

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

    }
}
