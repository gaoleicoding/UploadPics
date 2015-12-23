package com.example.administrator.uploadphoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class UploadPhotoUtil<MultipartEntity> {

    final static int BUFFER_SIZE = 1024;
    public static UploadPhotoUtil uploadPhotoUtil;


    public static UploadPhotoUtil getInstance() {
        if (uploadPhotoUtil == null) {
            uploadPhotoUtil = new UploadPhotoUtil();
        }
        return uploadPhotoUtil;
    }

    public String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
    }

    public String getUploadBitmapZoomString(String filePath) {

        String fileString = "";
        try {
            Log.d("gaolei",
                    "file.length()------------------------"
                            + new Date(System.currentTimeMillis()));
            Bitmap bitmap = trasformToZoomBitmapAndLessMemory(filePath);// ת��ͼƬ
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, bos);
            byte[] result = bos.toByteArray();
            fileString = new String(result, "ISO-8859-1");
            Log.d("gaolei",
                    "UploadPhotoZoom--------end---------"
                            + new Date(System.currentTimeMillis()));
            Log.d("gaolei",
                    "file.length()-----------ZoomedToStr----------"
                            + fileString.length());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileString;

    }

    //把图片转化为字符串上传
    public String getUploadPhotoZoomString(String filePath) {
        Log.d("gaolei",
                "file.length()------------------------"
                        + new Date(System.currentTimeMillis()));
        String fileString = "";
        try {

            Bitmap bitmap = trasformToZoomPhotoAndLessMemory(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, bos);
            byte[] result = bos.toByteArray();
            fileString = new String(result, "ISO-8859-1");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("gaolei",
                "UploadPhotoZoom--------end---------"
                        + new Date(System.currentTimeMillis()));
        Log.d("gaolei",
                "file.length()-----------ZoomedToStr----------"
                        + fileString.length());
        return fileString;

    }

    public static byte[] InputStreamTOByteArray(InputStream in)
            throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        int count = -1;
        byte[] data = new byte[BUFFER_SIZE];
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
            outStream.write(data, 0, count);
        }
        data = null;
        outStream.close();
        return outStream.toByteArray();

    }

    /* 如果有些手机拍照反转，读取角度*/
    public int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /* 如果有些手机拍照反转，那么把图片调正 */
    public Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }


    public Bitmap trasformToZoomBitmapAndLessMemory(String url) {
        File file = new File(url);
        Log.d("gaolei", "file.length()---------------------" + file.length());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int inSampleSize = 1;
        if (file.length() < 256 * 1024) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        } else if (file.length() < 512 * 1024) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 2;
            inSampleSize = 2;
        } else if (file.length() < 1024 * 1024) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 4;
            inSampleSize = 4;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 6;
            inSampleSize = 6;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inJustDecodeBounds = false;
        Log.d("gaolei", "inSampleSize-----------------" + inSampleSize);
        int degree = readPictureDegree(file.getAbsolutePath());
        InputStream is = null;
        try {
            is = new FileInputStream(url);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap cameraBitmap = BitmapFactory.decodeStream(is, null, options);
        // Bitmap cameraBitmap = BitmapFactory.decodeFile(url, options);
        Bitmap photo = rotaingImageView(degree, cameraBitmap);
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return photo;
    }

    //注意显示本地图片时一定要压缩质量，不然容易出现OOM
    public Bitmap trasformToZoomPhotoAndLessMemory(String url) {
        File file = new File(url);

        Log.d("gaolei", "file.length()--------original-------------" + file.length());
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 通过这个bitmap获取图片的宽和高
        options.inJustDecodeBounds = true;
        int inSampleSize = 1;
        if (file.length() < 256 * 1024) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else if (file.length() < 512 * 1024) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 2;
            inSampleSize = 2;
        } else if (file.length() < 1024 * 1024) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 4;
            inSampleSize = 4;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 6;
            inSampleSize = 6;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的
        options.inJustDecodeBounds = false;

        Log.d("gaolei", "inSampleSize-----------------" + inSampleSize);
        int degree = readPictureDegree(file.getAbsolutePath());
        // Log.d("gaolei", "degree------------uploadImg--------------" +
        // degree);
        InputStream is = null;
        try {
            is = new FileInputStream(url);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap cameraBitmap = BitmapFactory.decodeStream(is, null, options);
        // Bitmap cameraBitmap = BitmapFactory.decodeFile(url, options);
        Bitmap photo = rotaingImageView(degree, cameraBitmap);
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return photo;
    }

    public Bitmap trasformToZoomBitmapInHeightAndWidth(Context context,
                                                       String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(url, options);
        options.inSampleSize = calculateInSampleSize(options, CommonUtils
                .getUtilInstance().dp2px(context, 80), CommonUtils
                .getUtilInstance().dp2px(context, 80));
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inJustDecodeBounds = false;

        File file = new File(url);
        int degree = readPictureDegree(file.getAbsolutePath());

        InputStream is = null;
        try {
            is = new FileInputStream(url);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap cameraBitmap = BitmapFactory.decodeStream(is, null, options);
        // Bitmap cameraBitmap = BitmapFactory.decodeFile(url, options);
        Bitmap photo = rotaingImageView(degree, cameraBitmap);
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return photo;
    }

    public void setImageDrawable(Context context, ImageView imageView,
                                 int drawable) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(drawable);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        try {

            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bitmap);
        imageView.setImageDrawable(bd);
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = (int) Math.floor(height / reqHeight);
            final int widthRatio = (int) Math.floor(width / reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

        }
        return inSampleSize;
    }
}
