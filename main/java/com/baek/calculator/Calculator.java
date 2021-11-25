package com.baek.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame{
    JPanel panel = new JPanel();
    JButton bt[] = new JButton[19];
    JButton calc = new JButton("=");
    JLabel exp = new JLabel("\n");
    JLabel results = new JLabel("\n");

    public Calculator() {
        setTitle("20172099 여지원");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(2,1));
        panel.add(exp);
        panel.add(results);
        results.setHorizontalAlignment(SwingConstants.RIGHT);
        exp.setHorizontalAlignment(SwingConstants.RIGHT);

        c.add(panel, BorderLayout.NORTH);
        Container num = new Container();
        num.setLayout(new GridLayout(5,4));
        String e = "  \t";
        String op[] = {e,e,e,"/","7","8","9","*","4","5","6","-","1","2","3","+",e,"0","C"};

        for(int i =0; i<bt.length; i++) {
            bt[i] = new JButton(op[i]);
            num.add(bt[i]);
            bt[i].addActionListener(new MyActionListener());
        }
        calc.addActionListener(new CalcActionListener());
        num.add(calc);
        c.add(num,BorderLayout.CENTER );
        setSize(300,350);
        setVisible(true);

    }

    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton)e.getSource();
            exp.setForeground(Color.GRAY);
            exp.setText(exp.getText()+btn.getText());

            if(btn.equals(bt[18])) {    // 지우기
                exp.setText("\n");
                results.setText("\n");
            }
        }
    }
    class CalcActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            double result = new MyCalc(exp.getText()).calculate();
            results.setText(Double.toString(result));
            exp.setText(Double.toString(result));
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}

