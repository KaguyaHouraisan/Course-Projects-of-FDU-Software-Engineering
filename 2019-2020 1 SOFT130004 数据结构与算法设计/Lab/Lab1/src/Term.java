package Lab_1;

public class Term {
    private double coef;
    private int exp;
    private Term next;

    // create a term
    public Term(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
        this.next = null;
    }

    // get the value of the coefficient
    public double getCoefficient() {
        return coef;
    }

    // set the value of the coefficient
    public void setCoefficient(double coef) {
        this.coef = coef;
    }

    // get the value of the exponent
    public int getExponent() {
        return exp;
    }

    // set the value of the exponent
    public void setExponent(int exp) {
        this.exp = exp;
    }

    // get the following term
    public Term getNext() {
        return next;
    }

    // set the following term
    public void setNext(Term next) {
        this.next = next;
    }
}

