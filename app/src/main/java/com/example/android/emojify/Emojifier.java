package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by xplore on 8/3/17.
 */

public class Emojifier {

    private static final String TAG = Emojifier.class.getSimpleName();

    static void detectFaces(Context context, Bitmap picimage){


        FaceDetector faceDetector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false).setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        Frame frame = new Frame.Builder().setBitmap(picimage).build();

        SparseArray<Face> faces = faceDetector.detect(frame);

        Log.d(TAG,"number detect "+faces.size());

        if (faces.size() == 0){
            Toast.makeText(context, "No detct faces", Toast.LENGTH_SHORT).show();
        }else{

            for(int i = 0; i< faces.size() ; ++i){
                Face face = faces.valueAt(i);

                getClassification(face);
            }
        }

        faceDetector.release();


    }
    private static void getClassification(Face face){
        
        Log.d(TAG, "value smile " + face.getIsSmilingProbability());
        Log.d(TAG, "value eye right "+ face.getIsRightEyeOpenProbability());
        Log.d(TAG,"value of left eye "+ face.getIsLeftEyeOpenProbability());

    }
}
