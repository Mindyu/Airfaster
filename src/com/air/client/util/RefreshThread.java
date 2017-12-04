package com.air.client.util;

public class RefreshThread implements Runnable{

    @Override
    public void run() {

        while (true){
            try {
                //刷新列表
                JTableOperation jTableOperation = new JTableOperation();
                jTableOperation.reloadJTable(GlobalObject.getjTable());
                //10秒刷新
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
