package com.example.administrator.mytestapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ImageView iv_image= (ImageView) findViewById(R.id.iv_image);
//        ImageView iv_image2= (ImageView) findViewById(R.id.iv_image2);

        //获取宽高
        BitmapFactory.Options options=new BitmapFactory.Options();
        //这样获取的bitmap是null,但是可以通过options获得图片宽高等属性
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.test, options);
        System.out.println("width====" + options.outWidth + "   height====" + options.outHeight);

//        options.inSampleSize=1/2;
        options.outWidth=120;
        options.outHeight=160;
        options.inScaled=true;
        options.inJustDecodeBounds=false;//这样就能获的bitmap
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.test,options);
        System.out.println("width====" + options.outWidth + "   height====" + options.outHeight);


        iv_image.setImageBitmap(bitmap);

        //把图片变成正方形
//        Bitmap bitmap2=Bitmap.createBitmap(bitmap,0,120,bitmap.getWidth(),bitmap.getWidth());
//        iv_image2.setImageBitmap(bitmap2);

    }


    public int calculateInSampleSize(BitmapFactory.Options op, int reqWidth,
                                     int reqheight) {
        int originalWidth = op.outWidth;
        int originalHeight = op.outHeight;
        int inSampleSize = 1;
        if (originalWidth > reqWidth || originalHeight > reqheight) {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;
            while ((halfWidth / inSampleSize > reqWidth)
                    &&(halfHeight / inSampleSize > reqheight)) {
                inSampleSize *= 2;

            }
        }
        return inSampleSize;
    }

    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :(int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


}
