package logic;

public class BulletSpawner extends Thread {
		
	Entity owner;
	
	public BulletSpawner(BulletPattern pattern){
		super(pattern);
		this.owner = pattern.getOwner();
	}
	
}
