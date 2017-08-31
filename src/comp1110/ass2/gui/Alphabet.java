package comp1110.ass2.gui;

/**
 * Created by luoxi on 31/08/2017.
 */
public enum Alphabet {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int id;

    Alphabet(int id){
        this.id = id;
    }

    int getId(){
        return id;
    }
}
