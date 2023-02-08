package org.apache.maven.cantinappdesktop.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class Image {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 84;

    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public Image(byte[] image) {
        this.image = image;
    }

    public Image(File file) {
        this.image = ImageToByte(file);
    }


    private byte[] ImageToByte(File file) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                    bos.write(buf, 0, readNum);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        byte[] bytes = bos.toByteArray();

        return bytes;
    }

    private ImageIcon ScaleImage(Dimension medida) {
        return new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(medida.width,
                medida.height,
                java.awt.Image.SCALE_SMOOTH));
    }


    public ImageIcon getImageIcon() {
        try {
            BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(image));

            // calculando aspect ratio
            float imageAspect = (float) bimg.getWidth() / (float) bimg.getHeight();
            float canvasAspect = (float) WIDTH / (float) HEIGHT;

            int imgWidth = 150;
            int imgHeight = 84;
            if (imageAspect < canvasAspect) {
                // Se o aspect ratio da imagem for menor que o da tela então altera a largura
                float w = (float) HEIGHT * imageAspect;
                imgWidth = (int) w;
            } else {
                // senão altera a altura
                float h = (float) WIDTH / imageAspect;
                imgHeight = (int) h;
            }
            Dimension dimension = new Dimension(imgWidth, imgHeight);

            return ScaleImage(dimension);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}




