package com.microsoft.CognitiveServicesExample;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.app.Service;
import android.os.Bundle;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.os.IBinder;

public class NativeBridge {
    private static MainActivity m_MainActivityService;
    private static boolean mServiceBound = false;
    public static Context sContext;


    private static ServiceConnection mServiceConnection = new ServiceConnection() {

        MainActivity myBinder;
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MainActivity.MyServiceLocalBinder myBinder =(MainActivity.MyServiceLocalBinder) service;
            m_MainActivityService = (MainActivity) myBinder.getService();

            mServiceBound = true;
            Log.e("Unity", "Service connected: " +  myBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
           // m_MainActivityService = null;
            myBinder =null;
            mServiceBound = false;
            Log.e("Unity", "Service disconnected");
        }
    };

    public static void startBindService(Activity unity) {
        Intent intent = new Intent(unity, MainActivity.class);
        unity.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void startRecognition(){
        m_MainActivityService.StartButton_Click();
    }

    public static void stopBindService(Activity unity) {
        if (mServiceBound) {
            unity.unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }
}
