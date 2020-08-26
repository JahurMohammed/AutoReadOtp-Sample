package com.e.otpexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    private static SmsListener mListener;
    Boolean isSender;
    String otp;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            // isSender=sender.endsWith("JMJIOMNY");  //Just to fetch otp sent from JMJIOMNY
            String messageBody = smsMessage.getMessageBody();
            otp = messageBody.replaceAll("[^0-9]", "");   // here abcd contains otp which is in number format

            // if(isSender==true) {
            mListener.messageReceived(otp);  // attach value to interface object
            //  }
//            else
//            {
            //do nothing
//            }
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
