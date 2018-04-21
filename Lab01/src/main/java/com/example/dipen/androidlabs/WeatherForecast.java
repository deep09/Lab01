package com.example.dipen.androidlabs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast extends Activity {

    private TextView tvCity_Type;
    private TextView tvMinTemp;
    private TextView tvMaxTemp;
    private TextView tvWindSpeed;
    private TextView tvCurrentTemp;
    private ImageView imgCurrentWeather;
    private ProgressBar pbDownloadImage;
    private static final String ACTIVITY_NAME = "WeatherForecast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        tvCity_Type = findViewById(R.id.tvCity_Type);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        imgCurrentWeather = findViewById(R.id.ivCurrentWeather);
        pbDownloadImage = findViewById(R.id.pbDownloadImage);
        ForecastQuery obj = new ForecastQuery();
        obj.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
    }


    public class ForecastQuery extends AsyncTask<String, Integer, String>{

        private String city;
        private String weatherType;
        private String windSpeed;
        private String min;
        private String max;
        private String currentTemprature;
        private Bitmap cwImg;
        private int eventType;
        private FileInputStream fis = null;

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream iStrem = httpURLConnection.getInputStream();

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(iStrem, "UTF-8");

                while((eventType = xmlPullParser.getEventType()) != XmlPullParser.END_DOCUMENT){

                    if(eventType == XmlPullParser.START_TAG) {

                        if(xmlPullParser.getName().equals("city")){
                            city = xmlPullParser.getAttributeValue(null,"name");
                        }

                        if (xmlPullParser.getName().equals("temperature")) {
                            currentTemprature = xmlPullParser.getAttributeValue(null,"value");
                            min = xmlPullParser.getAttributeValue(null, "min");
                            publishProgress(25);
                            Log.i("MIN TEMP: ",min);
                            max = xmlPullParser.getAttributeValue(null, "max");
                            publishProgress(50);
                            Log.i("MAX TEMP: ",max);
                        }

                        if (xmlPullParser.getName().equals("speed")) {
                            windSpeed = xmlPullParser.getAttributeValue(null, "value");
                            publishProgress(75);
                        }

                        if(xmlPullParser.getName().equals("weather")){
                            weatherType = xmlPullParser.getAttributeValue(null,"value");
                            String imageFile = xmlPullParser.getAttributeValue(null,"value");
                            String icon = xmlPullParser.getAttributeValue(null, "icon");
                            if(fileExistance(icon+".png"))
                            {
                                fis = openFileInput(icon+".png");
                                cwImg = BitmapFactory.decodeStream(fis);
                                Log.i(ACTIVITY_NAME,"Fetching Image From The Database."+fis.toString());
                            }else {
                                cwImg = getImage("http://openweathermap.org/img/w/" + icon + ".png");
                                Log.i(ACTIVITY_NAME,"Downloading Image From The URL.");
                            }
                            publishProgress(100);
                        }
                    }
                    xmlPullParser.next();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "Finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbDownloadImage.setVisibility(View.VISIBLE);
            pbDownloadImage.setProgress(values[0]);
        }

        @Override
        public void onPostExecute(String result){
            tvCity_Type.setText(city+"\n"+weatherType);
            tvCurrentTemp.setText(currentTemprature+"");
            tvMinTemp.setText("Min Temp: \n"+min);
            tvMinTemp.setTextSize(15);
            tvMaxTemp.setText("Max Temp: \n"+max);
            tvMaxTemp.setTextSize(15);
            tvWindSpeed.setText("Wind Speed: \n"+windSpeed);
            tvWindSpeed.setTextSize(15);
            imgCurrentWeather.setImageBitmap(cwImg);
            pbDownloadImage.setVisibility(View.INVISIBLE);
        }

        private Bitmap getImage(String imgURL){
            try {
                InputStream isImage = (InputStream) new URL(imgURL).getContent();
                return BitmapFactory.decodeStream(isImage);
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            Log.i(ACTIVITY_NAME,file.getPath());
            return file.exists();
        }
    }


}
