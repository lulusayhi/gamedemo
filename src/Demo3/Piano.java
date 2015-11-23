package Demo3;

//Piano.java
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Piano extends Applet implements MouseListener,KeyListener,MouseMotionListener
{
    //变量声明
    Image m_ImgUp;//键盘、鼠标松开时显示的键盘图片
    Image m_ImgDown;//键盘、鼠标按下时显示的键盘图片
    AudioClip[] m_AudioClip=new AudioClip[14];
    int[] m_nState=new int[14];
    int m_nOldDownCount=-1;
    final int IMG_WIDTH=17;
    final int IMG_HEIGHT=85;
    final int STATE_UP=0;
    final int STATE_DOWN=1;
    //初始化小程序
    public void init()
    {
        for(int i=0;i<14;i++)
        {
            m_nState[i]=0;
        }
        m_ImgUp=getImage(getDocumentBase(),"img/up.gif");
        m_ImgDown=getImage(getDocumentBase(),"img/down.gif");
        MediaTracker mediaTracker=new MediaTracker(this);
        mediaTracker.addImage(m_ImgUp,0);
        mediaTracker.addImage(m_ImgDown,1);
        try
        {
            mediaTracker.waitForID(0);
        }
        catch(Exception e)
        {
            System.out.println("m_ImgUp is not loaded right");
        }
        try
        {
            mediaTracker.waitForID(1);
        }
        catch(Exception e)
        {
            System.out.println("m_ImgDown is not loaded right");
        }

        for(int i=0;i<14;i++)
        {
            String sUrl=new String("au/"+i+".au");
            m_AudioClip[i]=getAudioClip(getCodeBase(),sUrl);
        }
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    //画屏函数
    public void paint(Graphics g)
    {
        for(int i=0;i<14;i++)
        {
            switch(m_nState[i])
            {
                case STATE_UP:
                    g.drawImage(m_ImgUp,i*IMG_WIDTH,0,this);
                    break;
                case STATE_DOWN:
                    g.drawImage(m_ImgDown,i*IMG_WIDTH,0,this);
                    break;
            }

        }
    }
    //主函数
    public static void main(String[] args)
    {
        Frame frame=new Frame();
        Piano piano=new Piano();
        frame.add(piano);
        Dimension dimension=new Dimension(40,200);
        frame.setSize(dimension);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                       System.exit(0);
            }
    	});
        frame.setVisible(true);
        frame.repaint() ;
    }
    void showSound(int nCount)
    {
        m_AudioClip[nCount].play();
    }
    //响应点击鼠标
    public void mouseClicked(MouseEvent e)
    {
            int nMouseCount=e.getClickCount();
    }
    //响应鼠标进入
    public void mouseEntered(MouseEvent e)
    {
    }
    //响应鼠标退出
    public void mouseExited(MouseEvent e)
    {
        if(m_nOldDownCount!=-1)m_nState[m_nOldDownCount]=STATE_UP;
        m_nOldDownCount=-1;
    }
    //响应按下鼠标
    public void mousePressed(MouseEvent e)
    {
        int nX=e.getX();
        int nY=e.getY();

        int nCount=nX/IMG_WIDTH;
        m_nState[nCount]=STATE_DOWN;
        showSound(nCount);
        m_nOldDownCount=nCount;
        Graphics g=getGraphics();
        g.drawImage(m_ImgDown,nCount*IMG_WIDTH,0,this);
    }
    //响应松开鼠标
    public void mouseReleased(MouseEvent e)
    {
        int nX=e.getX();
        int nY=e.getY();
        int nCount=nX/IMG_WIDTH;
        m_nState[nCount]=STATE_UP;
        m_nOldDownCount=-1;
        Graphics g=getGraphics();
        g.drawImage(m_ImgUp,nCount*IMG_WIDTH,0,this);
    }
    //响应按下键盘
    public void keyPressed(KeyEvent e)
    {
        int nKeyCode=e.getKeyCode();
        String sKeyName=e.getKeyText(nKeyCode);
        boolean bControlDown=e.isControlDown();

        int nCount=-1;
        switch(nKeyCode)
        {
            case KeyEvent.VK_1:
                nCount=0;
                break;
            case KeyEvent.VK_2:
                nCount=1;
                break;
            case KeyEvent.VK_3:
                nCount=2;
                break;
            case KeyEvent.VK_4:
                nCount=3;
                break;
            case KeyEvent.VK_5:
                nCount=4;
                break;
            case KeyEvent.VK_6:
                nCount=5;
                break;
            case KeyEvent.VK_7:
                nCount=6;
                break;
            default:
                return;
        }
        if(bControlDown)nCount=nCount+7;
        setPianoKeyDown(nCount);
        showSound(nCount);
        Graphics g=getGraphics();
        g.drawImage(m_ImgDown,nCount*IMG_WIDTH,0,this);

    }
    public void keyReleased(KeyEvent e)
    {
        int nKeyCode=e.getKeyCode();
        String sKeyName=e.getKeyText(nKeyCode);
        boolean bControlDown=e.isControlDown();
        int nCount=-1;
        switch(nKeyCode)
        {
            case KeyEvent.VK_1:
                nCount=0;
                break;
            case KeyEvent.VK_2:
                nCount=1;
                break;
            case KeyEvent.VK_3:
                nCount=2;
                break;
            case KeyEvent.VK_4:
                nCount=3;
                break;
            case KeyEvent.VK_5:
                nCount=4;
                break;
            case KeyEvent.VK_6:
                nCount=5;
                break;
            case KeyEvent.VK_7:
                nCount=6;
                break;
            default:
                return;
        }
        if(bControlDown)nCount=nCount+7;
        m_nState[nCount]=STATE_UP;
        Graphics g=getGraphics();
        g.drawImage(m_ImgUp,nCount*IMG_WIDTH,0,this);
    }
    public void keyTyped(KeyEvent e)
    {
    }
    void setPianoKeyDown(int nCount)
    {
        for(int i=0;i<14;i++)
        {
            m_nState[i]=STATE_UP;
        }
        m_nState[nCount]=STATE_DOWN;
    }
    void setAllPianoKeyUp()
    {
        for(int i=0;i<14;i++)
        {
            m_nState[i]=STATE_UP;
        }
    }
    //响应拖动鼠标
    public void mouseDragged(MouseEvent e)
    {
        int nX=e.getX();
        int nY=e.getY();
        System.out.println("鼠标拖动到：x="+nX+"   y="+nY);
        int m_nTempCount=nX/IMG_WIDTH;
        if(m_nTempCount==m_nOldDownCount)return;
        m_nState[m_nTempCount]=STATE_DOWN;
        m_nState[m_nOldDownCount]=STATE_UP;
        showSound(m_nTempCount);
        Graphics g=getGraphics();
        g.drawImage(m_ImgDown,m_nTempCount*IMG_WIDTH,0,this);
        g.drawImage(m_ImgUp,m_nOldDownCount*IMG_WIDTH,0,this);
        m_nOldDownCount=m_nTempCount;
    }
    //响应移动鼠标
    public void mouseMoved(MouseEvent e)
    {
        int nX=e.getX();
        int nY=e.getY();
        System.out.println("鼠标移动到：x="+nX+"   y="+nY);
    }
}
