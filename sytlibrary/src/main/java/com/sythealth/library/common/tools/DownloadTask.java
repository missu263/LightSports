package com.sythealth.library.common.tools;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Handler;
import android.os.Message;

public class DownloadTask extends Thread {
    private static final String TAG = "DownloadTask";

    private String downloadUrl;// 下载链接地址
    private int threadNum;// 开启的线程数
    private String filePath;// 保存文件路径地址
    private int blockSize;// 每一个线程的下载量
    private Handler mHandler;

    public DownloadTask(String downloadUrl, int threadNum, String fileptah, Handler mHandler) {
        this.downloadUrl = downloadUrl;
        this.threadNum = threadNum;
        this.filePath = fileptah;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        // 通知handler去更新视图组件
        FileDownloadThread[] threads = new FileDownloadThread[threadNum];
        try {
            URL url = new URL(downloadUrl);
            LogUtil.d(TAG, "download file http path:" + downloadUrl);
            URLConnection conn = url.openConnection();
            // 读取下载文件总大小
            int fileSize = conn.getContentLength();
            if (fileSize <= 0) {
                System.out.println("读取文件失败");
                return;
            }
            // 设置ProgressBar最大的长度为文件Size

            // 计算每条线程下载的数据长度
            blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum : fileSize / threadNum + 1;

            File file = new File(filePath);
            for (int i = 0; i < threads.length; i++) {
                // 启动线程，分别下载每个线程需要下载的部分
                threads[i] = new FileDownloadThread(url, file, blockSize, (i + 1));
                threads[i].setName("Thread:" + i);
                threads[i].start();
            }

            boolean isfinished = false;
            int downloadedAllSize = 0;
            while (!isfinished) {
                isfinished = true;
                // 当前所有线程下载总量
                downloadedAllSize = 0;
                for (int i = 0; i < threads.length; i++) {
                    downloadedAllSize += threads[i].getDownloadLength();
                    if (!threads[i].isCompleted()) {
                        isfinished = false;
                    }
                    // sendMsg(0, Integer.valueOf(downloadedAllSize / fileSize *
                    // 100));
                }
                // 通知handler去更新视图组件
                LogUtil.d(TAG, "current downloadSize:" + downloadedAllSize);
                int x = (int) (downloadedAllSize * 100.0 / fileSize );
                sendMsg(0, x);
                Thread.sleep(500);// 休息1秒后再读取下载进度
            }
            if (isfinished) {
                // 通知handler去更新视图组件
                sendMsg(1, 100);
            }
            LogUtil.d(TAG, " all of downloadSize:" + downloadedAllSize);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            sendMsg(-1, 0);
        } catch (IOException e) {
            e.printStackTrace();
            sendMsg(-1, 0);
        } catch (Exception e) {
            e.printStackTrace();
            sendMsg(-1, 0);
        }
    }

    private void sendMsg(int what, int progress) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = progress;
        mHandler.sendMessage(msg);
    }

}
