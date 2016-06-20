package model.element.motionless;

import java.awt.image.BufferedImage;

import contract.Permeability;
import model.element.Bonus;

public class Purse extends Motionless{
	
	protected Bonus bonus;

	private final static int ID = 8;
	
	public Purse(int posX, int posY, BufferedImage sprite){
		super(posX, posY, Permeability.PENETRABLE, sprite, ID);
		
	}
}