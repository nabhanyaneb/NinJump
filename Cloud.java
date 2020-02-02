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

public class Cloud{
	int locX;
	int locY;
	Rectangle myRect;
	public Cloud(int x, int y){
		locX=x;
		locY=y;
		myRect=new Rectangle(locX,locY+20,65,40);
	}

	public int[] drawRect(){
		int[] ret=new int[4];
		ret[0]=locX;
		ret[1]=locY+20;
		ret[2]=65;
		ret[3]=40;
		return ret;
	}

	public int getX(){
		return locX;
	}
	public int getY(){
		return locY;
	}

	public void decY(){
		locY+=5;
		myRect=new Rectangle(locX,locY+20,65,40);
	}

	public Rectangle getRect(){
		return myRect;
	}
}