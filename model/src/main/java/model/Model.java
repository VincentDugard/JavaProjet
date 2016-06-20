package model;

import java.util.ArrayList;

import contract.Direction;
import contract.IElement;
import contract.IModel;
import model.actions.FireballMove;
import model.actions.MonsterMove;
import model.actions.Movement;
import model.actions.PlayerMove;
import model.actions.SpriteSwitcher;
import model.element.Bonus;
import model.element.mobile.Hero;

/** The class Model.
 * 
 * @author Group 10
 */
public class Model implements IModel {
	
	private boolean isKeyPressed = false;
	
	/** the attribute stage.
	 * Actual loaded stage.
	 */
	private Stage stage;
	private static int score;
	private Bonus bPurse;

	/** Instantiates a new model */
	public Model() {
		ImageLoader.loadImage();
		score = 0;
		bPurse = new Bonus(650);
	}
	/** the method addToScore
	 * 
	 * @param bonus
	 * Add bonus points to the total score
	 */
	public static void addToScore(int bonus){
		score = score + bonus;
	}
	
	/**the method loadStage
	 * @param numStage
	 * 	load the stage number numstage.
	 */
	public ArrayList<IElement> loadStage(int numStage){
		stage = new Stage(numStage, bPurse);
		return stage.LoadStage();
	}
	
	
	/**the method playerDirection
	 * 
	 * Launch the playerDirection method in PlayerMove
	 */
	public void playerDirection(Direction direction){
		PlayerMove.playerDirection(direction);
	}

	/**the method playerSHot
	 * Allows to cast the fireball spell
	 */
	public void playerShot() {
		ArrayList<Hero> rLorannList = Stage.rLorannList;
		for (int i = 0; i < rLorannList.size(); i++) {
			Stage.fireball.setDirection(fireballDirection(Stage.rLorannList.get(0).getDirection()));
			Stage.fireball.setPosX(Stage.rLorannList.get(0).getPosX());
			Stage.fireball.setPosY(Stage.rLorannList.get(0).getPosY());
			Movement.getElementCoordinates(Stage.fireball, "attack", Stage.elements);
		}
	}

	
	/**
	 * the method fireballDirection
	 * @param playerDir
	 * @return
	 * Gives the new direction of the fireball
	 */
	public Direction fireballDirection(Direction playerDir){
		switch(playerDir){
		case BOTTOM_LEFT: return Direction.UPPER_RIGHT;
		case BOTTOM_RIGHT:return Direction.UPPER_LEFT;
		case DOWN: return Direction.UP;
		case LEFT: return Direction.RIGHT;
		case RIGHT:return Direction.LEFT;
		case UP: return Direction.DOWN;
		case UPPER_LEFT: return Direction.BOTTOM_RIGHT;
		case UPPER_RIGHT: return Direction.BOTTOM_RIGHT;
		}
		return null;
	}
	public void monsterMove() {
		MonsterMove.Move();
	}
	
	public void fireballMove(){
		FireballMove.move();
	}
	
	public void switchFireBallSprite(){
		SpriteSwitcher.fireBallLoader();
	}
	
	public void switchLorannSprite(){
		SpriteSwitcher.lorannloader();;
	}
	
	//getters
	public int getScore(){
		return score;
	}
	
	public int getMonsterNumber(){
		return Stage.monsterList.size();
	}
	
	public int getBonusNumber(){
		return Stage.purseList.size();
	}
	
	public IElement getFireball(){
		return Stage.fireball;
	}
	
	public boolean getKeyPressed() {
		return isKeyPressed;
	}
	
	//setters
	public void setKeyPressed(boolean isKeyPressed) {
		this.isKeyPressed = isKeyPressed;
	}

}