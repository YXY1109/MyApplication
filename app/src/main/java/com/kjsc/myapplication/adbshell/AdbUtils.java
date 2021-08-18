package com.kjsc.myapplication.adbshell;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

public class AdbUtils {
    private static final int sleeptime = 3000;
    public static final String default_package = "com.kjsc.smartcommunity";
    public static final String default_activity = default_package + "/.activity.MainActivity";


    //启动应用
    public static void startapp() throws InterruptedException {
        String log_text = null;
        int startapp = RootCmd.execRootCmdSilent("am start -n " + default_activity);
        if (startapp == 0) {
            log_text = "启动应用成功：" + startapp;
        } else {
            log_text = "启动应用失败：" + startapp;
        }
        showMessage(log_text, 2 * sleeptime);
    }

    //强制关闭应用
    public static void stopapp() throws InterruptedException {
        String log_text = null;
        int startapp = RootCmd.execRootCmdSilent("am force-stop " + default_package);
        if (startapp == 0) {
            log_text = "关闭应用成功：" + startapp;
        } else {
            log_text = "关闭应用失败：" + startapp;
        }
        showMessage(log_text, 2 * sleeptime);
    }

    public static void tapXY(int x, int y) throws InterruptedException {
        String log_text = null;
        int tapxy = RootCmd.execRootCmdSilent("input tap " + x + " " + y);
        if (tapxy == 0) {
            log_text = "点击坐标x轴：" + x + "，y轴：" + y + " ！成功！";
        } else {
            log_text = "点击坐标x轴：" + x + "，y轴：" + y + " ！！失败！！";
        }
        showMessage(log_text, sleeptime);
    }

    //3=home 4=back 上=19 下=20 左=21 右=22
    public static void keycode(int keycode) throws InterruptedException {
        String log_text = null;
        int keyevent = RootCmd.execRootCmdSilent("input keyevent " + keycode);
        if (keyevent == 0) {
            log_text = "点击keyevent成功：" + keyevent;
        } else {
            log_text = "点击keyevent失败：" + keyevent;
        }
        showMessage(log_text, sleeptime);
    }

    public static void showMessage(String log_text, int time) throws InterruptedException {
        Log.i("yxy", log_text);
        ToastUtils.showShort(log_text);
        Thread.sleep(time);
    }
}
