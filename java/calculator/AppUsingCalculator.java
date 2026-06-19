import calculator.cal_oper;

public class AppUsingCalculator{
    public static void main (String[] args){
        cal_oper cal = new cal_oper();
        int first = 50;
        int second = 10;
        System.out.println("Calculator main test");
        System.out.println("test add: " + first + " + " + second + " = " + cal.add2(first, second));
        System.out.println("test sub: " + first + " - " + second + " = " + cal.sub2(first, second));
        System.out.println("test mul: " + first + " * " + second + " = " + cal.mul2(first, second));
        System.out.println("test sub: " + first + " / " + second + " = " + cal.div2(first, second));
    }
}