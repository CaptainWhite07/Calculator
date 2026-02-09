package calculator;
abstract class Values{
    private boolean operatorSwitch;
    private boolean n2Switch;
    private double n;
    private double n1;
    private byte symbol;
    public String input = new String();
    public void clear(){
        this.n = 0;
        this.n1 = 0;
        this.symbol = (byte)0;
        this.operatorSwitch = false;
        this.n2Switch = false;
        this.input = "";
    }
    public double getn(){
        return this.n;
    }
    public void setn(double n){
        this.n = n;
    }
    public double getn1(){
        return n1;
    }
    public void parseNumToinput(double num){
        if ((num == Math.floor(num)) && num < Integer.MAX_VALUE && num > Integer.MIN_VALUE) {
            input = String.valueOf((int)num);
        }
        else input = String.valueOf(num);
    }
    public void setn1(double n1){
        this.n1 = n1;
    }
    public byte getsymbol(){
        return this.symbol;
    }
    public void setsymbol(byte symbol){
        this.symbol = symbol;
    }
    public boolean getoperatorSwitch(){
        return this.operatorSwitch;
    }
    public void setoperatorSwitchTrue(){
        this.operatorSwitch=true;
    }
    public void setoperatorSwitchFalse(){
        this.operatorSwitch = false;
    }
    public boolean getn2Switch(){
        return this.n2Switch;
    }
    public void setn2SwitchTrue(){
        this.n2Switch = true;
    }
    public void setn2SwitchFalse(){
        this.n2Switch = false;
    }
}
interface Process{
    public double getn();
    public void setn(double n);
    public double getn1();
    public void setn1(double n1);
    public byte getsymbol();
    public void setsymbol(byte symbol);
    public void clear();
    public boolean getoperatorSwitch();
    NumberFormatException undefined = new NumberFormatException("Undefined");
    ArithmeticException indeterminate = new ArithmeticException("Indeterminate");
    public default double calculate(){
        if(this.getn()==2&&this.getn1()==2&&this.getsymbol()==1){
            this.setn(1);
        }
        else{
            switch(getsymbol()){
                case 1 -> this.setn(this.getn()+this.getn1());
                case 2 -> this.setn(this.getn()-this.getn1());
                case 3 -> this.setn(this.getn()*this.getn1());
                case 4 -> {
                    if(this.getn1() == 0 && this.getn() == 0) throw indeterminate;
                    else if(this.getn1() == 0)throw undefined;
                    else this.setn(this.getn()/this.getn1());
                }
            }
        }
        return this.getn();
    }
    public default double squareRoot(){
        if(this.getoperatorSwitch()){
            if(this.getn1() < 0){throw undefined;}
            this.setn1(Math.sqrt(this.getn1()));
            return this.getn1();
        }
        else{
            if(this.getn() < 0){throw undefined;}
            this.setn(Math.sqrt(this.getn()));
            return this.getn();
        }
    }
    public default double percentage(){
        if(this.getoperatorSwitch()){
            this.setn1(this.getn1()/100);
            return this.getn1();
        }
        else{
            this.setn(this.getn()/100);
            return this.getn();
        }
    }
    public default Double signFlip(){
        if(this.getoperatorSwitch()){
            this.setn1(this.getn1()*-1);
            return this.getn1();
        }
        else{
            this.setn(this.getn()*-1);
            return this.getn();
        }
    }
}