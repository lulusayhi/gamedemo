package Demo;

//MouseTrack.java
import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;

public class MouseTrack extends java.applet.Applet {
    //变量声明
    int mx, my;
    int onaroll;
    //初始化小程序
    public void init() {
	onaroll = 0;
	resize(500, 500);
	setBackground(Color.black);
    }
    //画屏函数
    public void paint(Graphics g) {
        g.setColor(Color.red);	
	g.drawRect(0, 0, size().width - 1, size().height - 1);
	mx = (int)(Math.random()*1000) % (size().width - (size().width/10));
	my = (int)(Math.random()*1000) % (size().height - (size().height/10));
	g.drawRect(mx, my, (size().width/10) - 1, (size().height/10) - 1);
    }

    //响应按下鼠标
    public boolean mouseDown(java.awt.Event evt, int x, int y) {
	requestFocus();
	if((mx < x && x < mx+size().width/10-1) && (my < y && y < my+size().height/10-1)) {
	    if(onaroll > 0) {
		switch(onaroll%4) {
		case 0:
		    break;
		case 1:
		    break;
		case 2:
		    break;
		case 3:
		    break;
		}
		onaroll++;
		if(onaroll > 5)
		    getAppletContext().showStatus("You're on your way to THE HALL OF FAME:"
			+ onaroll + "Hits!");
		else
		    getAppletContext().showStatus("YOU'RE ON A ROLL:" + onaroll + "Hits!");
	    }
	    else {
		getAppletContext().showStatus("HIT IT AGAIN! AGAIN!");		
		onaroll = 1;
	    }
	}
	else {
	    getAppletContext().showStatus("You hit nothing at (" + x + ", " + y + "), exactly");	    
	    onaroll = 0;
	}
	repaint();
	return true;
    }
    //响应移动鼠标
    public boolean mouseMove(java.awt.Event evt, int x, int y) {
	if((x % 3 == 0) && (y % 3 == 0))
	    repaint();
	return true;
    }
    //响应鼠标进入
    public void mouseEnter() {
	repaint();
    }
    //响应鼠标退出
    public void mouseExit() {
	onaroll = 0;
	repaint();
    }
    //响应按下键盘  
    public void keyDown(int key) {
	requestFocus();
	onaroll = 0;
    }
}