package com.example.lab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {
    private static final int CHOOSE_PHOTO = 2;
    private ImageView imageView;
    String imgPath;
    static int state = 0; // 0：等待接收图片；1：正在接收参数；2：正在发送参数
    private int num = 0x72;
    static float dx = 0;
    static float dy = 0;
    static Matrix matrix = new Matrix();
    Timer timer = new Timer();
    private boolean isInitiator = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNetPermission();

        @SuppressLint("HandlerLeak")
        final Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x71) {
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    state = 1;
                    System.out.println("**Main.run.0 Download photo succeed. ");
                } else if (msg.what == num) {
                    num++;
                    Point temp = (Point) msg.obj;

                    if (imageView.getDrawable() != null) {
                        if (isInitiator) {
                            matrix.postTranslate(temp.getX(), temp.getY());
                        } else {
                            matrix.postTranslate(temp.getX() - (float) imageView.getDrawable().getBounds().width() / 2, temp.getY());
                            isInitiator = true;
                        }
                    } else {
                        matrix.postTranslate(temp.getX(), temp.getY());
                    }

                    imageView.setImageMatrix(matrix);
                    System.out.println("**Main.run.1 Download parm succeed. ");
                }
            }
        };

        Thread timerThread = new Thread(timer);
        timerThread.start();

        // 得到button实例
        Button imageViewButton = findViewById(R.id.buttonLoadImage);
        Button imageShareButton = findViewById(R.id.buttonShareImage);
        // 得到imageView实例
        imageView = findViewById(R.id.imageView);

        // 设置监听图片拖动事件
        imageView.setLongClickable(true);
        imageView.setOnTouchListener(new ImageViewOnTouchListener());

        // 设置监听按钮点击事件
        imageViewButton.setOnClickListener(v -> checkPermission());
        imageShareButton.setOnClickListener(v -> uploadImage());

        Thread get = new Thread(new AccessNetworkWithGetting(imageView, h));
        get.start();
    }

    public void uploadImage() {
        if (imgPath == null) {
            return;
        }

        boolean res = Util.uploadFile(imgPath, "Test04.jpg");
        System.out.println("**MainActivity.sendImage:" + res);

        matrix.postTranslate(imageView.getWidth(), 0);
        imageView.setImageMatrix(matrix);
        isInitiator = true;

        Thread get = new Thread(new AccessNetworkWithInit(imageView));
        get.start();
    }

    public void getNetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_WIFI_STATE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    /**
     * 检查权限
     * 发现没有权限，调用requestPermissions方法向用户申请权限，requestPermissions接收三个参数，第一个是context，第二个是一个String数组
     * 把要申请的权限名放在数组中即可，第三个是请求码，只要是唯一值就行
     * 有权限就打开相册
     */
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    /**
     * 通过intent打开相册，使用startActivityForResult方法启动activity，会返回到onActivityResult方法，所以重载onActivityResult方法
     */
    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 获得了用户的授权结果，保存在grantResults中，判断grantResult中的结果来决定接下来的操作
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data); // 高于4.4版本使用此方法处理图片
                } else {
                    handleImageBeforeKitKat(data); // 低于4.4版本使用此方法处理图片
                }
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                // 解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android,providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    // 获得图片路径
    public String getImagePath(Uri uri, String selection) {
        String path = null;
        // 内容提供器
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // 获取路径
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }
        assert cursor != null;
        cursor.close();
        return path;
    }

    // 展示图片
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            // 格式化图片
            Bitmap bitImage = BitmapFactory.decodeFile(imagePath);
            // 为imageView设置图片
            imgPath = imagePath;
            imageView.setImageBitmap(bitImage);
        } else {
            Toast.makeText(MainActivity.this, "Fail To Get This Image", Toast.LENGTH_SHORT).show();
        }
    }

    private final class ImageViewOnTouchListener implements View.OnTouchListener {
        PointF startPoint = new PointF();

        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()&MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN: // 指点杆按下
                    // 当前位子保存为新的起始点
                    startPoint.set(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_MOVE: // 指点杆保持按下，并进行位移
                    timer.updateTimer();

                    dx = event.getX() - startPoint.x;
                    dy = event.getY() - startPoint.y;
                    matrix.postTranslate(dx, dy);
                    // 将当前坐标保存为新起点
                    startPoint.set(event.getX(),event.getY());
                    break;

                case MotionEvent.ACTION_UP: // 指点杆离开屏幕
                    break;

                case MotionEvent.ACTION_POINTER_UP: // 有手指离开屏幕，但还有手指压住屏幕，就会触发事件
                    break;

                case MotionEvent.ACTION_POINTER_DOWN: // 如果已经有手机压在屏幕上，又有手指压在屏幕上了，多点触摸的意思
                    break;
            }
            imageView.setImageMatrix(matrix);

            return true;
        }
    }
}
