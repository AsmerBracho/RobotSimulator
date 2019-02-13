Author: Asmer Bracho 

Java & Processing Programme
*************************************************************************************************************************
This code is a Robot Simulator, able to display 3 Robots, Alice, Bob & Charlie 

Alice patrol the environment doing a circle on each corner, while the program runs
(Notice that Alice position has been modify to show that the Robot Alice will look to follow what she was told to do(patrol 
the environment)) 

Bob is performing a random walk 

Charlie is remoted control and it can be moved by using: 
A = Left 
W = Up 
D = Right 
S = Down 

Robot.java file is the one which contain variables, constructors and methods. And this is basically the 
one in charge to define the way how the robots are going to be created, how they going to get properties such 
as colour, speed, height, width and so on. 

RobotSimulator.java file contain the main method and the processing methods such as settings, setup, draw etc.
In here we define the width and height of the screen, as well as creating the robots and setting up the behaviour they are 
going  to follow. 
This file has as well certain sections define for the user to change the position and orientation of any of the Robots.
**************************************************************************************************************************

To compile my code follow the steeps below: 
1.	Since this code use java and processing, make sure the core.jar file is accompanying the files RobotSimulator.java
and Robot.java. In order to do that, go to the processing  folder in your computer ...\core\library, COPY the core.jar 
File and paste it in the same folder you have your Robot.java and RobotSimulator.java 
2.	Open cmd 
3.	Navigate through cmd by using cd command till get to the folder which contain your files 
4.	Finally compile as follow: javac –cp “.;core.jar” RobotSimulator.java Robot.java 
**************************************************************************************************************************

To Run my code: java –cp “.;core.jar” RobotSimulator

NB: If after running your code the Robot Charlie DOES NOT move click the windows running the programme and try again
(this happens due to sometimes another programme in your computer might take over, sending  the RobotSimulator programme 
to a second level.)
 
