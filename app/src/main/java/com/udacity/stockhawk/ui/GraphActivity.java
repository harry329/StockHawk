package com.udacity.stockhawk.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphActivity extends Activity {

    private final static String KEY_HISTORY = "history";
    private LineChart mLineChart;
    private Intent mIntent;
    private String mHistoryString;
    private HashMap<String,String> historyMap = new HashMap<>();
    List<Entry> entries = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mIntent = getIntent();
        mHistoryString = mIntent.getStringExtra(KEY_HISTORY);
        extractHistoryString(mHistoryString);
        mLineChart = (LineChart) findViewById(R.id.chart);
        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate();
    }

    private void extractHistoryString(String mHistoryString) {
        mHistoryString = mHistoryString.trim();
        mHistoryString = mHistoryString.replace("\n",",");
        Log.d("Graph history spring", mHistoryString);
        if(mHistoryString != null) {
            String[] historyArray = mHistoryString.split(",");
            if(historyArray != null) {
                for(int i = 0; i <historyArray.length; i=i+2) {
                    int t = i;
                    int k = i+1;
                    //historyMap.put(historyArray[t],historyArray[t++]);
                    entries.add(new Entry(Float.parseFloat(historyArray[k].trim()), Float.parseFloat(historyArray[t].trim())));
                }
            }
        }

    }

    public static Intent getGraphIntent(Context context, String historyString) {
        Intent  intent = new Intent(context,GraphActivity.class);

        intent.putExtra(KEY_HISTORY,historyString);
        return intent;
    }


}
