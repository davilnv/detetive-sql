package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.utils.ErrorHandler;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Scene {
    private BufferedImage sceneImage;
    private final List<BufferedImage> subScenes;

    public Scene(String imagePath) {
        try {
            sceneImage = ImageIO.read(Objects.requireNonNull(Scene.class.getResource(imagePath)));
        } catch (IOException e) {
            ErrorHandler.logAndExit("Erro ao carregar imagem da cena: " + imagePath);
        }
        subScenes = new ArrayList<>();
    }

    public void cutScenes(int widthScene, int heightScene) {
        int horizontalNumberScenes = sceneImage.getWidth() / widthScene;
        int verticalNumberScenes = sceneImage.getHeight() / heightScene;

        for (int y = 0; y < verticalNumberScenes; y++) {
            for (int x = 0; x < horizontalNumberScenes; x++) {
                BufferedImage cutedScene = sceneImage.getSubimage(x * widthScene, y * heightScene, widthScene, heightScene);
                subScenes.add(cutedScene);
            }
        }
    }

}