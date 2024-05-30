package outils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileUtils {

    public static void saveImageToFile(Image image, String filePath) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        File file = new File(filePath);
        ImageIO.write(bufferedImage, "png", file);
    }

    public static Image loadImageFromFile(String filePath) {
        File file = new File(filePath);
        return new Image(file.toURI().toString());
    }
}

