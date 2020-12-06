public class Sign {
    private Character sign;
    private int lvl;

    public Sign() {
        lvl = 99;
    }

    public Sign(Character sign) {
        this.sign = sign;
        switch (this.sign) {
            case '+': case '-':
                lvl = 2;
                break;
            case '*': case '/':
                lvl = 1;
                break;
            case '(':
                lvl = 3;
                break;
            case ')':
                lvl = 4;
                break;
        }
    }

    public Character getSign() {
        return sign;
    }

    public void setSign(Character sign) {
        this.sign = sign;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
