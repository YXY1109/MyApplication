package com.kjsc.myapplication.adbshell;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.kjsc.myapplication.adbshell.RootCmd;

import java.util.Timer;
import java.util.TimerTask;

public class TapService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Log.i("yxy", "1开始执行adb shell");

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.i("yxy", "------开始执行任务，循环------");
                    loopTask();
                }
            }, 5 * 1000, 30 * 1000);


        } catch (Exception e) {
            Log.i("yxy", "3shell execption:" + e.getMessage());
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void loopTask() {
        try {
            //强制停止
            AdbUtils.stopapp();
            //启动应用
            AdbUtils.startapp();
            //点击坐标 模拟器
            AdbUtils.tapXY(723, 487);
            AdbUtils.tapXY(500, 320);

            //盒子
//            AdbUtils.tapXY(640, 450);
//            AdbUtils.tapXY(600, 250);

            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_DOWN);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_RIGHT);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_DOWN);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_DOWN);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_LEFT);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_DOWN);
            AdbUtils.keycode(KeyEvent.KEYCODE_DPAD_DOWN);

            //AdbUtils.keycode(KeyEvent.KEYCODE_BACK);
            AdbUtils.keycode(KeyEvent.KEYCODE_HOME);

            //强制停止
            AdbUtils.stopapp();

//                        int rootresultint = RootCmd.execRootCmdSilent("input tap 30 40");
//                        if (rootresultint == 0) {
//                            Thread.sleep(3000);
//                            Log.i("yxy", "执行成功！！开始截图：" + rootresultint);
//                            int screencap = RootCmd.execRootCmdSilent("screencap /sdcard/screen.png");
//                            Log.i("yxy", "截图：" + screencap);
//                            String path = Environment.getExternalStorageDirectory() + "/screen.png";
//                            Log.i("yxy", "截图地址：" + path);
//
//                        } else {
//                            Log.i("yxy", "执行失败！！：" + rootresultint);
//                        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
