package com.kjsc.myapplication.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.kjsc.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;

    private String web_path = "https://media.w3.org/2010/05/sintel/trailer.mp4";//网络地址
    private String upan_path = GetUsbPath() + "/kongjian555.mp4";//u盘地址
    private String upan_path2 = getAllExternalSdcardPath() + "/kongjian555.mp4";//u盘地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoview);

        String usbPath = GetUsbPath();// U盘根目录：/mnt/usb/sda1
        Log.i("yxy", "usbPath+::" + usbPath);

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoActivity.this, "开始播放网络视频", Toast.LENGTH_SHORT).show();
                playvideo1();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoActivity.this, "开始播放本地视频1", Toast.LENGTH_SHORT).show();
                playvideo2();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoActivity.this, "开始播放本地视频2", Toast.LENGTH_SHORT).show();
                playvideo3();
            }
        });



    }

    private void playvideo1() {
        //设置视频路径
        videoView.setVideoURI(Uri.parse(web_path));
        //开始播放视频
        videoView.start();
    }

    private void playvideo2() {
        videoView.setVideoPath(upan_path);
        //开始播放视频
        videoView.start();

    }

    private void playvideo3() {
        videoView.setVideoPath(upan_path2);
        //开始播放视频
        videoView.start();

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(VideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }


    //https://blog.csdn.net/zhangyalong_android/article/details/88718367
    public String GetUsbPath() {
        String strMountInfo = "";
        // 1.首先获得系统已加载的文件系统信息
        try {
            // 创建系统进程生成器对象
            ProcessBuilder objProcessBuilder = new ProcessBuilder();
            // 执行 mount -h 可以看到 mount : list mounted filesystems
            // 这条命令可以列出已加载的文件系统
            objProcessBuilder.command("mount"); // 新的操作系统程序和它的参数
            // 设置错误输出都将与标准输出合并
            objProcessBuilder.redirectErrorStream(true);
            // 基于当前系统进程生成器的状态开始一个新进程，并返回进程实例
            Process objProcess = objProcessBuilder.start();
            // 阻塞线程至到本地操作系统程序执行结束，返回本地操作系统程序的返回值
            objProcess.waitFor();
            // 得到进程对象的输入流，它对于进程对象来说是已与本地操作系统程序的标准输出流(stdout)相连接的
            InputStream objInputStream = objProcess.getInputStream();
            byte[] buffer = new byte[1024];
            // 读取 mount 命令程序返回的信息文本
            while (-1 != objInputStream.read(buffer)) {
                strMountInfo = strMountInfo + new String(buffer);
            }
            // 关闭进程对象的输入流
            objInputStream.close();
            // 终止进程并释放与其相关的任何流
            objProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.然后再在系统已加载的文件系统信息里查找 SD 卡路径
        // mount 返回的已加载的文件系统信息是以一行一个信息的形式体现的，
        // 所以先用换行符拆分字符串
        String[] lines = strMountInfo.split("\n");
        // 清空该字符串对象，下面将用它来装载真正有用的 SD 卡路径列表
        strMountInfo = "";
        for (int i = 0; i < lines.length; i++) {
            // 如果该行内有 /mnt/和 vfat 字符串，说明可能是内/外置 SD 卡的挂载路径
            if (-1 != lines[i].indexOf(" /mnt/") && // 前面要有空格，以防断章取义
                    -1 != lines[i].indexOf(" vfat ")) // 前后均有空格
            {
                // 再以空格分隔符拆分字符串
                String[] blocks = lines[i].split("\\s"); // \\s 为空格字符
                for (int j = 0; j < blocks.length; j++) {
                    // 如果字符串中含有/mnt/字符串，说明可能是我们要找的 SD 卡挂载路径
                    if (-1 != blocks[j].indexOf("/mnt/")) {
                        // 排除重复的路径
                        if (-1 == strMountInfo.indexOf(blocks[j])) {
                            // 用分号符(;)分隔 SD 卡路径列表，
                            strMountInfo += blocks[j]; //此处位一个插入一个U盘时的路径，如果U盘过多可能拼到一起。
                        }
                    }
                }
            }
        }
        return strMountInfo;
    }

    public static String getAllExternalSdcardPath() {
        List<String> PathList = new ArrayList<String>();

        String firstPath = Environment.getExternalStorageDirectory().getPath();

        try {
            // 运行mount命令，获取命令的输出，得到系统中挂载的所有目录
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                // 将常见的linux分区过滤掉
                if (line.contains("proc") || line.contains("tmpfs") || line.contains("media") || line.contains("asec") || line.contains("secure") || line.contains("system") || line.contains("cache")
                        || line.contains("sys") || line.contains("data") || line.contains("shell") || line.contains("root") || line.contains("acct") || line.contains("misc") || line.contains("obb")) {
                    continue;
                }

                // 下面这些分区是我们需要的
                if (line.contains("fat") || line.contains("fuse") || (line.contains("ntfs"))) {
                    // 将mount命令获取的列表分割，items[0]为设备名，items[1]为挂载路径
                    String items[] = line.split(" ");
                    if (items != null && items.length > 1) {
                        String path = items[1].toLowerCase(Locale.getDefault());
                        // 添加一些判断，确保是sd卡，如果是otg等挂载方式，可以具体分析并添加判断条件
                        if (path != null && path.contains("sd")) {
                            return items[1];
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}