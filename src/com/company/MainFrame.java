package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private static final double e = 2.718;
    private JLabel formulaLabel;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMemory;
    private ButtonGroup radioButtonsVar = new ButtonGroup();
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariable = Box.createHorizontalBox();
    private int formulaId = 1;
    private int variableId = 1;
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;

    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.log((1 + x)*(1 + x)) + Math.cos(3.14 * z * z * z), Math.sin(y)) + Math.pow(Math.pow(e,x*x)+ Math.cos(Math.pow(e,z))+Math.pow(1/y,0.5),1/x);
    }
    public Double calculate2(Double x, Double y, Double z) {
        return Math.pow(Math.cos(3.14 * x * x * x) + Math.log((1 + y)*(1 + y)), 1/4) * (Math.pow(e, z * z) + Math.pow(1/x, 0.5) + Math.cos(Math.pow(e, y)));
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                if(formulaId == 1){
                    ImageIcon icon = new ImageIcon("C:\\java\\labs\\lab2\\res\\1.png");
                    formulaLabel.setIcon(icon);
                }else{
                    ImageIcon icon = new ImageIcon("C:\\java\\labs\\lab2\\res\\2.png");
                    formulaLabel.setIcon(icon);
                }
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButtonVar(String buttonName, final int variableId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.variableId = variableId;
                if (variableId == 1)	textFieldMemory.setText(mem1.toString());
                if (variableId == 2)	textFieldMemory.setText(mem2.toString());
                if (variableId == 3)	textFieldMemory.setText(mem3.toString());
            }
        });
        radioButtonsVar.add(button);
        hboxVariable.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        Box image = Box.createHorizontalBox();
        image.add(Box.createHorizontalGlue());
        formulaLabel = new JLabel("");
        image.add(formulaLabel);
        image.add(Box.createHorizontalGlue());
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        //boxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        // hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        //hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariable.add(Box.createHorizontalGlue());
        addRadioButtonVar("Переменная 1", 1);
        addRadioButtonVar("Переменная 2", 2);
        addRadioButtonVar("Переменная 3", 3);
        radioButtonsVar.setSelected(
                radioButtonsVar.getElements().nextElement().getModel(), true);
        hboxVariable.add(Box.createHorizontalGlue());
        hboxVariable.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JLabel labelForMemory = new JLabel("Memory:");
        textFieldMemory = new JTextField("0", 15);
        textFieldMemory.setMaximumSize(textFieldMemory.getPreferredSize());
        JButton sum = new JButton("M+");
        sum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Double result = Double.parseDouble(textFieldResult.getText());
                    if (variableId == 1){
                        mem1 += result;
                        textFieldMemory.setText(mem1.toString());
                    }else if(variableId ==2){
                        mem2 += result;
                        textFieldMemory.setText(mem2.toString());
                    }else{
                        mem3 += result;
                        textFieldMemory.setText(mem3.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (variableId == 1){
                    mem1 = 0.0;
                    textFieldMemory.setText("0.0");
                }else if(variableId ==2){
                    mem2 = 0.0;
                    textFieldMemory.setText("0.0");
                }else{
                    mem3 = 0.0;
                    textFieldMemory.setText("0.0");
                }
            }
        });
        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(MC);
        hboxMemory.add(Box.createHorizontalStrut(30));
        hboxMemory.add(sum);
        hboxMemory.add(Box.createHorizontalStrut(30));
        hboxMemory.add(labelForMemory);
        hboxMemory.add(textFieldMemory);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.setBorder(
                BorderFactory.createLineBorder(Color.BLACK));
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(image);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxVariable);
        contentBox.add(hboxMemory);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon("C:\\java\\labs\\lab2\\res\\1.png");
        formulaLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}