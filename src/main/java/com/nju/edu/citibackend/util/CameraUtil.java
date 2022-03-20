package com.nju.edu.citibackend.util;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 摄像头获取工具类
 *
 * @author Zyi
 */
public class CameraUtil {

	private static Webcam webcam = Webcam.getDefault();
	private static final String ROOT_PATH = "src/main/resources/";
	private static final String IMAGE_PATH = ROOT_PATH + "image/";

	/**
	 * 初始化摄像头获取的照片分辨率
	 *
	 * @param height 高度
	 * @param width  长度
	 */
	public static void init(int height, int width) {
		// 设置摄像头大小等
		webcam.setViewSize(new Dimension(width, height));
	}

	/**
	 * 打开摄像头，要求获得权限
	 */
	public static void openCamera() {
		webcam.open();
	}

	/**
	 * 写入图像文件
	 */
	public static void writeImage(String imageName) throws IOException {
		ImageIO.write(webcam.getImage(), "PNG", new File(IMAGE_PATH + imageName + ".png"));
	}
}
