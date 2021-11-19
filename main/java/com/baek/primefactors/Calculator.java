package com.baek.primefactors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Calculator extends JFrame{
    String num = "";
    Double number;
    JPanel panel = new JPanel();
    JButton bt[] = new JButton[19];
    JButton calc = new JButton("=");
    JLabel exp = new JLabel("\n");
    JLabel results = new JLabel("\n");
    List<Double> numbers = new ArrayList<>();
    List<String> operators = new ArrayList<>();

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
            bt[i].addKeyListener(new MyKeyListener());
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

            if(btn.equals(bt[3])) { // 나누기
                numbers.add((double)Integer.parseInt(num));
                operators.add(btn.getText());
                num = "";
            }
            else if(btn.equals(bt[7])) {   // 곱하기
                numbers.add((double)Integer.parseInt(num));
                operators.add(btn.getText());
                num = "";
            }
            else if(btn.equals(bt[11])) {   // 빼기
                numbers.add((double)Integer.parseInt(num));
                operators.add(btn.getText());
                num = "";
            }
            else if(btn.equals(bt[15])) {    // 더하기
                numbers.add((double)Integer.parseInt(num));
                operators.add(btn.getText());
                num = "";
            }
            else if(btn.equals(bt[18])) {    // 지우기
                exp.setText("\n");
                results.setText("\n");
                numbers.clear();
                operators.clear();
                num = "";
            }
            else
                num = num.concat(btn.getText());
        }
    }
    class CalcActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            numbers.add((double)Integer.parseInt(num));
            int i = 0;
            double sum=0;
            for(i=0; i< operators.size();) {
                String a = operators.get(i);
                if(a.equals("/")){
                    double n1 = numbers.get(i);
                    double n2 = numbers.get(i+1);
                    sum = n1/n2;
                    numbers.remove(i+1);
                    numbers.add(i,sum);
                    numbers.remove(i+1);
                    operators.remove(i);
                    i = 0;
                }
                if(a.equals("*")){
                    double n1 = numbers.get(i);
                    double n2 = numbers.get(i+1);
                    sum = n1*n2;
                    numbers.remove(i+1);
                    numbers.add(i,sum);
                    numbers.remove(i+1);
                    operators.remove(i);
                    i=0;
                }
                else {
                    i++;
                    if(i >= operators.size())
                        break;
                }
            }
            i = 0;
            while(operators.size()>0){
                String a = operators.get(i);
                if(a.equals("+")){
                    double n1 = numbers.get(i);
                    double n2 = numbers.get(i+1);
                    sum = n1+n2;
                    numbers.remove(i+1);
                    numbers.add(i,sum);
                    numbers.remove(i+1);
                    operators.remove(i);
                }
                if(a.equals("-")){
                    double n1 = numbers.get(i);
                    double n2 = numbers.get(i+1);
                    sum = n1-n2;
                    numbers.remove(i+1);
                    numbers.add(i,sum);
                    numbers.remove(i+1);
                    operators.remove(i);
                }
            }
            double dap = numbers.get(0);

            results.setText(Double.toString(dap));
        }
    }
    class MyKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            JButton btn = (JButton)e.getSource();
            btn.requestFocus();
            exp.setForeground(Color.GRAY);
            exp.setText(exp.getText()+Character.toString(e.getKeyChar()));
            if(e.getKeyChar() == '/') {
                number = (double)Integer.parseInt(num);
                numbers.add(number);
                operators.add(Character.toString(e.getKeyChar()));
                num = "";
            }
            else if((e.getKeyChar() == '*')) {
                number = (double)Integer.parseInt(num);
                numbers.add(number);
                operators.add(Character.toString(e.getKeyChar()));
                num = "";
            }
            else if((e.getKeyChar() == '-')) {
                number = (double)Integer.parseInt(num);
                numbers.add(number);
                operators.add(Character.toString(e.getKeyChar()));
                num = "";
            }
            else if((e.getKeyChar() == '+')) {
                number = (double)Integer.parseInt(num);
                numbers.add(number);
                operators.add(Character.toString(e.getKeyChar()));
                num = "";
            }
            else if((e.getKeyChar() == '=')) {
                number = (double)Integer.parseInt(num);
                numbers.add(number);
                int i = 0;
                double sum=0;
                for(i =0; i< operators.size();) {
                    String a = operators.get(i);
                    if(a.equals("/")){
                        double n1 = numbers.get(i);
                        double n2 = numbers.get(i+1);
                        sum = n1/n2;
                        numbers.remove(i+1);
                        numbers.add(i,sum);
                        numbers.remove(i+1);
                        operators.remove(i);
                        i = 0;
                    }
                    if(a.equals("*")){
                        double n1 = numbers.get(i);
                        double n2 = numbers.get(i+1);
                        sum = n1*n2;
                        numbers.remove(i+1);
                        numbers.add(i,sum);
                        numbers.remove(i+1);
                        operators.remove(i);
                        i = 0;
                    }
                    else {
                        i++;
                        if(i>= operators.size())
                            break;
                    }
                }
                i=0;
                while(numbers.size()!=1){
                    String a = operators.get(i);
                    if(a.equals("+")){
                        double n1 = numbers.get(i);
                        double n2 = numbers.get(i+1);
                        sum = n1+n2;
                        numbers.remove(i+1);
                        numbers.add(i,sum);
                        numbers.remove(i+1);
                        operators.remove(i);
                    }
                    if(a.equals("-")){
                        double n1 = numbers.get(i);
                        double n2 = numbers.get(i+1);
                        sum = n1-n2;
                        numbers.remove(i+1);
                        numbers.add(i,sum);
                        numbers.remove(i+1);
                        operators.remove(i);
                    }
                }
                double dap = numbers.get(0);
                results.setText(Double.toString(dap));
            }
            else
                num = num.concat(Character.toString(e.getKeyChar()));
        }

    }

    public static void main(String[] args) {
        new Calculator();
    }
}

