package logic;

import graphics.DrawingUtility;
import javafx.scene.canvas.GraphicsContext;
import main.IRenderableHolder;
import utilities.NoBulletSpawnerException;

public class Wisp extends RangedEnemy {

	public Wisp(float x, float y) {
		super(x, y, Math.PI, 3, 20);
		// TODO Auto-generated constructor stub
		this.setHp(3);
		this.givenExp = 50;
		this.bulletPattern = new SpreadPattern(this, 12, 360, 1, 4000, BulletPattern.DEFAULT_BURST_DELAY);
		this.bulletSpawner = new BulletSpawner(this.bulletPattern);
		setNewPoint();
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(Math.abs(this.angle) <= Math.PI / 2 ){
			gc.drawImage(IRenderableHolder.wispModel, x, y);
		} else {
			gc.drawImage(IRenderableHolder.wispModelLeft, x, y);
		}
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		if(!this.isDestroy()){
			if(this.walkTo(pointX, pointY)){
				setNewPoint();
			}
			try {
				this.spawnBullet(this.bulletPattern);
			} catch (NoBulletSpawnerException e) {
				// TODO Auto-generated catch block
			}
		}
	}

}
