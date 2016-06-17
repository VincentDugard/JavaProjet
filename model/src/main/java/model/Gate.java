package model;

import java.awt.image.BufferedImage;

import contract.Permeability;

public class Gate extends Motionless{
	
	private final static int ID = 7;
	private boolean open;
	
	public Gate(int posX, int posY, BufferedImage[] sprite){
		super(posX, posY, Permeability.BLOCKING, sprite[0], ID);
		this.open = false;
	}
	
	public boolean isSolid(){
		return true;
	}
	
	public boolean isOpen(){
		return open;
	}
	
	public void action(){
	}

	@Override
	public boolean isPickable() {
		return false;
	}
}
