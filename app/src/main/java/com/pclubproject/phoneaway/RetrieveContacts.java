package com.pclubproject.phoneaway;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Anubhav Mittal on 23-06-2016.
 */
public class RetrieveContacts {
    String getContact(Context context,String reqContact,String sendernumber)
    {
        try {
            int n = 0;
            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if (name.length() >= reqContact.length() && name.substring(0, reqContact.length()).equalsIgnoreCase(reqContact)) {
                        n++;
                    }
                }
                cur.close();
                cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (n == 1) {
                    String message = "Following numbers were found for the requested contact:\n";
                    while (cur.moveToNext()) {
                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if (name.length() >= reqContact.length() && name.substring(0, reqContact.length()).equalsIgnoreCase(reqContact)) {
                            if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                                Cursor pCur = cr.query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                        new String[]{id}, null);

                                while (pCur.moveToNext()) {
                                    message = message + pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "\n";
                                }
                                SmsSender smsSender = new SmsSender();
                                smsSender.sendsms(context, message, sendernumber);
                                pCur.close();
                                cur.close();
                                return message;
                            }
                        }
                    }

                } else if (n > 1) {
                    String message = "Multiple contacts were found starting as " + reqContact + ":\n\n";
                    while (cur.moveToNext()) {
                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if (name.length() >= reqContact.length() && name.substring(0, reqContact.length()).equalsIgnoreCase(reqContact)) {
                            if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                                Cursor pCur = cr.query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                        new String[]{id}, null);
                                message = message + "Numbers of contact " + name + ":\n";
                                while (pCur.moveToNext()) {
                                    message = message + pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "\n";
                                }
                                message = message + "\n";
                                pCur.close();
                            }
                        }
                    }
                    //For this part,long message is implemented.
                    SmsManager sms = SmsManager.getDefault();
                    ArrayList<String> parts = sms.divideMessage(message);
                    sms.sendMultipartTextMessage(sendernumber, null, parts, null, null);
                    cur.close();
                    return message;
                } else {
                    String message = "No contacts found that match with the one requested";
                    SmsSender smsSender = new SmsSender();
                    smsSender.sendsms(context, message, sendernumber);
                    return message;
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG);
            return "";
        }
        return "";
    }
}
