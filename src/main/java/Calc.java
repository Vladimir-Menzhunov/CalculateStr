import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    private String str;
    Stack<Integer> num = new Stack<Integer>();
    Stack<Sign> sign = new Stack<Sign>();

    public Calc(String str) {
        this.str = "(" + str.replaceAll(" ", "") + ")";
    }

    public double calculateStr() {
        Double result = 0.0;

        Stack<Double> num = new Stack<Double>();
        Stack<Sign> sign = new Stack<Sign>();

        Sign prev = new Sign();

        Matcher regStr = Pattern.compile("([-+*/]?)*\\(*[0-9]+[-+*/]?\\)*([+-/*]?)*").matcher(str);

        ArrayList<String> list = new ArrayList<String>();
        while (regStr.find()) {
            list.add(regStr.group(0));
        }

        for (String regSplit : list) {


            Matcher braces = Pattern.compile("\\(+").matcher(regSplit);
            if (braces.find()) {
                for (Character signChar : braces.group(0).toCharArray())
                    sign.push(new Sign(signChar));
                prev = new Sign('(');
                regSplit = braces.replaceAll("");
            }

            braces = Pattern.compile("[0-9]+").matcher(regSplit);
            if (braces.find()) {
                Double numOne = Double.parseDouble(braces.group(0));
                num.push(numOne);
                regSplit = braces.replaceAll("");
            }

            braces = Pattern.compile("[+-/*]?").matcher(regSplit);
            if(braces.matches()) {
                Sign simbol = new Sign(braces.group(0).charAt(0));
                if(prev.getLvl() <= simbol.getLvl() && prev.getSign() != '(' && simbol.getSign() != '(' ) {
                    Double oneNum = num.pop();
                    Double twoNum = num.pop();
                    Character si = sign.pop().getSign();
                    num.push(calculate(oneNum, twoNum, si));
                }
                sign.push(simbol);
                regSplit = braces.replaceAll("");
                prev = simbol;
            }
            braces = Pattern.compile("\\)+([+-/*]?)*").matcher(regSplit);
            if (braces.find()) {
                for (Character signChar : braces.group(0).toCharArray()) {
                    if(!signChar.equals(')')) {
                        prev = new Sign(signChar);
                        sign.push(prev);
                        break;
                    }
                    Character ch = sign.pop().getSign();
                    Double oneNum;
                    Double twoNum;
                    while(!ch.equals('(')) {
                        oneNum = num.pop();
                        twoNum = num.pop();
                        num.push(calculate(oneNum, twoNum, ch));
                        ch = sign.pop().getSign();
                    }
                }

            }
        }

        return num.pop();
    }

    private Double calculate(Double oneNum, Double twoNum, Character simbol) {

        switch (simbol) {
            case '+':
                return oneNum + twoNum;
            case '-':
                    return twoNum - oneNum;
            case '*':
                return oneNum * twoNum;
            case '/':
                return twoNum / oneNum;
            default: return -1.;
        }
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
