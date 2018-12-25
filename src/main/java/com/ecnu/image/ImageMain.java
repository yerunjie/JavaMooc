package com.ecnu.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by yerunjie on 2018-12-25
 *
 * @author yerunjie
 */
public class ImageMain {
    public static void main(String[] args) {

        BufferedImage bufferedImage;

        try {

            //读取图片
            bufferedImage = ImageIO.read(new File("pic.jpg"));

            //创建一个空白大小相同的RGB背景
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            //重新写图片
            ImageIO.write(newBufferedImage, "png", new File("1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
