package Lab_1;

public class Polynomial {
    private Term firstTerm;

    // create a polynomial
    public Polynomial(Term firstTerm) {
        this.firstTerm = firstTerm;
    }

    // get the first term
    public Term getFirst() {
        return firstTerm;
    }

    // set the first term
    public void setFirst(Term first) {
        this.firstTerm = first;
    }

    // add a single term to the polynomial
    public void addTerm(Term term) {
        if (firstTerm == null) {
            firstTerm = term;
            return;
        }
        if(term == null){
            return;
        }
        else if (term.getExponent() >= firstTerm.getExponent()) {
            if (term.getExponent() == firstTerm.getExponent()) {
                firstTerm.setCoefficient(firstTerm.getCoefficient() + term.getCoefficient());
                return;
            } else {
                term.setNext(firstTerm);
                firstTerm = term;
                return;
            }
        }
        Term result = firstTerm;
        while (result.getNext() != null) {
            Term next = result.getNext();
            if (term.getExponent() >= next.getExponent()) {
                if (term.getExponent() == next.getExponent()) {
                    next.setCoefficient(next.getCoefficient() + term.getCoefficient());
                    return;
                }
                else{
                    term.setNext(next);
                    result.setNext(term);
                    return;
                }
            }
            else result = result.getNext();
        }
        result.setNext(term);
    }

    // add another polynomial, return the sum
    public Polynomial add(Polynomial another) {
        Term temp = getFirst();
        Term temp_2 = another.getFirst();
        Polynomial result = new Polynomial(new Term(temp.getCoefficient(), temp.getExponent()));
        temp = temp.getNext();
        while (temp != null) {
            result.addTerm(new Term(temp.getCoefficient(), temp.getExponent()));
            temp = temp.getNext();
        }
        while (temp_2 != null) {
            result.addTerm(new Term(temp_2.getCoefficient(), temp_2.getExponent()));
            temp_2 = temp_2.getNext();
        }
        return result;
    }

    // convert to string representation
    // example: 4.0x^3+3.2x^2-2.1x^1+1.0x^0
    // example: -12.0x^9-1.0x^7+3.0x^5+10.0x^2+5.0x^0
    public String toString() {
        Term temp = getFirst();
        String str = "";
        while (temp.getNext() != null) {
            str = str.concat(String.valueOf(temp.getCoefficient()));
            str = str.concat("x^");
            str = str.concat(String.valueOf(temp.getExponent()));
            if (temp.getNext().getCoefficient() > 0) {
                str = str.concat("+");
            }
            temp = temp.getNext();
        }
        str = str.concat(String.valueOf(temp.getCoefficient()));
        str = str.concat("x^");
        str = str.concat(String.valueOf(temp.getExponent()));
        return str;
    }

    // write your own code to test your implementation
    public static void main(String[] args) {
        Polynomial temp_1 = new Polynomial(new Term(4, 3));
        temp_1.addTerm(new Term(2.2, 2));
        temp_1.addTerm(new Term(-2.1, 1));
        temp_1.addTerm(new Term(1.0, 0));
        temp_1.addTerm(new Term(1.0, 2));
        String str_1 = temp_1.toString();

        Polynomial temp_2 = new Polynomial(new Term(-12.0, 9));
        temp_2.addTerm(new Term(3.0, 5));
        temp_2.addTerm(new Term(10.0, 2));
        temp_2.addTerm(new Term(5.0, 0));
        temp_2.addTerm(new Term(-1.0, 7));
        String str_2 = temp_2.toString();

        String str_3 = temp_1.add(temp_2).toString();

        System.out.println("( " + str_1 + " ) + ( " + str_2 + " ) = ( " + str_3 + " )");

        Polynomial temp_3 = new Polynomial(new Term(-4.0, 5));
        temp_3.addTerm(new Term(4.4, 3));
        temp_3.addTerm(new Term(-8.0, 9));
        String str_4 = temp_3.toString();

        Polynomial temp_4 = new Polynomial(new Term(5.3, 9));
        temp_4.addTerm(new Term(-0.5, 17));
        temp_4.addTerm(new Term(2.9, 5));
        temp_4.addTerm(new Term(-1.0, 0));
        String str_5 = temp_4.toString();

        String str_6 = temp_4.add(temp_3).toString();

        System.out.println("( " + str_4 + " ) + ( " + str_5 + " ) = ( " + str_6 + " )");
    }
}

