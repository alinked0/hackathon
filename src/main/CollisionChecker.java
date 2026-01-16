package src.main;

import src.entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // check the collison when the direction is up/down/left/right
    // get the world coordonate of the player from top,down,left,right
    // get the col and row of the screen where the player is
    // check the collision between the player the th 2 blocks close to it relatively
    // to the direction
    // and always check if you are walking on ground or not
    public void checkTile(Entity entity) {
        Integer entityLeftWorldX, entityRightWorldX,
                entityTopWorldY, entityBottomWorldY,
                leftCol, rightCol,
                topRow, bottomRow,
                tileNum1, tileNum2;

        // entitys solid area boundaries
        entityLeftWorldX = entity.worldX + entity.solidArea.x;
        entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        entityTopWorldY = entity.worldY + entity.solidArea.y;
        entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // tile indices in the world
        leftCol = entityLeftWorldX / gp.tileSize;
        rightCol = entityRightWorldX / gp.tileSize;
        topRow = entityTopWorldY / gp.tileSize;
        bottomRow = entityBottomWorldY / gp.tileSize;

        tileNum1 = tileNum2 = null;
        switch (entity.direction) {
            case "up":
                topRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                break;
            case "down":
                bottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                break;
            case "left":
                leftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                break;
            case "right":
                rightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                break;
        }
        if (tileNum1!=null && tileNum2!=null && (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)) {
            entity.collisionOn = true;
        }
    }
}
