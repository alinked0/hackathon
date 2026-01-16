package src.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    // get the images from their path and put them in a table
    // tile[0] = grass
    // tile[0] = wall
    // tile[0] = water
    // tile[0] = earth
    // tile[0] = tree
    // tile[0] = sand
    // using ImageIO.read()
    // and set the collision
    public final void getTileImage() {

        try {
            File folder_tiles;
            File[] files;

            folder_tiles = new File("res/tiles/");
            files = folder_tiles.listFiles();
            tile = new Tile[files.length];

            for (int i = 0; i<files.length; ++i){
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(files[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the map by putting the id of each tile in a mapTileNum[] to draw the map
    public final void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // draw each tile on the right position (col and row) of the screen from the
    // world around the player
    // using g2.drawImage()
    //
    public void draw(Graphics2D g2) {
        int x, y, pos_x, pos_y;
        for (x=0; x< gp.maxWorldCol; ++x){
            for (y=0; y< gp.maxWorldRow; ++y){
                pos_x = x*gp.tileSize;
                pos_y = y*gp.tileSize;

                g2.drawImage(
                    tile[mapTileNum[x][y]].image, 
                    pos_x, pos_y, 
                    gp.tileSize, gp.tileSize, 
                    null
                );
            }
        }
    }

}
