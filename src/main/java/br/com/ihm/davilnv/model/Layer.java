package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.utils.ErrorHandler;
import lombok.Getter;
import lombok.Setter;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

@Getter
@Setter
public class Layer {

    private final String key;
    public int[][] map;
    public BufferedImage layerImage;
    private BufferedImage tileSet;
    private final int mapWidth;
    private final int mapHeight;
    private final int tileWidth;
    private final int tileHeight;

    public Layer(String key, int mapWidth, int mapHeight, int tileWidth, int tileHeight, String img, String file) {
        this.key = key;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        map = new int[mapHeight][mapWidth];
        map = loadMatrix(map, file);
        try {
            tileSet = ImageIO.read(Objects.requireNonNull(Layer.class.getResourceAsStream(img)));
        } catch (IOException e) {
            ErrorHandler.logAndExit("Erro ao tileSet.\nEncerrando aplicação");
            System.exit(0);
        }
    }

    public int[][] loadMatrix(int[][] matx, String file) {
        ArrayList<String> matrixLinesLayer = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Layer.class.getResourceAsStream(file))));
        String line;
        try {

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                matrixLinesLayer.add(line);
            }
            int j = 0;
            for (int i = 0; i < matrixLinesLayer.size(); i++) {
                StringTokenizer token = new StringTokenizer(matrixLinesLayer.get(i), ",");

                while (token.hasMoreElements()) {
                    matx[i][j] = Integer.parseInt(token.nextToken());
                    j++;
                }
                j = 0;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            ErrorHandler.logAndExit("Não carregou o arquivo do Mapa");
            System.exit(0);
        } catch (IOException ioException) {
            ErrorHandler.logAndExit("Erro na leitura do mapa");
            System.exit(0);
        }
        return matx;
    }

    public void buildMap() {

        int lar = tileWidth * mapWidth;
        int alt = tileHeight * mapHeight;

        layerImage = new BufferedImage(lar, alt, BufferedImage.TYPE_4BYTE_ABGR);
        layerImage.createGraphics();

        int tile;
        int tileRow;
        int tileCol;
        int columnsTileSet = tileSet.getWidth() / tileWidth;
//		System.out.println(columnsTileSet);

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                tile = (map[i][j] != 0) ? (map[i][j] - 1) : 16;
                tileRow = (tile / (columnsTileSet));
                tileCol = (tile % (columnsTileSet));
                layerImage.getGraphics().drawImage(tileSet, (j * tileHeight), (i * tileWidth), (j * tileHeight) + tileHeight,
                        (i * tileWidth) + tileWidth, (tileCol * tileHeight), (tileRow * tileWidth),
                        (tileCol * tileHeight) + tileHeight, (tileRow * tileWidth) + tileWidth, null);
            }
        }
    }

    /**
     * @return lista de Rectangle para calculo da colisão
     */
    public List<Rectangle> buildColision() {
        List<Rectangle> tmp = new ArrayList<>();
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (map[i][j] != 0) {
                    tmp.add(new Rectangle((j * tileHeight), (i * tileWidth), tileWidth, tileHeight));
                }
            }
        }
        return tmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Layer tmp) {
            return tmp.getKey().equals(this.getKey());
        }
        return false;
    }

}