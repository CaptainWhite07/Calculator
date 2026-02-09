package calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class CalculatorGUI extends Values implements Process{
    JFrame frame = new JFrame("Calculator");
    JTextField display = new JTextField(input);
    private Timer errorTimer;
    public CalculatorGUI(){
    UIManager.put("Panel.background", new Color(245, 245, 245));
    UIManager.put("Button.background", Color.WHITE);
    UIManager.put("Button.focus", new Color(0, 0, 0, 0));
    this.frame();
    this.display();
    this.buttons();
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }
    private void frame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
    }
    private void display(){
    display.setEditable(false);
    display.setHorizontalAlignment(JTextField.RIGHT);
    display.setFont(new Font("Arial", Font.BOLD, 28)); 
    display.setBackground(new Color(240, 240, 240));
    display.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    frame.add(display, BorderLayout.NORTH);
    }
    private void buttons(){
        JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(5, 4, 4, 4));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    buttonPanel.setBackground(new Color(245, 245, 245));
        String[] buttons = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        ".", "0", "√", "="
        };
        for (byte i = 0; i < buttons.length; i++) {
            String buttonValue = buttons[i];
            JButton button = new JButton(buttonValue);
            button.setFont(new Font("Arial", Font.PLAIN, 37));
            button.setFocusable(false);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), BorderFactory.createEmptyBorder(8, 15, 8, 15)));
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(245, 245, 245));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                }
            });
            buttonPanel.add(button);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    JButton source = (JButton) a.getSource();
                    String value = source.getText();
                    color(value, button);
                    if(value.matches("[0-9]")){
                        if (!getoperatorSwitch()){
                           input+=value;
                           setn(Double.parseDouble(input));
                           display.setText(input);
                        }
                        else{
                            input+=value;
                            setn1(Double.parseDouble(input));
                            display.setText(input);
                            setn2SwitchTrue();
                        }
                    }
                    else{
                        switch (value) {
                            case "√":
                                    try{
                                        parseNumToinput(squareRoot());
                                        display.setText(input);
                                    }
                                    catch(NumberFormatException e){error(e);}
                                    break;
                            case "+/-":
                                parseNumToinput(signFlip());
                                display.setText(input);
                                break;
                            case "%":
                                parseNumToinput(percentage());
                                display.setText(input);
                                break;
                                
                            case ".":
                                if(!input.contains("."))input+=value;
                                display.setText(input);
                                break;
                            
                            case "AC":
                                display.setText("");
                                clear();
                                break;
                            case "=":
                                if(getoperatorSwitch()){
                                    try{
                                        parseNumToinput(calculate());
                                        display.setText(input);
                                        input = "";
                                        setn2SwitchFalse();
                                        setoperatorSwitchFalse();
                                    }
                                    catch(NumberFormatException | ArithmeticException e){error(e);}
                                }
                                break;
                            case "+":
                                try{ 
                                    if (getn2Switch() && getoperatorSwitch()){
                                        calculate();
                                        setoperatorSwitchFalse();
                                    }
                                        setsymbol((byte) 1);
                                    displayOperator(value);
                                }
                                catch(NumberFormatException | ArithmeticException e){error(e);}
                                break;
                                
                            case "-":
                                try{ 
                                    if (getn2Switch() && getoperatorSwitch()){
                                        calculate();
                                        setoperatorSwitchFalse();
                                    }
                                    setsymbol((byte) 2);
                                    displayOperator(value);
                                }
                                catch(NumberFormatException | ArithmeticException e){error(e);}
                                break;

                            case "×":
                                try{ 
                                    if (getn2Switch() && getoperatorSwitch()){
                                        calculate();
                                        setoperatorSwitchFalse();
                                    }
                                    setsymbol((byte) 3);
                                    displayOperator(value);
                                }
                                catch(NumberFormatException | ArithmeticException e){error(e);}
                                break;

                            case "÷":
                                try{ 
                                    if (getn2Switch() && getoperatorSwitch()){
                                        calculate();
                                        setoperatorSwitchFalse();
                                    }
                                    setsymbol((byte) 4);
                                    displayOperator(value);
                                }
                                catch(NumberFormatException | ArithmeticException e){error(e);}
                                    break;
                        }
                    }
                }
            });
        }
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.pack();
    }
    public void error(Throwable eror){
        clear();
        if (errorTimer != null && errorTimer.isRunning()) {errorTimer.stop();}
        display.setForeground(Color.RED);
        display.setText(eror.getMessage());
        errorTimer = new Timer(1000, e -> {display.setForeground(Color.BLACK);});
        errorTimer.setRepeats(false);
        errorTimer.start();
    }
    public void displayOperator(String value){
        if (!input.isEmpty())display.setText(input + " " + value);
        else {display.setText(value);}
        setoperatorSwitchTrue();
        input = "";
    }
    public void color(String value, JButton button){
        if (value.matches("[0-9.]")) {
            button.setBackground(new Color(250, 250, 250));
        } 
        else if (value.matches("[+\\-×÷=]")) {
            button.setBackground(new Color(230, 240, 255));
        } 
        else if (value.equals("AC")) {
        button.setBackground(new Color(255, 230, 230));
        }
    }
}