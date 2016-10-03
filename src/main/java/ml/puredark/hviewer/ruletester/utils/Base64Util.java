package ml.puredark.hviewer.ruletester.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	
	
    public static String getImageStr(byte[] data) {

        // 对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();
        String img = encoder.encode(data).replaceAll("\\+", "%2B").replaceAll("\\r", "").replaceAll("\\n", "");
        return img;// 返回Base64编码过的字节数组字符串  
    }
	
    public static String getImageStr(ImageInputStream in) {
    	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        byte[] data = null;

        // 读取图片字节数组  
        try {
            data = new byte[(int)in.length()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getImageStr(data);
    }
	
    public static String getImageStr(InputStream in) {
    	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        byte[] data = null;

        // 读取图片字节数组  
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getImageStr(data);
    }
    
    public static String getImageStr(String imgFilePath) {
    	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        byte[] data = null;

        // 读取图片字节数组  
        try {
            return getImageStr(new FileInputStream(imgFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();
        String img = encoder.encode(data).replaceAll("\\+", "%2B").replaceAll("\\r", "").replaceAll("\\n", "");
        return img;// 返回Base64编码过的字节数组字符串  
    }

    public static boolean generateImage(String imgStr, String imgFilePath) {
    	// 对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) // 图像数据为空  
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码  
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据  
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片  
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //灰化图片
    public static void grayImage(String sFile, String toFile) {
        try {
            File file = new File(sFile);
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage grayImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    grayImage.setRGB(i, j, rgb);
                }
            }
            File newFile = new File(toFile);
            ImageIO.write(grayImage, "jpg", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
