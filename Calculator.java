
/**
 * Calculator
 * 
 * @author James Sonntag
 * @version v1.0.1
 */
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame
{
    JTextField textField;
    private boolean Number = true; //tells if there has been a number entered or not.
    private boolean Operator = true; //tells if an operator has been used yet.

    public static void main(String[] args) {
        JFrame calc = new Calculator(); //creates a new Calculator function
        calc.setVisible(true);
    }

    /**
     * Constructor for objects of class Calculator
     */
    public Calculator()
    {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400); //sets the size of the window
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //centers the calculator

        JPanel textPanel= new JPanel(); //creates a text panel
        textPanel.setLayout(new FlowLayout());
        textField= new JTextField("0", 20); //creates a new text field
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textPanel.add(textField);
        add(textPanel, BorderLayout.NORTH);

        JPanel Arithmetic= new JPanel(); //creates a new panel that the Arithmatic operators will go into
        Arithmetic.setLayout(new GridLayout(5,1));

        JPanel numberPanel= new JPanel(); //creates a panel that the numbers go into 
        numberPanel.setLayout(new GridLayout(4,3));

        /**
         * This code creates a button, adds an action listener to it, and adds each one
         * to its correct panel.
         */
        JButton one = new JButton("1");
        one.addActionListener(new numberListener());
        numberPanel.add(one);
        JButton two = new JButton("2");
        two.addActionListener(new numberListener());
        numberPanel.add(two);
        JButton three = new JButton("3");
        three.addActionListener(new numberListener());
        numberPanel.add(three);
        JButton four = new JButton("4");
        four.addActionListener(new numberListener());
        numberPanel.add(four);
        JButton five = new JButton("5");
        five.addActionListener(new numberListener());
        numberPanel.add(five);
        JButton six = new JButton("6");
        six.addActionListener(new numberListener());
        numberPanel.add(six);
        JButton seven = new JButton("7");
        seven.addActionListener(new numberListener());
        numberPanel.add(seven);
        JButton eight = new JButton("8");
        eight.addActionListener(new numberListener());
        numberPanel.add(eight);
        JButton nine = new JButton("9");
        nine.addActionListener(new numberListener());
        numberPanel.add(nine);
        JButton zero = new JButton("0");
        zero.addActionListener(new numberListener());
        numberPanel.add(zero);
        add(numberPanel, BorderLayout.WEST);

        JButton ButtonLP = new JButton("(");
        ButtonLP.addActionListener(new operatorListener());
        
        JButton ButtonRP = new JButton(")");
        ButtonRP.addActionListener(new operatorListener());
        

        JButton ButtonEq = new JButton(" = ");
        ButtonEq.addActionListener(new operatorListener());
        
        JButton ButtonPl = new JButton(" + ");
        ButtonPl.addActionListener(new operatorListener());
        
        JButton ButtonMin = new JButton(" - ");
        ButtonMin.addActionListener(new operatorListener());
        
        JButton ButtonTim = new JButton(" * ");
        ButtonTim.addActionListener(new operatorListener());
        
        JButton ButtonDiv = new JButton(" / ");
        ButtonDiv.addActionListener(new operatorListener());
        
        add(Arithmetic, BorderLayout.EAST);
        JButton Period = new JButton(".");
        Period.addActionListener(new operatorListener());
        numberPanel.add(Period);
        add(Arithmetic, BorderLayout.EAST);
        
        Arithmetic.add(ButtonLP);
        Arithmetic.add(ButtonRP);
        Arithmetic.add(ButtonPl);
        Arithmetic.add(ButtonMin);
        Arithmetic.add(ButtonTim);
        Arithmetic.add(ButtonDiv);
        Arithmetic.add(ButtonEq);

        //this creates a clear button and add its functions.
        JPanel c = new JPanel();
        c.setLayout(new FlowLayout());
        JButton clear = new JButton("Clear");
        clear.addActionListener(new operatorListener());
        c.add(clear);
        add(c, BorderLayout.CENTER);
        pack();
    }

    //This is the basic clear method
    public void Clear() {
        Number = true;
        textField.setText("0");
    }

    /**
     * This method takes a post-fix expression and evaluates its value
     * 
     * @return     The value of the evaluated expression
     */
    public static double evaluatePostfix(String expression) {
        MyStack evalStack = new MyStack();
        String[] split = expression.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("+")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand + secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("-")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand - secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("*")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand * secondOperand;
                evalStack.push(new StackNode(result));
            } else if (split[i].equals("/")) {
                double secondOperand = evalStack.pop().getData();
                double firstOperand = evalStack.pop().getData();
                double result = firstOperand / secondOperand;
                evalStack.push(new StackNode(result));
            } else {
                evalStack.push(new StackNode(Double.parseDouble(split[i])));
            }
        }
        double expressionValue = evalStack.pop().getData();
        return expressionValue;
    }

    /**
     * This inside class is a basic action listener that will add whatever operator
     * to the text field.
     */
    public class operatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String operator = e.getActionCommand();
            if (operator.equals("(")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(")")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(" / ")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(" * ")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(" - ")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(" + ")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals("Clear")) {
                textField.setText("0");
                Number = true;
            } else if(operator.equals(".")) {
                textField.setText(textField.getText() + operator);
            } else if (operator.equals(" = ")) {
                InfixToPostfixParens converter = new InfixToPostfixParens();
                try{
                    String conv = converter.convert(textField.getText());
                    String Total = Double.toString(evaluatePostfix(conv));
                    textField.setText(Total);
                    Number = true;
                } catch (Exception ex) {
                };
            }
        }
    }

    /**
     * This class adds the number selected to the text field.
     */
    private class numberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String number = e.getActionCommand();
            if(Number) {
                textField.setText(number);
                Number = false;
            } else {
                textField.setText(textField.getText() + number);
            }
        }
    }
}

