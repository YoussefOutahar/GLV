package Utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageProcessing {
    public static byte[] convertImgtoBytes(BufferedImage image) {
        if (image == null) return null;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                newImage.setRGB(x, y, image.getRGB(x, y));
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(newImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static BufferedImage convertToBufferedImage(byte[] buffer) {
        if (buffer == null)
            return null;
        InputStream is = new ByteArrayInputStream(buffer);
        BufferedImage newBi = null;
        try {
            newBi = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rgbImage = new BufferedImage(newBi.getWidth(), newBi.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp op = new ColorConvertOp(null);
        op.filter(newBi, rgbImage);
        return newBi;
    }
}
