package ge.tbc.testautomation.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComparison {
    // Compares two images pixel by pixel.
    public static boolean compareImages(File file1, File file2) throws IOException {
        BufferedImage img1 = ImageIO.read(file1);
        BufferedImage img2 = ImageIO.read(file2);

        // Check dimensions first
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            System.out.println("Images have different dimensions.");
            return false;
        }

        int width = img1.getWidth();
        int height = img1.getHeight();

        // Compare pixel by pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
//                    System.out.println("Difference found at (" + x + ", " + y + ")");
                    return false;
                }
            }
        }
        return true;
    }
}