package Demo;

//HuaRongRoad.java
import java.awt.*; 
import java.applet.*; 
import java.awt.event.*; 
import javax.swing.*;
class People extends Button implements FocusListener //�����ݵ�������� 
{
	Rectangle rect=null; 
	int left_x,left_y;//��Ť�����Ͻ����� 
	int width,height; //��Ť�Ŀ�͸� 
	String name; 
	int number; 
	People(int number,String s,int x,int y,int w,int h,HuaRongRoad road) 
	{
		super(s); 
		name=s;
		this.number=number; 
		left_x=x;left_y=y; 
		width=w;height=h;
		setBackground(Color.orange); 
		road.add(this);
		addKeyListener(road); 
		setBounds(x,y,w,h);
		addFocusListener(this); 
		rect=new Rectangle(x,y,w,h); 
	} 
	public void focusGained(FocusEvent e) 
	{ 
		setBackground(Color.red); 
	} 
	public void focusLost(FocusEvent e) 
	{ 
		setBackground(Color.orange); 
	} 
} 
public class HuaRongRoad extends Applet 
	implements KeyListener,ActionListener 
{ 
	/**
	*ʹ�ó��������ΪӦ�ó������С�
	*/
	public static void main(String args[]) {
		HuaRongRoad applet = new HuaRongRoad();
	    JFrame frame = new JFrame("���ݵ�");
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e){
	        System.exit(0);
	      }
	    });
	    frame.getContentPane().add(
	      applet, BorderLayout.CENTER);
	    frame.setSize(800,400);
	    applet.init();
	    applet.start();
	    frame.setVisible(true);
	  }
	
	People people[]=new People[10]; 
	Rectangle left,right,above ,below;//���ݵ��ı߽� 
	Button restart=new Button("���¿�ʼ"); 

	public void init() 
	{
		setLayout(null);
		restart.setBackground(Color.yellow); 
		add(restart); 
		restart.setBounds(5,5,80,25); 
		restart.addActionListener(this); 
		people[0]=new People(0,"�ܲ�",104,54,100,100,this); 
		people[1]=new People(1,"����",104,154,100,50,this); 
		people[2]=new People(2,"�ŷ�",54, 154,50,100,this); 
		people[3]=new People(3,"����",204,154,50,100,this); 
		people[4]=new People(4,"����",54, 54, 50,100,this); 
		people[5]=new People(5,"����",204, 54, 50,100,this); 
		people[6]=new People(6,"�� ",54,254,50,50,this); 
		people[7]=new People(7,"�� ",204,254,50,50,this); 
		people[8]=new People(8,"�� ",104,204,50,50,this); 
		people[9]=new People(9,"�� ",154,204,50,50,this); 
		people[9].requestFocus(); 
		left=new Rectangle(49,49,5,260); 
		people[0].setForeground(Color.white) ; 
		right=new Rectangle(254,49,5,260); 
		above=new Rectangle(49,49,210,5); 
		below=new Rectangle(49,304,210,5); 
	} 
	public void paint(Graphics g) 
	{
		//�������ݵ��ı߽� 
		g.setColor(Color.cyan); 
		g.fillRect(49,49,5,260);//left 
		g.fillRect(254,49,5,260);//right 
		g.fillRect(49,49,210,5); //above 
		g.fillRect(49,304,210,5);//below 
		//��ʾ�ܲ��ӳ�λ�úͰ������� 
		g.setColor(Color.blue); 
		g.drawString("�����Ӧ������,Ȼ�󰴼����ϵ��������Ҽ�ͷ�ƶ�",100,20); 
		g.setColor(Color.red); 
		g.drawString("�ܲٵ����λ��",110,300); 
	} 
	public void keyPressed(KeyEvent e) 
	{
		People man=(People)e.getSource();//��ȡ�¼�Դ 
		man.rect.setLocation(man.getBounds().x, man.getBounds().y); 
		if(e.getKeyCode()==KeyEvent.VK_DOWN) 
		{
	        man.left_y=man.left_y+50; //����ǰ��50����λ 
			man.setLocation(man.left_x,man.left_y); 
			man.rect.setLocation(man.left_x,man.left_y); 
			//�ж��Ƿ������������±߽�����ص�,��������ص����˻�50����λ���� 
			for(int i=0;i<10;i++) 
			{
				if((man.rect.intersects(people[i].rect))&&(man.number!=i)) 
				{
					man.left_y=man.left_y-50; 
					man.setLocation(man.left_x,man.left_y); 
					man.rect.setLocation(man.left_x,man.left_y); 
				} 
			} 
			if(man.rect.intersects(below)) 
			{
				man.left_y=man.left_y-50; 
				man.setLocation(man.left_x,man.left_y); 
				man.rect.setLocation(man.left_x,man.left_y); 
			} 
		} 
		if(e.getKeyCode()==KeyEvent.VK_UP) 
		{ 
			man.left_y=man.left_y-50; //����ǰ��50����λ 
			man.setLocation(man.left_x,man.left_y); 
			man.rect.setLocation(man.left_x,man.left_y); 
			//�ж��Ƿ������������ϱ߽�����ص�,��������ص����˻�50����λ���� 
			for(int i=0;i<10;i++) 
			{
				if((man.rect.intersects(people[i].rect))&&(man.number!=i)) 
				{
				man.left_y=man.left_y+50; 
				man.setLocation(man.left_x,man.left_y); 
				man.rect.setLocation(man.left_x,man.left_y); 
				} 
			} 
			if(man.rect.intersects(above)) 
			{ 
				man.left_y=man.left_y+50; 
				man.setLocation(man.left_x,man.left_y); 
				man.rect.setLocation(man.left_x,man.left_y); 
			} 
		} 
		if(e.getKeyCode()==KeyEvent.VK_LEFT) 
		{
			man.left_x=man.left_x-50; //����ǰ��50����λ 
			man.setLocation(man.left_x,man.left_y); 
			man.rect.setLocation(man.left_x,man.left_y); 
			//�ж��Ƿ�������������߽�����ص�,��������ص����˻�50����λ���� 
			for(int i=0;i<10;i++) 
			{
				if((man.rect.intersects(people[i].rect))&&(man.number!=i)) 
				{
					man.left_x=man.left_x+50; 
					man.setLocation(man.left_x,man.left_y); 
					man.rect.setLocation(man.left_x,man.left_y); 
				} 
			} 
			if(man.rect.intersects(left)) 
			{
				man.left_x=man.left_x+50; 
				man.setLocation(man.left_x,man.left_y); 
				man.rect.setLocation(man.left_x,man.left_y); 
			} 
		} 
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) 
		{
			man.left_x=man.left_x+50; //����ǰ��50����λ 
			man.setLocation(man.left_x,man.left_y); 
			man.rect.setLocation(man.left_x,man.left_y); 
			//�ж��Ƿ������������ұ߽�����ص�,��������ص����˻�50����λ���� 
			for(int i=0;i<10;i++) 
			{
				if((man.rect.intersects(people[i].rect))&&(man.number!=i)) 
				{
					man.left_x=man.left_x-50; 
					man.setLocation(man.left_x,man.left_y); 
					man.rect.setLocation(man.left_x,man.left_y); 
				} 
			} 
			if(man.rect.intersects(right)) 
			{ 
				man.left_x=man.left_x-50; 
				man.setLocation(man.left_x,man.left_y); 
				man.rect.setLocation(man.left_x,man.left_y); 
			} 
		} 
	} 
	public void keyTyped(KeyEvent e){} 
	public void keyReleased(KeyEvent e){} 
	public void actionPerformed(ActionEvent e) 
	{ 
		this.removeAll(); 
		this.init(); 
	} 
} 