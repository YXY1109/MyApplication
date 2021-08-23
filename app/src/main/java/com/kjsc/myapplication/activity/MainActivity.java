package com.kjsc.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kjsc.myapplication.adbshell.RootCmd;
import com.kjsc.myapplication.adbshell.TapService;
import com.kjsc.myapplication.databinding.ActivityMainBinding;
import com.kjsc.utils.ToastUtils;

import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        setRadioButtonNULL(binding.radiobutton);

        binding.startService1.setOnClickListener(v -> {
            //
            Toast.makeText(this, "跳转到第二个页面", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });

        binding.startService2.setOnClickListener(v -> {
            //
            Log.i("yxy", "是否获取root权限：" + RootCmd.haveRoot());
            if (RootCmd.haveRoot()) {
                Toast.makeText(this, "获取root权限，启动服务", Toast.LENGTH_SHORT).show();
                //startService(new Intent(this, TapService.class));
            } else {
                Toast.makeText(this, "无root权限哈！！！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.startTengxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = getPackageManager().getLaunchIntentForPackage("com.ktcp.video");
                //startActivity(intent);
                startActivity(new Intent(MainActivity.this, MPChartActivity.class));
            }
        });
        binding.startYouku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = getPackageManager().getLaunchIntentForPackage("com.cibn.tv");
                //startActivity(intent);
                //startActivity(new Intent(MainActivity.this, VideoActivity.class));
                startActivity(new Intent(MainActivity.this, ExoPlayerActivity.class));

            }
        });
        binding.startAiqiyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = getPackageManager().getLaunchIntentForPackage("com.gitvdemo.video");
                //startActivity(intent);
            }
        });

        /**
         //加载sd卡图片
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inSampleSize = 2;
         Bitmap bm = BitmapFactory.decodeFile("/storage/emulated/0/screen.png", options);

         //裁剪图片 https://blog.csdn.net/qunqunstyle99/article/details/87869018
         Bitmap ret = Bitmap.createBitmap(bm, 50, 50, 100, 100);

         //图片ocr 图片文字提取，文件缺失
         //图片对比 opencv
         binding.sdPic.setImageBitmap(ret);
         **/

        //DisplayMetrics dm = new DisplayMetrics();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heigth = dm.heightPixels;// 表示屏幕的像素高度，单位是px（像素）
        int width = dm.widthPixels;// 表示屏幕的像素宽度，单位是px（像素）
        //Log.i("yxy", "width:" + width);
        //Log.i("yxy", "heigth:" + heigth);

        Log.e("yxy", "densityDpi: " + dm.densityDpi);
        Log.e("yxy", "density: " + dm.density);
        Log.e("yxy", "widthPixels: " + dm.widthPixels);
        Log.e("yxy", "heightPixels: " + dm.heightPixels);

    }

    public static void doCmds(List<String> cmds) throws Exception {
        Process process = Runtime.getRuntime().exec("su");
        DataOutputStream os = new DataOutputStream(process.getOutputStream());

        for (String tmpCmd : cmds) {
            os.writeBytes(tmpCmd + "\n");
        }

        os.writeBytes("exit\n");
        os.flush();
        os.close();

        process.waitFor();
    }

    private void setRadioButtonNULL(RadioButton radioButton) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            radioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
        } else {
            Log.i("yxy", "KITKAT22");
            radioButton.setBackground(null);
            radioButton.setButtonDrawable(null);
        }
    }
}