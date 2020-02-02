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

public class Ninja{

		BufferedImage guy;
		BufferedImage[] guys;
		BufferedImage guyFlip;
		BufferedImage[] guysFlip;
		int myX=400;
		int myY=700;
		int imgCount;
		Rectangle myRect;
		Rectangle myRectFlipped;

		public Ninja(BufferedImage gguy, BufferedImage[] gguys, BufferedImage gguyFlip, BufferedImage[] gguysFlip){
			guy=gguy;
			guys=gguys;
			guyFlip=gguyFlip;
			guysFlip=gguysFlip;
		}
		public BufferedImage setImage(int imageCount){
			return guys[imageCount];
		}
		public void updatePosRight(){
			myY-=10;
			myX+=10;
		}

		public void updatePosLeft(){
			myY-=10;
			myX-=10;
		}

		public void createRects(){
			myRect=new Rectangle(myX+10,myY+30,50,30);
			myRectFlipped=new Rectangle(myX-60,myY+30,50,30);
		}

		public void updateRects(){
			myRect=new Rectangle(myX+10,myY+30,50,30);
			myRectFlipped=new Rectangle(myX-60,myY+30,50,30);			
		}

		public int getX(){
			return myX;
		}

		public int getY(){
			return myY;
		}
		public void setX(int set){
			myX=set;
		}
		public void setY(int set){
			myY=set;
		}
		public void decY(){
			myY=700;
		}
		public void updateY(int add){
			myY+=add;
		}
		public int getImgCount(){
			return 1;
		}
		public Rectangle getRect(){
			return myRect;
		}
		public Rectangle getRectFlipped(){
			return myRectFlipped;
		}

		public int[] drawRect(){
			int[] ret=new int[4];
			ret[0]=myX+10;
			ret[1]=myY+30;
			ret[2]=50;
			ret[3]=30;

			return ret;
		}

		public int[] drawRectFlip(){
			int[] ret=new int[4];
			ret[0]=myX-60;
			ret[1]=myY+30;
			ret[2]=50;
			ret[3]=30;

			return ret;
		}
}
