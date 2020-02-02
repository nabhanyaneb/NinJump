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

public class Monster{
	int x;
	int y;
	Rectangle myRect;
	public Monster(int xx, int yy){
		x=xx;
		y=yy;
		myRect=new Rectangle(x,y,32,32);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	public void updatePos(){
		if (x<1000){
			x+=2;
		}
		else x=0;
		myRect=new Rectangle(x,y,32,32);
		
	}
	public Rectangle getRect(){
		return myRect;
	}
	public int[] drawRect(){
		int[] ret=new int[4];
		ret[0]=x;
		ret[1]=y;
		ret[2]=32;
		ret[3]=32;

		return ret;
	}
}
