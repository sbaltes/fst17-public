package de.unitrier.st.fst17.list;

public class Main {
    public static void main(String[] args) {

        // Liste erzeugen und einige Werte eintragen
        List list = new List();
        list.append(new double[]{0.5, 4.2, 3.3, 0.9});

        System.out.println("\nDie Liste enthält folgende Elemente:");
        list.print();
    }
}
