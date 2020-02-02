import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.util.*;


import sun.audio.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;

import javax.sound.sampled.*;

public class NinJump extends JPanel implements KeyListener,Runnable,MouseListener
{
	private float angle;
	private int x;
	private int y;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage guy;
	BufferedImage[] guys=new BufferedImage[12];
	BufferedImage guyFlip;
	BufferedImage[] guysFlip=new BufferedImage[12];
	BufferedImage cloudPic;
	BufferedImage monster;
	boolean restart=false;
	int imgCount=0;
	int upCount=0;
	boolean jump=true;
	Ninja myNinja;
	int plus=0;
	boolean startGame=false;
	boolean up=true;
	boolean rightArrow=false;
	boolean leftArrow=false;
	boolean flip=false;
	boolean isMoving=true;
	boolean drawStart;
	boolean stillMove=false;
	boolean startCheck=false;
	Rectangle startButton;
	int myY=700;
	int rand;
	ArrayList<Cloud> cloudList=new ArrayList<Cloud>();
	ArrayList<Monster> monsterList=new ArrayList<Monster>();
	int rightCount=0,leftCount=0;
	boolean paintCloud=true;
	boolean addedOneCloud=false;
	boolean updatePos=true;
	Cloud index;
	boolean isBouncing=true;
	int addMonster;
	boolean goneDown=false;
	File islandMusic=new File("island_music_x.wav");
	File boing=new File("boing2.wav");
	File thunk=new File("thunk.wav");
	Clip clip;
	Clip boingClip;
	Clip thunkClip;
	Rectangle howTo;
	Rectangle goal;
	Rectangle about;
	
	boolean instruct=false;		
	boolean goals=false;
	boolean abouts=false;		

	Rectangle music;
	boolean musicOn=true;
	boolean boingClipOn=false;

	boolean died=false;

	int score=0;
	public NinJump()
	{
		frame=new JFrame();
		x=400;
		y=650;
		startMenu();
		//gameOn=true;
		try{
			clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(islandMusic));
			boingClip=AudioSystem.getClip();
			boingClip.open(AudioSystem.getAudioInputStream(boing));
			thunkClip=AudioSystem.getClip();
			thunkClip.open(AudioSystem.getAudioInputStream(thunk));						
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		}catch (Exception e){
			System.out.println("cannot play");
		}

		try {
			guy = ImageIO.read(new File("ninjatwo.png"));
			monster=ImageIO.read(new File("mushroom.png"));
			for(int i=0;i<12;i++)
				guys[i]=guy.getSubimage(i*80,720,80,80);

			guyFlip = ImageIO.read(new File("ninjatwoflip.png"));
			for(int i=11;i>=0;i--)
				guysFlip[i]=guy.getSubimage(i*80,720,80,80);

			cloudPic=ImageIO.read(new File("cloudtwo.png"));

			myNinja=new Ninja(guy,guys,guyFlip,guysFlip);
			rand=(int)(Math.random()*2)+1;
			if (rand==1) cloudList.add(new Cloud(myNinja.getX()-170,myNinja.getY()-110));
			if (rand==2) cloudList.add(new Cloud(myNinja.getX()+110,myNinja.getY()-110));

			

		}
		catch (IOException e) {
		}

	 //JButton button = new JButton("Click"); //we create the buttons to click
      //frame.add(button);
      //button.addActionListener(new AL());
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.add(this);
		frame.setSize(1000,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
		//run();
	}

	/*static void playSound(File sound){
		try{

			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

			Thread.sleep(clip.getMicrosecondLength()/1000);

		}catch (Exception e){

		}
	}*/

	public void run()
	{

		
		while(true)
		{
			//playSound(sound);
			try{
				if (musicOn){	
					FloatControl gainControl = 
    				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(+8.0f);
					
				} 
				else{
					FloatControl gainControl = 
    				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-100.0f);					
				}

			}catch(Exception e){

			}
				clip.start();

			if(gameOn)
			{
				
			
					if (isMoving){
					myNinja.updateRects();
					checkMonster();
					for (Monster m: monsterList) m.updatePos();		
					
					if (rightArrow){

					int addMonster=(int)(Math.random()*100)+1;
					if (addMonster%25==0){
						monsterList.add(new Monster(100, myNinja.getY()-110));
						System.out.println(addMonster+"num");
					}
					//for (Monster m: monsterList) m.updatePos();						
						//startCheck=true;
						System.out.println("doing right");
						boolean check=false;
						if(rightCount<11)
						{
							stillMove=true;
							if (updatePos) myNinja.updatePosRight();
							//if (goneDown) goneDown=false;

							rightCount++;
							//else gameOn=true;
						}

						/*if (startCheck){
							System.out.println("got to else");
							check=checkCloud();
							if (check){
								//updatePos=false;
								//paintCloud=false;
								check=false;
								System.out.println("intersects");
								/*if (!addedOneCloud){
									cloudList.add(new Cloud(myNinja.getX()+110, myNinja.getY()-100));
									cloudList.add(new Cloud(myNinja.getX()-110, myNinja.getY()-100));
									addedOneCloud=true;
								}*/
								//paintCloud=true;
								//startCheck=false;
								
							//}

						//} 

						//startCheck=false;						
						/*
						if (!(check)){
							gameOn=false;
							drawStart=true;;
						} 
						*/			
					}
						
						if (startCheck){
							System.out.println("checking");
							boolean check=checkCloud();
							try{
							if (check){
							 boingClip=AudioSystem.getClip();
							 boingClip.open(AudioSystem.getAudioInputStream(boing));	
							 boingClip.start();
							}
							}catch (Exception e){

							}
							if (!(check)) {
								gameOn=false;
								drawStart=true;
								//died=true;
								ninjaDied();
								restartGame();
							}							
							startCheck=false;
						}

					if (leftArrow){
					int addMonster=(int)(Math.random()*100)+1;
					if (addMonster%25==0){
						monsterList.add(new Monster(100, myNinja.getY()-110));
						System.out.println(addMonster+"num");
					}

						//startCheck=true;
						System.out.println("doing left");
						boolean check=false;
						if(leftCount<11){
							//imgCount=i;
							stillMove=true;
							if (updatePos) myNinja.updatePosLeft();
							//if (goneDown) goneDown=false;
				//else gameOn=true;	
							leftCount++;
							//if (imgCount>11) imgCount=1;
							
						}

	
						/*if (startCheck){	
							System.out.println("got to else");
							check=checkCloud();	
							if (check){
								//updatePos=false;
								//paintCloud=false;
								check=false;
								System.out.println("intersects");	
								/*if (!addedOneCloud){
									cloudList.add(new Cloud(myNinja.getX()+110, myNinja.getY()-100));
									cloudList.add(new Cloud(myNinja.getX()-110, myNinja.getY()-100));
									addedOneCloud=true;
								}*/
								//paintCloud=true;
								//startCheck=false;
								
							//} 	
							
						//}
						/*	
						if (!(check)) {
							gameOn=false;
							drawStart=true;
						}
						*/
						//imgCount=1;
						//startCheck=false;
					}	
					
						if (startCheck){
							System.out.println("checking");
							boolean check=checkCloud();
							try{
								if (check){
								 boingClip=AudioSystem.getClip();
								 boingClip.open(AudioSystem.getAudioInputStream(boing));	
								 boingClip.start();
								}
							}
							catch(Exception e){

							}
							if (!(check)) {
								gameOn=false;
								drawStart=true;
								//died=true;
								ninjaDied();
								restartGame();
							}							
							startCheck=false;
						}

					if (isBouncing){
						if (plus==-150) up=false;
						if (plus==0) up=true;

						if (up){ 
							plus-=5; 
							//myNinja.updateY(-5);
						}
						if (!(up)){
						 plus+=5;
						 //myNinja.updateY(5);
						}
						imgCount=Math.abs(plus/13);
					}



					myY=myNinja.getY();

					if (myNinja.getY()<131) {
						gameOn=false;
						
					}

					
				}

			}

			else if (!gameOn)
			{
				goDown();
				startCheck=false;
				//cloudGoDown();
				myNinja.decY();

			}
			repaint();

			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			try
			{
				t.sleep(20);
			}catch(InterruptedException e)
			{
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		Color myGray=new Color(96,96,96);

		//all painting happens here!

		g2d.setColor(Color.BLUE);
		g2d.fillRect(50,50,800,700);
		g2d.setColor(Color.BLACK);

		try{
		BufferedImage img = ImageIO.read(new File("stars.png"));
		g2d.drawImage(img,0,0,this);
		}catch (IOException e){

		}
		
		g2d.setFont(new Font("Monospaced",Font.PLAIN,36));
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score: "+score, 750,40);

		g2d.setFont(new Font ("Tamoha",Font.PLAIN,12));
		g2d.setColor(myGray);
		music=new Rectangle(52,52,100,40);
		g2d.fillRoundRect(52,52,100,40,50,15);
		g2d.setColor(Color.WHITE);
		if (!musicOn) g2d.drawString("Turn Music ON",55,75);
		else g2d.drawString("Turn Music OFF",55,75);

		if (died){
			g2d.setFont(new Font ("Courier",Font.BOLD,50) );
			g2d.drawString ("Game Over", 375,350);
			g2d.setFont(new Font ("Courier",Font.BOLD,30) );
			g2d.drawString ("Press Backspace to return to Start Menu",150,400);
		}

		if (drawStart && !died){
			g2d.setFont(new Font ("Courier",Font.BOLD,70) );
			g2d.setColor(Color.WHITE);
			g2d.drawString("NinJump",350,80);
			
			g2d.setColor(myGray);
			startButton=new Rectangle(400,300,150,100);
			g2d.fillRoundRect(400,300,150,100,35,35);
			g2d.setFont(new Font ("Tamoha",Font.PLAIN,24));
			g2d.setColor(Color.WHITE);
			g2d.drawString("Start Game",415,345);

			g2d.setColor(myGray);
			howTo=new Rectangle(400,450,150,50);
			g2d.fillRoundRect(400,450,150,50,40,20);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Instructions",410,475);

			g2d.setColor(myGray);
			goal=new Rectangle(400,550,150,50);
			g2d.fillRoundRect(400,550,150,50,40,20);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Goals",440,580);	

			g2d.setColor(myGray);
			about=new Rectangle(400,650,150,50);
			g2d.fillRoundRect(400,650,150,50,40,20);
			g2d.setColor(Color.WHITE);
			g2d.drawString("About",435,680);	

			if (instruct){
				try{
				BufferedImage img = ImageIO.read(new File("stars.png"));
				g2d.drawImage(img,0,0,this);
				}catch (IOException e){

				}

				g2d.setFont(new Font ("Tamoha",Font.PLAIN,20));
				g2d.setColor(Color.WHITE);	
				g2d.drawString("Use the left and right arrows to jump on clouds! Always Press backspace to return to previous screen.",40,375);						
			}

			if (goals){
				try{
				BufferedImage img = ImageIO.read(new File("stars.png"));
				g2d.drawImage(img,0,0,this);
				}catch (IOException e){

				}
				g2d.setFont(new Font ("Tamoha",Font.BOLD,16));
				g2d.setColor(Color.WHITE);								
				g2d.drawString("Stay on the clouds, and avoid the scary mushrooms! You will automatically move down so you have more space to jump!",10,375);
			}

			if (abouts){
				try{
				BufferedImage img = ImageIO.read(new File("stars.png"));
				g2d.drawImage(img,0,0,this);
				}catch (IOException e){

				}
				g2d.setFont(new Font ("Courier",Font.BOLD,30) );
				g2d.setColor(Color.WHITE);								
				g2d.drawString("Made by Nabhanya Neb, Block 2B",200,375);
			}			

		}

		if (!(drawStart)){
		//if (paintCloud){

			if (flip){ 
				g2d.drawImage(myNinja.setImage(imgCount), myNinja.getX(), myY+plus, -80, 80, null); 
				//g2d.drawRect(myNinja.drawRectFlip()[0],myNinja.drawRectFlip()[1],myNinja.drawRectFlip()[2],myNinja.drawRectFlip()[3]);
			}
			else{ 
				g2d.drawImage(myNinja.setImage(imgCount),myNinja.getX(),myY+plus,null);
				//g2d.drawRect(myNinja.drawRect()[0],myNinja.drawRect()[1],myNinja.drawRect()[2],myNinja.drawRect()[3]);
			}

			for (int i=0;i<cloudList.size();i++){
				g2d.drawImage(cloudPic,cloudList.get(i).getX(),cloudList.get(i).getY()+10,null);
				//g2d.drawRect(cloudList.get(i).drawRect()[0],cloudList.get(i).drawRect()[1],cloudList.get(i).drawRect()[2],cloudList.get(i).drawRect()[3]);
			
			}

			for (int i=0;i<monsterList.size();i++){
				int color=(int)(Math.random()*4)+1;
				g2d.drawImage(monster.getSubimage(570-(140*color),13,32,32),monsterList.get(i).getX(), monsterList.get(i).getY(),null);
				//g2d.drawRect(monsterList.get(i).drawRect()[0],monsterList.get(i).drawRect()[1],monsterList.get(i).drawRect()[2],monsterList.get(i).drawRect()[3]);

			}


		}

		//if (!flip) g2d.drawRect(myNinja.getRect());
		//else g2d.drawRect(myNinja.getRectFlipped());


		if (isMoving){

			//g2d.drawString("!", myNinja.getX(), myNinja.getY());

			if (!flip){
			//g2d.drawString("*",myNinja.getX()+10, myNinja.getY()+30);
			//g2d.drawString("*",myNinja.getX()+60, myNinja.getY()+60);
			}

			else{
			//g2d.drawString("*",myNinja.getX()-10, myNinja.getY()+30);
			//g2d.drawString("*",myNinja.getX()-60, myNinja.getY()+60);
			}

		} 
		/*for (Cloud c: cloudList){
			g2d.drawString("$", c.getX(), c.getY());
			g2d.drawString("%", c.getX(), c.getY()+20);
			g2d.drawString("%", c.getX()+65, c.getY()+60);
		}*/ 

	} 
	public void keyPressed(KeyEvent key)
	{
		System.out.println(key.getKeyCode());
		if(key.getKeyCode()==39 && !rightArrow)
		{
			updatePos=true;
			isBouncing=false;
			rightArrow=true;
			flip=false;
			System.out.println("right");
			/*if (goneDown){
				startCheck=false;
				//goneDown=false;
			}*/

				//try{


					//clip.start();

					//Thread.sleep(clip.getMicrosecondLength()/1000);

					//System.out.println("playing sound");

				//}catch (Exception e){
				//	System.out.println("cannot play");
				//}	
		}
		if (key.getKeyCode()==37 &&!leftArrow){
			updatePos=true;
			isBouncing=false;
			leftArrow=true;
			flip=true;
			System.out.println("left");

			/*if (goneDown){
				startCheck=false;
				//goneDown=false;
			}*/			
		}

		if (key.getKeyCode()==8 && instruct==true){
			instruct=false;
			drawStart=true;
		}

		if (key.getKeyCode()==8 && goals==true){
			goals=false;
			drawStart=true;
		}

		if (key.getKeyCode()==8 && abouts==true){
			abouts=false;
			drawStart=true;
		}	

		//if(key.getKeyCode()==8 && died=true){
		//	died=false;
		//	drawStart=true;
		//}

		if (key.getKeyCode()==8 && !abouts && !goals && !instruct) {
			died=false;
			drawStart=true;
		}		

		//if(key.getKeyCode()==82)
		//	restart=true;
	}
	public void keyReleased(KeyEvent key)
	{
		if(key.getKeyCode()==39)
		{
			//if (goneDown) startCheck=false;
			 startCheck=true;
			rightArrow=false;
			rightCount=0;
			isMoving=true;	
			addedOneCloud=false;
			isBouncing=true;
			//plus=0;
			 
			//cloudList.remove(0);
			//stillMove=false;
			//startCheck=true;
			/*for (Cloud c: cloudList){
				if (!c.equals(index)) cloudList.remove(c);
			}*/

		}
		if (key.getKeyCode()==37){
			//if (goneDown) startCheck=false;
			 startCheck=true;
			leftArrow=false;
			leftCount=0;
			isMoving=true;	
			addedOneCloud=false;
			isBouncing=true;
			//plus=0;
			
			//startCheck=true;
			/*for (Cloud c: cloudList){
				if (!c.equals(index)) cloudList.remove(c);
			}*/			
		}

	}
	public void keyTyped(KeyEvent key)
	{

	}

 	public void mousePressed(MouseEvent e) {
 		
       if (startButton.contains(e.getX(),e.getY())){
       	startClicked();
       	System.out.println("detected");
       }

       if (howTo.contains(e.getX(), e.getY())){
       	howToClicked();
       	System.out.println("instruct clicked");
       }

       if (goal.contains(e.getX(), e.getY())){
       	goalClicked();
       	System.out.println("goal clicked");
       }   

        if (about.contains(e.getX(), e.getY())){
       	aboutClicked();
       	System.out.println("about clicked");
       }   

       if (music.contains(e.getX(), e.getY())){
       	musicClicked();
       }        
    }

    public void mouseReleased(MouseEvent e) {
     
    }

    public void mouseEntered(MouseEvent e) {
      
    }

	public void mouseExited(MouseEvent e){

	}

    public void mouseClicked(MouseEvent e) {
       
    }

	public void goDown(){
		isMoving=false;

		 myY+=5;
		if (myY<700) cloudGoDown();
		if(myY==700)
		{
			gameOn=true;
			plus=0;
			isMoving=true;
			score*=2;
			//goneDown=true;
		}
	}

	public void restartGame(){
		cloudList.clear();
		monsterList.clear();
		score=0;
		cloudList=new ArrayList<Cloud>();
		monsterList=new ArrayList<Monster>();
			myNinja=new Ninja(guy,guys,guyFlip,guysFlip);
			myNinja.setX(400);
			myNinja.setY(700);
			rand=(int)(Math.random()*2)+1;
			if (rand==1) cloudList.add(new Cloud(myNinja.getX()-110,myNinja.getY()-110));
			if (rand==2) cloudList.add(new Cloud(myNinja.getX()+110,myNinja.getY()-110));		
	}

	public void cloudGoDown(){
		if (myY!=700){
			for (Cloud c: cloudList) c.decY();
		}
	}

	public boolean checkCloud(){
		boolean ret=false;
		myNinja.createRects();
		for (Cloud cloud: cloudList){
			if (!flip){
				if (myNinja.getRect().intersects(cloud.getRect())){
					ret=true;
					System.out.println("intersects");
					score+=10;
					index=cloud;
				}
			}

			else{
				if (myNinja.getRectFlipped().intersects(cloud.getRect())){
					ret=true;
					System.out.println("intersects");
					score+=10;
					index=cloud;
				}
			}			
		}
		if (ret){
			//boingClip.start();
			boingClipOn=true;
			if (myNinja.getY()-110<131){
			 gameOn=false;
			 goDown();
			 cloudGoDown();
			}
			if (x+110>890) cloudList.add(new Cloud(myNinja.getX()-170, myNinja.getY()-110));
			else if (x-200<0) cloudList.add(new Cloud(myNinja.getX()+110, myNinja.getY()-110));
			else{
				int rand=(int)(Math.random()*2)+1;
				if (rand==1) cloudList.add(new Cloud(myNinja.getX()+110, myNinja.getY()-110));
				else if (rand==2) cloudList.add(new Cloud(myNinja.getX()-170, myNinja.getY()-110));
			}
			for (int i=0;i<cloudList.size()-2;i++) cloudList.remove(i);
			repaint();
		}
		return ret;
	}

	public void checkMonster(){
		for (int i=0;i<monsterList.size();i++){
			if (!flip){
				if /*( (myNinja.getX()==m.getX() && myNinja.getY()+plus==m.getY()) ||*/  (monsterList.get(i).getRect().intersects(myNinja.getRect())){
					System.out.println("monster");//)//{//(m.getRect().contains(myNinja.getX(),myNinja.getY()+plus)){//(myNinja.getX()==m.getX() && myNinja.getY()+plus==m.getY()){//if (m.getRect().intersects(myNinja.getRect())){
					System.out.println("monster");
					ninjaDied();
					gameOn=false;
					restartGame();
					//drawStart=true;
				} 
			}
			else{
				if /*( (myNinja.getX()==m.getX() && myNinja.getY()+plus==m.getY()) ||*/  (monsterList.get(i).getRect().intersects(myNinja.getRectFlipped())){//(m.getRect().contains(myNinja.getX(),myNinja.getY()+plus)){//(myNinja.getX()==m.getX() && myNinja.getY()+plus==m.getY()){//(m.getRect().intersects(myNinja.getRectFlipped())){
					 
					 ninjaDied();
					 gameOn=false;
					 System.out.println("monster"); 
					 restartGame();
					 //drawStart=true;
				}
			} 
		}
	}

	public void ninjaDied(){

		
		try{
			thunkClip=AudioSystem.getClip();
			thunkClip.open(AudioSystem.getAudioInputStream(thunk));	
		}
		catch (Exception e){

		}
		thunkClip.start();
		died=true;
	}

	public void startMenu(){
		drawStart=true;
		gameOn=false;
		//clip.start();
	}
	public void startClicked(){
		//clip.start();
		System.out.println("start clicked");
		drawStart=false;
		gameOn=true;
		isMoving=true;
	}

	public void howToClicked(){
		instruct=true;

	}

	public void goalClicked(){
		goals=true;
	}

	public void aboutClicked(){
		abouts=true;
	}

	public void musicClicked(){
		if (musicOn) musicOn=false;
		else musicOn=true;
	}


    public static class AL implements ActionListener{
        public final void actionPerformed(ActionEvent e){
        //here we creates a method AL that implements ActionListener for the button
            music();
        }
    }

        public static void music(){
        AudioPlayer MGP = AudioPlayer.player; //here we create an AudioPlayer from sun.audio
        AudioStream BGM; //here we create an AudioStream from sun.audio
        AudioData MD; //here we create an AudioData from sun.audio
        
        ContinuousAudioDataStream loop = null;
        //here we loop our audio inside the program
        
        try{
        BGM = new AudioStream(new FileInputStream("arrow.wav")); //here we find our sound file
        MD = BGM.getData();
        loop = new ContinuousAudioDataStream(MD);
        }catch(IOException error){//here we catch an IOException, if it occurs it will print “yo”
            System.out.println("yo"); 
        }
        
        MGP.start(loop); //here we’ll start the loop whenever the method “music” is called
    }

	public static void main(String args[])
	{
		NinJump app=new NinJump();
	}
}