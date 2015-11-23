package Demo;

// CalculatorGUI.java
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI {
	
  // 定义按钮
  private Button key0, key1, key2, key3, key4;
  private Button key5, key6, key7, key8, key9;
  private Button keyequal, keyplus, keyminus;
  private Button keyperiod, keymult, keydiv;
  
  // 定义存放按钮的区域
  private Panel buttonArea;
  
  // 定义存放运算结果的区域
  private Label answer;
  
  // 用来实现运算功能的类的对象实例化
  private Calculator calculator;
  private boolean    readyForNextNumber;

  public CalculatorGUI() {
    calculator = new Calculator();
    readyForNextNumber = true;

    answer = new Label("0.0",Label.RIGHT);

    key0 = new Button("0");
    key1 = new Button("1");
    key2 = new Button("2");
    key3 = new Button("3");
    key4 = new Button("4");
    key5 = new Button("5");
    key6 = new Button("6");
    key7 = new Button("7");
    key8 = new Button("8");
    key9 = new Button("9");
    keyequal = new Button("=");
    keyplus = new Button("+");
    keyminus = new Button("-");
    keymult = new Button("*");
    keydiv = new Button("/");
    keyperiod = new Button(".");
    buttonArea = new Panel();
  }

  public void launchFrame() {
    buttonArea.setLayout(new GridLayout(4,4));

    buttonArea.add(key7);
    buttonArea.add(key8);
    buttonArea.add(key9);
    buttonArea.add(keyplus);
    buttonArea.add(key4);
    buttonArea.add(key5);
    buttonArea.add(key6);
    buttonArea.add(keyminus);
    buttonArea.add(key1);
    buttonArea.add(key2);
    buttonArea.add(key3);
    buttonArea.add(keymult);
    buttonArea.add(key0);
    buttonArea.add(keyperiod);
    buttonArea.add(keyequal);
    buttonArea.add(keydiv);

    // 设置事件接收句柄
    OpButtonHanlder op_handler = new OpButtonHanlder();
    NumberButtonHanlder number_handler = new NumberButtonHanlder();
    key0.addActionListener(number_handler);
    key1.addActionListener(number_handler);
    key2.addActionListener(number_handler);
    key3.addActionListener(number_handler);
    key4.addActionListener(number_handler);
    key5.addActionListener(number_handler);
    key6.addActionListener(number_handler);
    key7.addActionListener(number_handler);
    key8.addActionListener(number_handler);
    key9.addActionListener(number_handler);
    keyperiod.addActionListener(number_handler);
    keyplus.addActionListener(op_handler);
    keyminus.addActionListener(op_handler);
    keymult.addActionListener(op_handler);
    keydiv.addActionListener(op_handler);
    keyequal.addActionListener(op_handler);

    // 新建一个帧，并且加上消息监听
    Frame f = new Frame("Calculator");
    f.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
	  System.exit(0);
	}
    });
    f.setSize(200, 200);

    f.add(answer, BorderLayout.NORTH);
    f.add(buttonArea, BorderLayout.CENTER);
    f.setVisible (true);
  }

  //响应按钮事件，并根据不同的按钮事件进行不同的运算
  private class OpButtonHanlder implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      char  operator = event.getActionCommand().charAt(0);
      String result = "";
      switch  ( operator ) {
      case '+':
	result = calculator.opAdd(answer.getText());
	break;
      case '-':
	result = calculator.opSubtract(answer.getText());
	break;
      case '*':
	result = calculator.opMultiply(answer.getText());
	break;
      case '/':
	result = calculator.opDivide(answer.getText());
	break;
      case '=':
	result = calculator.opEquals(answer.getText());
	break;
      }
      answer.setText(result);
      readyForNextNumber = true;
    }
  }

  //处理连续运算时主界面的显示
  private class NumberButtonHanlder implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if ( readyForNextNumber ) {
	answer.setText(event.getActionCommand());
	readyForNextNumber = false;
      } else {
	answer.setText(answer.getText() + event.getActionCommand().charAt(0));
      }
    }
  }

 //主函数，也是该应用程序的执行入口处
  public static void main(String args[]) {
    CalculatorGUI calcGUI = new CalculatorGUI();
    calcGUI.launchFrame();
  }
}
