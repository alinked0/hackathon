package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int leftInJump;

    BufferedImage image;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = gp.tileSize - (solidArea.x * 2); // 32
        solidArea.height = gp.tileSize - solidArea.y; // 32

        leftInJump = 0;

        image = null;

        setDefaultValues();
        getPlayerImage();
    }

    // get the images of the player and put it in a variable
    // example up from boy_up_0.png
    // using ImageIO.read()
    public final void getPlayerImage() {
        try {
            File image_file = new File("res/player/p0/boy_up_0.png");
            this.image = ImageIO.read(image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // the default value of the speed, the location and the direction the player is
    // facing
    public final void setDefaultValues() {
        worldX = gp.tileSize * 7;
        worldY = gp.tileSize * 6;
        speed = 4;
        direction = "right";
    }

    // if a key is pressed change the direction, check the collision if he can move,
    // if he can add or substract the speed from his coordonates and select what
    // image to use to maka an animation
    //add the jump and the condition for the jump
    public void update() {
        //TO DO
    }

    public void draw(Graphics2D g2) {

        switch (direction) {
            case "jump" -> {
            }

            case "left" -> {
                if (spriteNum == 0)
                    image = left1;
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
            }
            case "right" -> {
                if (spriteNum == 0)
                    image = right1;
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
