package simple_platformer;


import gameEngineKI.*;
import processing.core.PApplet;


public class Launcher extends BaseLauncher{

	public Launcher(PApplet p) {
		super(p);
	}

	public void StartGame(){
		super.StartGame();
        Player player = new Player(parent, parent.width/2,parent.height/2,60, 60);
        player.start();
        this.gameManager.addObject(player);
        this.gameManager.addPlayerGameObjects(player);
        int platforms = 8;
        Tile platforms;
        for(int i = 0; i < platforms; i++){
            Tile platform = new Tile(parent, 50 + i * 55, parent.height-50,50, 20);
            platform.start();
            this.gameManager.addObject(platform);
            this.gameManager.addGameBoundingBoxes(platform);
        }
        
        for(int i =0; i < platforms; i++) {
        	platform = new Tile(parent, parent.random(parent.width),parent.random(parent.hight),50, 20);
        	platform.start();
        	this.gameManager.addObject(platform);
        	this.gameManager.addGameBoundingBoxes(platform);
        }
        
        for(int i = 0; i < platforms; i++) {
        	platform = new Tile(parent, 26+ i * 50,40 + 15 * i,50, 20);
        	platform.start();
        	this.gameManager.addObject(platform);
        	this.gameManager.addGameBoundingBoxes(platform);
        }
        
        platform = new Tile(parent, parent.width/2, parent.height-100,50, 50);
        platform.start();
        this.gameManager.addObject(platform);
        this.gameManager.addGameBoudingBoxes(platform);
        
        platform = new Tile(parent, (parent.width*0.75f), (parent.height))
    }
	  public void UpdateAll(){
	        super.UpdateAll();
	    }

}
