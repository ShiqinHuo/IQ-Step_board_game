package comp1110.ass2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by luoxi on 31/08/2017.
 */
public enum Alphabet {
    A(0) , B(1) , C(2) , D(3) , E(4) , F(5) , G(6) , H(7) , I(8) , J(9),
    K(10), L(11), M(12), N(13), O(14), P(15), Q(16), R(17), S(18), T(19),
    U(20), V(21), W(22), X(23), Y(24), a(25), b(26), c(27), d(28), e(29),
    f(30), g(31), h(32), i(33), j(34), k(35), l(36), m(37), n(38), o(39),
    p(40), q(41), r(42), s(43), t(44), u(45), v(46), w(47), x(48), y(49);

    private final int id;

    Alphabet(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static boolean isPeg(int pos){
        if(pos < 10) return pos % 2 == 0;
        else if(pos < 20) return pos % 2 == 1;
        else if(pos < 30) return pos % 2 == 0;
        else if(pos < 40) return pos % 2 == 1;
        else return pos % 2 == 0;
    }

}


