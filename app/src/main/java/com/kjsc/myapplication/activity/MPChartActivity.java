package com.kjsc.myapplication.activity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.kjsc.myapplication.MyMarkerView;
import com.kjsc.myapplication.R;
import com.kjsc.myapplication.databinding.ActivityMpchartBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MPChartActivity extends AppCompatActivity {

    private ActivityMpchartBinding binding;

    private int[] mLableYHeartRate = new int[]{11, 13, 15, 18, 35, 40, 26, 23, 19, 34, 24, 3};
    String[] mLableXHeartRate = new String[]{"00.00", "02.00", "04.00", "06.00", "08.00", "10.00", "12.00", "14.00", "16.00", "18.00", "20.00", "22.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMpchartBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initLineChart();
        setLineData();
    }

    private void initLineChart() {
        //设置线状图不显示描述
        binding.chart.setDescription(null);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        // Set the marker to the chart
        mv.setChartView(binding.chart);
        binding.chart.setMarker(mv);

        //获取柱状图的X轴
        XAxis xAxis = binding.chart.getXAxis();
        //下面两个是获取Y轴  包括左右
        YAxis axisLeft = binding.chart.getAxisLeft();
        YAxis axisRight = binding.chart.getAxisRight();
        //设置XY轴
        setAXis(xAxis, axisLeft, axisRight);
    }

    public void setAXis(XAxis xaxis, YAxis yAxisLeft, YAxis yAxisRight) {
        //设置X轴在图底部显示
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴的宽度
        //xaxis.setAxisLineWidth(1);
        xaxis.setAxisLineColor(Color.WHITE);
        //起始0坐标开始
        xaxis.setAxisMinimum(0);
        //设置X轴显示轴线
        xaxis.setDrawAxisLine(false);
        //x的表格线不显示
        xaxis.setDrawGridLines(false);
        //设置X轴显示
        xaxis.setEnabled(true);

        xaxis.setLabelCount(mLableXHeartRate.length, true);
        xaxis.setTextColor(Color.WHITE);

        //x轴显示字符串
        xaxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return mLableXHeartRate[(int) value];
            }
        });

        //y轴显示字符串
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "℃";
            }
        });
        //y轴0刻度
        yAxisLeft.setAxisMinimum(-10);
        //不画网格线
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setAxisLineColor(Color.WHITE);
        //显示Y轴轴线
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setTextColor(Color.WHITE);

        yAxisLeft.enableGridDashedLine(10f, 10f, 0f);

        //不显示右Y轴
        yAxisRight.setEnabled(false);

        //左下角的图例
        Legend legend = binding.chart.getLegend();
        legend.setEnabled(false);
    }

    public void setLineData() {
        List<Entry> mListEnryMin = new ArrayList<>();

        for (int i = 0; i < mLableXHeartRate.length; i++) {
            //添加x,y坐标的值
            mListEnryMin.add(new Entry(i, mLableYHeartRate[i]));
        }
        LineDataSet lineDataSet = new LineDataSet(mListEnryMin, "");
        //圆点实心还是空心
        lineDataSet.setDrawCircleHole(true);
        //两点之间线条的模式
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置线条颜色
        lineDataSet.setColors(Color.parseColor("#2cca8f"));
        //设置折线图转择点的值的大小
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.parseColor("#2cca8f"));

//        float mScore=10;
//        lineDataSet.setHighlightEnabled(true);//设置是否显示十字线
//        binding.chart.highlightValue(mScore, 0);
//        binding.chart.setHighlightPerDragEnabled(true);
//        binding.chart.setHighlightPerTapEnabled(false);
//        binding.chart.setMaxHighlightDistance(mScore);

        LineData lineData = new LineData(lineDataSet);
        binding.chart.setData(lineData);

        lineDataSet.setHighlightEnabled(true);//设置是否显示十字线
        binding.chart.highlightValue(1, 0);
//        binding.chart.setHighlightPerDragEnabled(true);
//        binding.chart.setHighlightPerTapEnabled(true);
//        binding.chart.setMaxHighlightDistance(1);
    }
}
