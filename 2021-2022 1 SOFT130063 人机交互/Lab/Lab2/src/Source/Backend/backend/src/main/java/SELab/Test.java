package SELab;

import SELab.utility.response.ImageString;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLSyntaxErrorException;

public class Test {

//    public static void main(String[] args) {
//        // 1 初始化用户身份信息（secretId, secretKey）。
//        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
//        String secretId = "AKIDe6HCuNYS6TYMuCEzUifQv97oDMFrCG9p";
//        String secretKey = "SeZDagW5MM5XhaCXbOuA8pFPItDG2Wjf";
//        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
//        Region region = new Region("ap-shanghai");
//        ClientConfig clientConfig = new ClientConfig(region);
//        // 这里建议设置使用 https 协议
//        // 从 5.6.54 版本开始，默认使用了 https
//        clientConfig.setHttpProtocol(HttpProtocol.https);
//        // 3 生成 cos 客户端。
//        COSClient cosClient = new COSClient(cred, clientConfig);
//
//
//
//        // 指定要上传的文件
//        File localFile = new File("img_0107.jpg");
//        // 指定文件将要存放的存储桶
//        String bucketName = "cos1-1301521501";
//        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
//        String key = "userPhotos/img_0107.jpg";
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//
//        cosClient.shutdown();
//        System.out.println("---------------------------------------------------------");
//
//        System.out.println(putObjectResult);
//    }
public static boolean generateImage(byte[] imgStr, String filename) {

    if (imgStr == null) {
        return false;
    }
//    BASE64Decoder decoder = new BASE64Decoder();
    try {
        // 解密
        System.out.println("==============================================");

        byte[] b = imgStr;
        System.out.println(b);

        // 处理数据
        for(int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OutputStream out = new FileOutputStream(filename);
        out.write(b);
        out.flush();
        out.close();
        return true;
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return false;
}

public static byte[] getImageStr(String filePath) {
            InputStream inputStream = null;
            byte[] data = null;
            try {
                    inputStream = new FileInputStream(filePath);
                    data = new byte[inputStream.available()];
                    inputStream.read(data);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            // 加密

//            BASE64Encoder encoder = new BASE64Encoder();
//            return encoder.encode(data);
            System.out.println("==============================================");
            System.out.println(data);
            return data;
        }

    static void ByteToFile(byte[] bytes)throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            File w2 = new File("00000000003.jpg");//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }

    public static byte[] fileToByte(String imgPath) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[0];
        File img = new File(imgPath);
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
            System.err.println(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals(""))
            return;
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(
                    new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in "
                    + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception{
        ImageString imageString = new ImageString();
        byte[] cs =getImageStr("004.jpg");
//        byte[] cs1 = fileToByte("004.jpg");
        String s = imageString.bytesToHexString(cs);
        byte[] a = imageString.hexStringToBytes(s);
    //    boolean k = generateImage(a, "0010.jpg");
        byte2image(a, "0010.jpg");
        System.out.println(s);

}



}
