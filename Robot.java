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

/*
* while you are going through the content of this code you will find certain logic which it refer to a different mathematics implementations 
* some of then were got by the research of methods a equations. 
* at the end of this document there is a bibliography which it might help to understand better some of the calculation that will be done in this code.  
* the bibliography contain as well certain links which refer only to the mathematics used in the maths Report which accompany this file.  
*
*/

import processing.core.PApplet;

public class Robot {
	// Here we have our Instances Variables
	int color; // It is an int because we will use colour as RBG
	String name; // Name of our Robots
	float height; 
	float width;
	float speed;
	float tempSpeed = 0;
	float xPos; // Our robot need to have  
	float yPos; // a position in the world
	PApplet parent; // Variable which allow us to access the processing library 
	int drive; 
	int numOfSteeps = 50; // Steeps when moving backwards
	/*
	* Next, is an important variable which define the position of our robot. when this ang changes
	* the position of the robot will change by the angle given 
	*/
	int ang = 0;
	int randomNumber = 0; // Used to turn Bob Randomly.
	int tempRandomNumber = randomNumber;  
	float vel = 2; // turning speed. 
	float tempVel = vel;
	//------------------------------------
	float xVertex1 = 0; //
	float xVertex2 = 0; // Coordinates of
	float xVertex3 = 0; // vertex of our
	float yVertex1 = 0; // robots 
	float yVertex2 = 0; // which is a Triangle
	float yVertex3 = 0; //

	float xFront = 0.0f; // Front of our Robot 
	float yFront = 0.0f;
	float rad = 6; // radius of the front circle of each robot  
	//------------------------------------
	float angle = 0;
	boolean moveBackward = false; 
	float lastY = 0; // Variable use for calculation with Bob 
	float lastX = 0; // Variable use for calculation with Bob
	//----------------------------------
	boolean once = true;
	boolean once2 = true;
	int var = 0; 
	int var1 = 0; // This variables will be used in the draw section in order to 
	int var2 = 0; // help us to skip if statements 
	int var3 = 0; // that we do not want to be repeated when the loop created by "draw()" 
	int var4 = 0; // keeps running once and again 
	int var5 = 0;
	int var6 = 0;
	int var7 = 0;
	int var8 = 1;
	//-----------------------------------------------------------------------
	//----------------Variables for maths calculation-------------------------
	float x1; 
	float y1; // Use to do maths calculation such as:
	float x2; // line equations, length of line etc.
	float y2; 
	float m; // slope
	String ind = "||"; // undefined slope (parallel to the y axis)
	float length; // length of the line
	float area; // area of triangle 
	float xA;
	float xB;	//
	float xC; // This variable will be used to	
	float yA; // calculate the area of the
	float yB; // triangle Charlie 
	float yC;

	//------------------------------------------------------------------------- 
	// First Constructor 
	public Robot(PApplet p) { // This constructor does not take any values (with the exception of the parent of type PApplet which allow us to access the processing library)
		parent = p; // Access to the PApplet and processing properties
		color = parent.color(255,0,0); // Every Robot is Red 
		height = 1.5f; // with a given height
		width = 1f;  //  a given width 
		speed = 1.5f; // and a given speed
		name = "MyRobot";
		xPos = 30; // fixed position in x 
		yPos = 30; // fixed position in y
	} // End of first Constructor

	// Second Constructor 
	public Robot(PApplet p, String name, float height, float width, float speed, int color, float xPos, float yPos) { // It Takes the Respectively Values
		parent = p; // Access to the PApplet and processing properties
		drive = 1;
		this.name = name; 
		this.height = height; 
		this.width = width; 
		this.speed = speed; 
		this.color = color;
		this.xPos = xPos; // xPosition of the centre of the triangle 
		this.yPos = yPos;	// yPosition of the centre of the triangle 
		tempSpeed = speed; // save the speed of or robot in a temporal variable.	
	} // End of Second Constructor 

	//---------------------------------------------------------------------------------------------
	/*
	* The following constructor generate a robot with a random position and orientation.  
	* This will be used for mathematics assignment rather than a practical way of creating our robot 
	*/
	// Third Constructor 
	public Robot(PApplet p, String name, float height, float width, float speed) {
		parent = p; // Access to the PApplet and processing properties
		this.name = name;
		this.height = height; 
		this.width = width; 
		this.speed = speed;
		color = parent.color(215,206,68); // Mustard  
		xPos = parent.round(parent.random(70, (parent.width - 70))); // random xPos
		yPos = parent.round(parent.random(70, (parent.height - 70))); // random yPos 
		tempSpeed = speed; // save the speed of or robot in a temporal variable.
		

	} // End of Third Constructor 
	//--------------------------------------------------------
	/*
	* METHODS 
	*
	* Our programme is going to try to implement as many methods as possible with the purpose
	* of having a better organized programme which allow us to make changes such as positions 
	* sizes, directions in an easy and fast way
	*/

	//---------------------------------------------------------
	
	/*
	* Appearance
	*
	* Every Robot has an Appearance; in our case they are triangles so this method is going to build each robot 
	* starting from a given height and width. 
	*/
 	public void appearance(){
 		/*
 		* This method is basically the heart of our programme since every appearance and rotation of our robot is 
 		* given by the calculation done in here 
 		*/

 		/*
		* Defining the position of each of the vertex of our robot both X and Y
 		* Notice that we are calculating the vertexes from a xPos and yPos which is the centre of our triangle
 		* in this way we guaranty our triangle is going to be a well organized shape, which will change in sizes and dimensions
 		* given a specific width and height. 
 		*/
 		xVertex1 = (xPos - (width/2)); 
		xVertex2 = (xPos + (width/2)); 
		xVertex3 = (xPos - (width/2));
		yVertex1 = (yPos - (height/2)); 
		yVertex2 = yPos; 
		yVertex3 = (yPos + (height/2));
		// Defining the position of the front of our robot
		xFront = xPos + (width/2); // coordinates in x
		yFront = yPos; // coordinates in y 
  		 
 		float sino = parent.sin(parent.radians(ang)); // this give us the value of sine in specific angle
 		float coseno = parent.cos(parent.radians(ang)); // cosine of the angle 
		
		/*
		* Notice that we are implementing sine and cosine, the reason is because a normal translation every 90 degrees, such as 90, 180, 270, which is can be done by applying 
		* algebra geometry do not give us a way to know the rotation in the points between. so that means our triangle will not be able to be seen rotating, that is why by the law of sine and cosine 
		* to rotate a point given an angle and the initial point is much better and suitable for our programme 
		*/
 		//----------------1------------------ first Point 
 		// First of all we are going to translate our point to the centre. same rule we would have to apply for a single translation of 90 degrees over the origin  
 		xVertex1 = xVertex1 - xPos; // translate x
		yVertex1 = yVertex1 - yPos; // translate y 
		// rotate the point 
		float xVertNew1 = (xVertex1*coseno) - (yVertex1*sino); // sine and cosine were calculate above by a given angle 
		float yVertNew1 = (xVertex1*sino) + (yVertex1*coseno);
		// Finally we translate point (already rotated) from centre to destination 
		xVertex1 = xVertNew1 + xPos; 
		yVertex1 = yVertNew1 + yPos; 
		//----------------2------------------ Second Point 
		// translate to the centre
 		xVertex2 = xVertex2 - xPos; 
		yVertex2 = yVertex2 - yPos;
		// rotate the point 
		float xVertNew2 = (xVertex2*coseno) - (yVertex2*sino);
		float yVertNew2 = (xVertex2*sino) + (yVertex2*coseno);
		// translate point from centre to destination 
		xVertex2 = xVertNew2 + xPos; 
		yVertex2 = yVertNew2 + yPos; 
		//---------------3------------------- Third Point
		// translate to the centre
 		xVertex3 = xVertex3 - xPos; 
		yVertex3 = yVertex3 - yPos;
		// rotate the point 
		float xVertNew3 = (xVertex3*coseno) - (yVertex3*sino);
		float yVertNew3 = (xVertex3*sino) + (yVertex3*coseno);
		// translate point from centre to destination 
		xVertex3 = xVertNew3 + xPos; 
		yVertex3 = yVertNew3 + yPos;
		//---------------Front------------------- Front of Robot 
		// translate to the centre
 		xFront = xFront - xPos; 
		yFront = yFront - yPos;
		// rotate the point 
		float xFrontNew = (xFront*coseno) - (yFront*sino);
		float yFrontNew = (xFront*sino) + (yFront*coseno);
		// translate point from centre to destination 
		xFront = xFrontNew + xPos; 
		yFront = yFrontNew + yPos;
		
		// Following we are going to draw our triangle
		parent.fill(color);
		parent.triangle(xVertex1, yVertex1, xVertex2, yVertex2, xVertex3, yVertex3);
		// Drawing front of triangle which is a circle 
		parent.fill(0);
		parent.ellipseMode(parent.CENTER);
		parent.ellipse(xFront, yFront, rad, rad);	
 	} // End of appearance
 	//---------------------------------------------------------
	// --------------START MOVING FORWARD---------------------
	// Start Moving Forward with a given speed 
	public void startMovingForward(){		
		if (drive == 1) { // left to right
			xPos += speed; // move Forward through the x axis from left to right
		} // End of left to right
		else if (drive == 2) { // up to down
			yPos += speed; // move Forward through the x axis from up to down	
		} // End of up to down
		else if (drive == 3) { // move Forward through the x axis from right to left	
			xPos -= speed;
		} // End of right to left 
		else if (drive == 4) { // move Forward through the x axis from down to up
			yPos -= speed;
		} // End of down to up
	} //End of startMoveForward
	//---------------------------------------------------------
	// --------------MOVE FORWARD------------------------------	
	public void moveForward() {	
		moveBackward = false; // avoid Bob to keep moving backward. see RobotSimulator line 277 
		speed = tempSpeed; // start speed if it has been change
		
	} // End of moveForward
	//--------------------------------------------------------
	// --------------MOVE BACKWARD----------------------------
	// Move Backward with a given speed 
	public void moveBackward(){
		moveBackward = true; // allow bob move backward 
		speed = tempSpeed; // save current speed
		speed = -(speed); // change the direction of movement 
	}	//End of moveBackward
	//--------------------------------------------------------
	// --------------STOP-------------------------------------
	public void stop(){
		speed = 0; // speed is equal to 0, what means the robot is not moving. 
	} // End of Stop 
	//--------------------------------------------------------
	// --------------TURN RIGHT-------------------------------
	public void turnRight(){ // 90 degrees clockwise
	angle = 90; // angle where our robot is going to stop 
	appearance(); // apply appearance method. see explanation line 160  
	ang += vel;
		if (ang >= angle) { //stop rotating 
			vel = 0;
		}
	} // End of turn Right

	public void turnRight(float angle) { // this method take a value
	this.angle = angle; 
	appearance();
	ang += vel;
		if (ang >= angle) { 
			vel = 0; // stop rotating 
		}
	} // End of turn Right (with a given value)
	// --------------------------------------------------------
	// --------------TURN LEFT---------------------------------
	public void turnLeft() { // 90 degrees anticlockwise
		angle = 90; 
		appearance();
		ang -= vel; // it will turn anticlockwise 
		if (ang <= -(angle)) {
			vel = 0; // stop rotating 
		}
	} // End of turn left

	public void turnLeft(float angle) { // it takes an angle
		this.angle = angle; 
		appearance();
		ang -= vel;
		if (ang <= -(angle)) {
			vel = 0;
		}
	} // End of turn left with a given angle
	// --------------------------------------------------------
	// ---------------TURN RANDONLY----------------------------
	public void turnRandonly() {
		/* 
		* this method will be used for Bob and what it does is evaluate a random number assigns in our draw method
		* in RobotSimulator line 263 
		*/
		if (randomNumber == 1) { // turn 90 degrees 
			turnRight();
			if (vel == 0) {
				drive = 2; // Drive Up to Down
				ang = 90; 			
			}
		} // end of randomNumber 1
		if (randomNumber == 2) { // Position of robot facing up
			turnLeft();
			if (vel == 0) {
				drive = 4;	// drive Down to Up (robot perspective is driving forward)

			} 
		} // end of randomNumber 2
		if (randomNumber == 3) { // Robot facing left (from our perspective)
			turnRight(180);
			if (vel == 0) {
				drive = 3;	// drive Right to left
				ang = 180; 		
			}
		} // end of randomNumber 3
		if (randomNumber == 4) {
			turnRight(360);
			if (vel == 0) {
				drive = 1;	// drive left to Right 
				ang = 0;		
			}
		} // end of randomNumber 4
	} // End of turnRandonly
	// -------------------------------------------------------- 
	// --------------START TURNING-----------------------------
	public void startTurning() { // This Method reassign the turning speed, so the robot can turn again
		vel = tempVel;
	} // End of starTurning
	// --------------------------------------------------------
	// --------------SET ORIENTATION---------------------------
	public void setOrientation(int ang){
		this.ang = ang; // take the value of the angle 
		/*
		* Even though this method allow us to change the orientation of our robot we only want to have four different orientation 
		* so we will create a conditional which in the event that the user set a position different than the four allowed, the programme will
		* automatic push the position to one of the allowed.   
		*/
		if (ang >= 70 && ang < 150 ) { // push to specifics numbers 
			this.ang = 90;
			drive = 2; 
		} else if (ang >= 150 && ang < 220) {
			this.ang = 180;
			drive = 3;
		} else if (ang >= 220 && ang < 310) {
			this.ang = 270;
			drive = 4;
		} else if (ang < 70 && ang >=310) {
			this.ang = 0;
			drive = 1; 
		} 
	} // End of setOrientatition
	// --------------------------------------------------------
	// --------------SET POSITION---------------------------
	public void setPosition(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos; 
	} // End of setPosition 
	// --------------------------------------------------------
	// ---------------------ASSINGN SENSE----------------------
	public void assignSense() {
		if (yFront < yPos) { // If it is facing up 
			ang = 270; // face up
			drive = 4; // drive down to up 
		} else if (xFront < xPos) { // if it is facing left
			ang = 180; // face left
			drive = 3; // drive right to left  
		} else if (yFront > yPos) { // if it is facing down 
			ang = 90; // face down 
			drive = 2; // drive up to down 
		} else if (xFront > xPos){ // if it is facing right 
			ang = 0; // face right 
			drive = 1; // drive left to right 
		}
	} // End of assignSense 
	//-----------------------------------------------------------
	//---------------------MATHS APPLICATIONS--------------------
	public void calculationsOfLine(float x1, float y1, float x2, float y2) {
		this.x1 = x1; // coordinate of first X 
		this.y1 = y1; // coordinate of first Y
		this.x2 = x2; // coordinate of Second X
		this.y2 = y2; // coordinate of Second Y

		// calculating the slope of the line 
		m = (y2 - y1)/(x2 - x1);
		// calculating the length of the line 
		length = ((x2 - x1)*(x2 - x1)) + ((y2 - y1)*(y2 - y1)); // This have to be square root. then => 
		
		/*
		* Calculate square root in accordance to Java method Math.sqrt
		* Reference : http://unestudiantedeinformatica.blogspot.ie/2014/01/raiz-cuadrada-de-un-numero-mathsqrt-y.html
		*/
		length = (float)Math.sqrt(length);
	} // End of CalculationsOfLine

	public void areaOfTriangle() {
		//First of all we will assign values to our variables
		xA = xVertex1;
		yA = yVertex1;
		xB = xVertex2; 
		yB = yVertex2; 
		xC = xVertex3;
		yC = yVertex3;
		/*
		* The reason we are creating a new variable with the values of our vertexes is because we will need to 
		* modify those values in order to do some calculation to get the area of the triangle 
		*/
		xA = xA; // translate x to the origin by taking away its value in x
		yA = yA; // translate y to the origin by taking away its value in y 
		// Then we apply the same translation to the other points 
		xB -= xA; 
		yB -= yA; 
		xC -= xA;
		yC -= yA;
		// Finally =>
		area = ((xB*yC) - (xC*yB))/2; // calculate area of triangle 
	} // End of areaOfTriangle
} // End of public class Robot


/*
* Bibliography
*
* Alec, J., 2011. Alec's Web Log. [Online] 
* Available at: http://www.alecjacobson.com/weblog/?p=1659
* [Accessed 10 January 2017].
* 
* Movahedi, V., n.d. Conjunctive Normal Form, York University: CSE 3401.
* Roberts, D., 2012. Maths bits notebook. [Online] 
* Available at: https://mathbitsnotebook.com/Algebra1/FunctionGraphs/FNGTransformationDilation.html
* [Accessed 01 February 2017].
*
* Sterling, M. J., n.d. HOW TO PINPOINT THE CENTER OF A TRIANGLE. [Online] 
* Available at: http://www.dummies.com/education/math/trigonometry/how-to-pinpoint-the-center-of-a-triangle/
* [Accessed 30 January 2017].

* unestudiantedeinformatica, n.d. Blog Un Estudiante de Informatica. [Online] 
* Available at: http://unestudiantedeinformatica.blogspot.ie/2014/01/raiz-cuadrada-de-un-numero-mathsqrt-y.html
* [Accessed 01 02 2017].
*
* Wolfram Research, inc, 2017. Triangle Centroid. [Online] 
* Available at: http://mathworld.wolfram.com/TriangleCentroid.html
* [Accessed 05 February 2017].
*/

