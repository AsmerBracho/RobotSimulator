/*
* Public Class Robot
*
* CCT Computer Programming Assignment
*
* Author: Asmer Bracho
* Student Number: 2016328
* Date: 15/01/2017
*
* Programming Tools Used: 
* JAVA
* Processing
*/

import processing.core.PApplet; 

public class RobotSimulator extends PApplet { 
	// We need to create the Robots here in order for them to be available y the whole program. 
	Robot[] robots; 
	/*
	* We are going to create an array of type boolean with 128 index. that is because this array is going to make reference  
	* to ASCII which is a table with the keys of every keyboard where every character has a decimal number attached to and it goes 
	* from 0 to 127 which it give us 128 spaces or index in our array.   
	*/
	boolean[] keyboard = new boolean[128]; 
	 
	public static void main(String[] args) {
		PApplet.main("RobotSimulator");
	}

	//Settings 
	public void settings(){
		size(1000,500);
	} // End of settings 

	//Setup 
	public void setup() {
		//Here we are going to set our Robots
		robots = new Robot[3]; 
		robots[0] = new Robot(this, "Alice", /*height*/30f, /*width*/30f, /*speed*/3f, color(215,96,68), /*xPos*/50, /*yPos*/50);
		robots[1] = new Robot(this, "Bob", /*height*/40f, /*width*/20f, /*speed*/2f, color(167,234,112), /*xPos*/200, /*yPos*/200);
		
		// Charlie will be created with the third constructor so, it will get a random position every time the programme runs. 
		robots[2] = new Robot(this,"Charlie",/*height*/20f, /*width*/20f, /*speed*/2f);


	} // End of Setup 

	public void draw() {
		background(255); // this will erase any trace left by our robots
		noStroke(); // no stroke in any shape on my programme
		//--------------------------------------------------------------------
		//-----------------------HEADER WITH COORDENADES----------------------
		// Header
		fill(0); // a black header 
		rect(0,0,width,50);
		fill(255); // text colour (white). 
		textSize(16);// For coordinates title
		text("Coordinates:",5,30);
		// Coordinates
		textSize(12); // Since this is on top it will apply for every robot.
		//Alice
		text("Alice: ( " + round(robots[0].xPos) + " , " + round(robots[0].yPos) + " )",130,30);
		stroke(255);
		line(245,0,245,50);
		//Bob 
		text("Bob: ( " + round(robots[1].xPos) + " , " + round(robots[1].yPos) + " )",255,30);
		line(370,0,370,50);
		//Charlie 
		text("Charlie: ( " + round(robots[2].xPos) + " , " + round(robots[2].yPos) + " )",385,30);
		text(" Vertex A: ( " + round(robots[2].xVertex1) + " , " + round(robots[2].yVertex1) + " )",515,15);
		text(" Vertex B: ( " + round(robots[2].xVertex2) + " , " + round(robots[2].yVertex2) + " )",515,30);
		text(" Vertex C: ( " + round(robots[2].xVertex3) + " , " + round(robots[2].yVertex3) + " )",515,45);
		//line(690,0,690,50);
		textSize(9);
		fill(255,0,0);
		text("x          y", 180,15);
		text("x          y", 305,15);
		text("x          y", 460,15);
		
		// calculate the slope of the line AB
		robots[2].calculationsOfLine(robots[2].xVertex1, robots[2].yVertex1,robots[2].xVertex2,robots[2].yVertex2);
		// display it
		fill(255);
		textSize(12); 
		text("Equation of AB: Y - " + round(robots[2].yVertex1) + " = " + round(robots[2].m) + "X - " + round(robots[2].xVertex1),665,15);
		// Length of line AB 
		text("|AB|= " + robots[2].length,890,15);

		// calculate the slope of line BC
		robots[2].calculationsOfLine(robots[2].xVertex2, robots[2].yVertex2,robots[2].xVertex3,robots[2].yVertex3);
		// display it
		text("Equation of BC: Y - " + round(robots[2].yVertex2) + " = " + round(robots[2].m) + "X - " + round(robots[2].xVertex2),665,30);
		// Length of line BC 
		text("|BC|= " + robots[2].length,890,30);

		//calculate the slope of line AC
		robots[2].calculationsOfLine(robots[2].xVertex1, robots[2].yVertex1,robots[2].xVertex3,robots[2].yVertex3);
		// display it
		if (robots[2].x2 - robots[2].x1 == 0) {
			text("Equation of AC: Y - " + round(robots[2].yVertex1) + " = " + robots[2].ind + "X - " + round(robots[2].xVertex1),665,45); // the slope is undefined which means the line is parallel to the y axis  
		}	else {
			text("Equation of AC: Y - " + round(robots[2].yVertex1) + " = " + round(robots[2].m) + "X - " + round(robots[2].xVertex1),665,45);
		}
		// Length of line AC 
		text("|AC|= " + robots[2].length,890,45);

		// Area of triangle Charlie
		//robots[2].areaOfTriangle();
		//text("Area = " + robots[2].area,900,30);
		//--------------------------------------------------------------------

		//----------------------ALICE-----------------------------------------
		robots[0].appearance(); // give an appearance to Our Robot
		robots[0].startMovingForward(); // Start Moving Forward (In accordance to the position of the robot).
		/*
		* Our Robot Alice has already a position and orientation, however what if we decide to change that?. We are going to 
		* define a behaviour in case this values would be changed. 
		*/
		//------------------------------------------------------------------
		// CHANGE VALUES
		if (robots[0].var == 0) { // we only going to change the values once
			robots[0].setOrientation(90); // Facing left	
			robots[0].setPosition(400,300); // Somewhere
			//--------------------------------------------------------
			if (robots[0].xPos < 80) { // whit this I just want to push the xPos to be at least 80
				robots[0].xPos = 80; 
			}
			robots[0].var = 1; // It never get into this if statement again 
		} // End of changes values 
		//-------------------------------------------------------------------
		// First of all whatever happens whit Alice when the program starts, It can only happens once.
		if (robots[0].yPos != 50 && robots[0].once == true) { // once
			robots[0].stop();
			robots[0].turnLeft();
			if (robots[0].vel <= 0) { // if it finished turning
				robots[0].ang = 270;
				robots[0].assignSense();
				robots[0].moveForward();
				robots[0].startTurning();
				robots[0].once = false;  // so the "once" statement will never repeat again
			}
		} // End on once
		if (robots[0].yPos <= 0 + ((robots[1].width/2) + (robots[1].height/2)) + /*Header*/50 && robots[0].once2 == true) {
			robots[0].stop();
			robots[0].turnRight(360);
			if (robots[0].vel <= 0) {
				robots[0].drive = 1;
				robots[0].moveForward();
				robots[0].startTurning();
				robots[0].ang = 0;
				robots[0].once2 = false; 
			}		
		} 
		/*
		* The next If conditional will be the last one to be checked and what this is doing in set the variables to their 
		* initial value so the structure coming below will repeat for the entire duration of the programme.
		*/
		if (robots[0].xPos >= width/2 && robots[0].var7 == 1) {
			robots[0].var1 = 0; 
			robots[0].var2 = 0;
			robots[0].var3 = 0;
			robots[0].var4 = 0;
			robots[0].var5 = 0;
			robots[0].var6 = 0;
			robots[0].var7 = 0;
			robots[0].ang = 0;
			robots[0].startTurning();
		} // End of If conditional

		// stop in the corner 
		if (robots[0].xPos >= width - ((robots[0].width/2) + (robots[0].height/2)) && robots[0].var1 == 0) {
			robots[0].stop();
			robots[0].turnRight(450); // make a circle and turn right
			
			// First corner
			if (robots[0].vel <= 0) { // if it finished turning
				robots[0].assignSense(); // chance the sense 
				robots[0].moveForward(); // move forward
				robots[0].var1 = 1; // change var1 so First corner will not repeat
			} // End of First Corner
		} // End of Stop in Corner 

		//stop second corner
		if (robots[0].yPos >= height - ((robots[0].width/2) + (robots[0].height/2)) && robots[0].var3 == 0) {
			robots[0].stop(); // stop 
	
			// Second Corner
			if (robots[0].var2 == 0) { // we need this happens only once
				robots[0].startTurning(); // give a turning velocity again 
				robots[0].var2 = 1; // change var2 so Second corner will not repeat

			} // End of second corner 

			robots[0].turnRight(540); // make a circle and turn right
			if (robots[0].vel <= 0) { // if it finished turning
				robots[0].assignSense(); // chance the sense 
				robots[0].moveForward(); // move forward
				robots[0].var3 = 1;
			} 
		} // End of stop in second Corner
		
		// Stop in third corner
		if (robots[0].xPos <= 0 + ((robots[0].width/2) + (robots[0].height/2)) && robots[0].var4 == 0) {
			robots[0].stop();
			if (robots[0].var5 == 0) {
				robots[0].startTurning();
				robots[0].var5 = 1;
			}
			robots[0].turnRight(630);
			if (robots[0].vel <= 0) { // if it finished turning
				robots[0].assignSense(); // chance the sense 
				robots[0].moveForward(); // move forward
				robots[0].var4 = 1;
				robots[0].var8 = 0;
			} 
		} // End on stop third corner 

		// Stop in fourth corner
		if (robots[0].yPos <= 0 + ((robots[1].width/2) + (robots[1].height/2)) + /*Header*/50 && robots[0].var8 == 0) {
			robots[0].stop();
			if (robots[0].var6 == 0) {
				robots[0].startTurning();
				robots[0].var6 = 1;
			}
			robots[0].turnRight(720);
			if (robots[0].vel <= 0) { // if it finished turning
				robots[0].assignSense(); // chance the sense
				robots[0].ang = 0;
				robots[0].moveForward(); // move forward
				robots[0].var7 = 1;
				robots[0].var8 = 1;
				robots[0].drive = 1;
			} 
		} // End of stop fourth corner
		//---------------------------------------------------------------------
		//----------------------BOB------------------------------------------  
		/* 
		* In Order to try the method setOrientation and setPosition we are going to change
		* the orientation and position of our robot Bob
		* However, We only want this to happens once in our program, so:
		*/
		if (robots[1].var == 0) {
		robots[1].setOrientation(180); // Facing left	
		robots[1].setPosition(200,300); // Somewhere
		robots[1].var = 1; // It never get into this if statement again 
		}
		//---------------------------------------------------
		robots[1].appearance(); // Define an appearance and orientation  
		//---------------------------------------------------
		robots[1].startMovingForward();

		// If it hit a wall, 
		if (robots[1].yPos >= height - ((robots[1].width/2) + (robots[1].height/2)) ||
			robots[1].xPos >= width - ((robots[1].width/2) + (robots[1].height/2)) ||
			robots[1].xPos <= 0 + ((robots[1].width/2) + (robots[1].height/2)) || 
			robots[1].yPos <= 0 + ((robots[1].width/2) + (robots[1].height/2)) + /*Header*/50) {
			// then =>
			robots[1].stop(); // stop
			robots[1].lastY = robots[1].yPos; // Defining the value of Y when bob stops
			robots[1].lastX = robots[1].xPos; // Defining the value of X when bob stops
			robots[1].moveBackward(); // move backward
			robots[1].randomNumber = round(random(1,4)); // assign a random number so it will turn randomly
			// The statement is going next is just to avoid that a random number repeats so bob will always turn in a different direction 
			if (robots[1].randomNumber == robots[1].tempRandomNumber) {
				robots[1].randomNumber = robots[1].tempRandomNumber + 1; 
				if (robots[1].randomNumber == 5) {
					robots[1].randomNumber = 2; 
				}
			}
			robots[1].tempRandomNumber = robots[1].randomNumber;
			//--------------------------------------------------
			robots[1].startTurning(); // start the turning velocity every time the robot hit the wall, so it will be ready to turn
		} // End of hit the wall 
		
		// If it is moving backward
		if (robots[1].moveBackward == true) {
			if (robots[1].yPos <= robots[1].lastY - robots[1].numOfSteeps && robots[1].xPos == robots[1].lastX ||
				robots[1].xPos <= robots[1].lastX - robots[1].numOfSteeps && robots[1].yPos == robots[1].lastY ||
				robots[1].yPos >= robots[1].lastY + robots[1].numOfSteeps && robots[1].xPos == robots[1].lastX ||
				robots[1].xPos >= robots[1].lastX + robots[1].numOfSteeps && robots[1].yPos == robots[1].lastY) {
				robots[1].stop();
				robots[1].turnRandonly();
				if (robots[1].vel <= 0) {
					robots[1].moveForward();							
				}
			}
		} // End of moving Backward
		//---------------------------------------------------------------------
		//------------------------CHARLIE-------------------------------------	
		robots[2].appearance(); 
		move();
	} // End of draw 

	public void move() {
		if (keyboard['s'] == true) { // s key is being pressed 
			robots[2].ang = 90; // robot facing Down 
			robots[2].drive = 2; // sense of driving up to down 
			robots[2].startMovingForward(); // move forward 
		}
		if (keyboard['w'] == true) { // w key is being pressed 
			robots[2].ang = 270; // robot facing up 
			robots[2].drive = 4; // sense of driving down to up  
			robots[2].startMovingForward(); // move forward
		}
		if (keyboard['a'] == true) { // a key is being pressed 
			robots[2].ang = 180; // robot facing left 
			robots[2].drive = 3; // sense of driving right to left 
			robots[2].startMovingForward(); 
		}
		if (keyboard['d'] == true) { // d key is being pressed 
			robots[2].ang = 0; // robot facing right 
			robots[2].drive = 1; // sense of driving left to right  
			robots[2].startMovingForward(); // move forward 
		}
		//--------------------------------------------------------------------------
		// Here we are defining statements to make the robot stop when it hit a wall 
		if (robots[2].xFront >= width - robots[2].rad) { // if It hit the right wall
			robots[2].xPos = width - (robots[2].width/2) - (robots[2].rad); // assign a static value to xPos so it stops 
		}
		if (robots[2].xFront <= 0 + robots[2].rad) { // if it hit the left wall 
			robots[2].xPos = 0 + (robots[2].width/2) + (robots[2].rad); // stop
		}
		if (robots[2].yFront <= 0 + robots[2].rad + /*Header*/50) { // if it hit the top wall 
			robots[2].yPos = 0 + (robots[2].width/2) + (robots[2].rad) + /*Header*/50; // assign a static value to yPos so it stops 
		}
		if (robots[2].yFront >= height - robots[2].rad) { // it hit the bottom wall 
			robots[2].yPos = height - (robots[2].width/2) - (robots[2].rad); // stop 
		}
	} // End of move

	public void keyPressed() {
		keyboard[key] = true;
	} // End of keyPressed

	public void keyReleased() {
		keyboard[key] = false;
	} // End of keyReleased
} // End of public Class RobotSimulator extend PApplet 



