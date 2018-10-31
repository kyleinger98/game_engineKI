/**
 * 
 */
package game_engine2D;

import processing.core.PApplet;
import processing.core.PVector;


public class Physics2D extends GameComponent {
	int frameWait = 0;
	int frameDelay = 2;
	private PVector velocity = new PVector(0,0);
	public BoxCollider2D boxCollider2D;
	float gravity = 0.1f;
	float friction = 0.9f;
	float frictionOveride = 1.0f;
	float frictionNormal = 0.9f;
	boolean isGrounded = false;
	public float speed = 3f;
	public float maxSpeed = 5f;
	
	public Physics2D(GameObject g) {
		super(g);
	}

	@Override
	public void start() {
		this.boxCollider2D = new BoxCollider2D(this.gameObject);

	}


	@Override
	public void update() {
	
		frameWait++;
		
		if (frameWait > frameDelay) {
			frameWait = 0;
			checkCollisions();
		}
		
		this.transform.prev_position.x = this.transform.position.x;
		this.transform.prev_position.y = this.transform.position.y;
		gravityEffect();
		if (isGrounded ) {
			this.velocity.x *= friction;
		}
		if(PApplet.abs(velocity.x) >= maxSpeed) {
			if(velocity.x > 0)
				velocity.x = maxSpeed;
			else
				velocity.x = -maxSpeed;
		}
		if (isGrounded && PApplet.abs(velocity.x) <= 0.1f) {
			velocity.x = 0f;
		}
		this.transform.position.add(velocity);
	}
	
	private void checkCollisions() {
		if (PApplet.abs(this.transform.prev_position.x - this.transform.position.x) < 0.1f) {
			if (PApplet.abs(this.transform.prev_position.y - this.transform.position.y) < 0.1f) {
				return;
			}
		}
		for (int i = 0; i < GameManager.gameBoundingBoxes.size(); i++) {
			
			HitInfo hitInfo = new HitInfo();
			hitInfo.boundingBox = GameManager.gameBoundingBoxes.get(i);
			hitInfo = this.boxCollider2D.checkCollision(hitInfo);
			if (hitInfo.didHit) {
				switch (hitInfo.hitSide) {
				case TOP:
					this.velocity.y = 0f;
					this.transform.position.y = hitInfo.boundingBox.bottom + this.transform.boundingBox.bottom;
					
					break;
				case BOTTOM:	
				}
			}
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	public void jump(int force) {
		if(canJump) {
			velocity.y = -force;
			canJump = false;
			
		}
		
	}
	public void isGrounded() {
		canJump = true;
	}
	private void gravityEffect() {
		
		velocity.y += gravity;
		if (velocity.y >= 4f) {
			velocity.y = 4f;
		}
	}


}
