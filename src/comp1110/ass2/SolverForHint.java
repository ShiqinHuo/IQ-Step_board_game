package comp1110.ass2;

import java.util.*;

/**
 * Created by wenjunyang on 8/10/17.
 */
public class SolverForHint {
    /**
     * A solver for the game (plays the same role as task9)
     * Given a start point placement and return a set which contains all the possible solution
     * @param placement A startPoints for the game
     * @return A set which contains all the possible solutions for the input placement
     */
    public static Set<String> Solutions(String placement){
        ArrayList<String> A = MaskGenerator.maskGenerator1('A'); ArrayList<String> B = MaskGenerator.maskGenerator2();
        ArrayList<String> C = MaskGenerator.maskGenerator1('C'); ArrayList<String> D = MaskGenerator.maskGenerator1('D');
        ArrayList<String> E = MaskGenerator.maskGenerator3(); ArrayList<String> F = MaskGenerator.maskGenerator1('F');
        ArrayList<String> G = MaskGenerator.maskGenerator1('G'); ArrayList<String> H = MaskGenerator.maskGenerator1('H');

        ArrayList<Character> firstAlphabet = new ArrayList<>();

        for (int i = 0; i < placement.length()/3; i++){
            firstAlphabet.add(placement.charAt(3*i));
        }

        Map<Character,ArrayList<String>> newMap = new HashMap<>();
        newMap.put('A',A);newMap.put('B',B);newMap.put('C',C);newMap.put('D',D);newMap.put('E',E);newMap.put('F',F);newMap.put('G',G);newMap.put('H',H);

        Set<Character> key = newMap.keySet();

        for (int i = 0; i < firstAlphabet.size(); i++){
            key.remove(firstAlphabet.get(i));
        }

        ArrayList<Character> keyList = new ArrayList<>(key);

        Set<String> orders = new HashSet<>();
        if (key.size() == 0){
            orders.add(placement);
            return orders;
        }else if (key.size() == 1){
            ArrayList<String> one = new ArrayList<>();
            String process;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process);
                }
            }
            Set<String> two = new HashSet<>(one);
            return two;
        }else if (key.size() == 2){
            ArrayList<String> one = new ArrayList<>();
            Set<String> two = new HashSet<>();
            String process1;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process1 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process1);
                }
                for (String b : one){
                    for (int j = 0; j < newMap.get(keyList.get(1)).size(); j++){
                        if (StepsGame.notObstruct(b,newMap.get(keyList.get(1)).get(j))){
                            b = b + newMap.get(keyList.get(1)).get(j);
                            two.add(b);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            Set<String> four = new HashSet<>();
            String process2;

            if (two.isEmpty()){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                        process2 = placement + newMap.get(keyList.get(1)).get(i);
                        three.add(process2);
                    }
                    for (String b : three){
                        for (int j = 0; j < newMap.get(keyList.get(0)).size(); j++){
                            if (StepsGame.notObstruct(b,newMap.get(keyList.get(0)).get(j))){
                                b = b + newMap.get(keyList.get(0)).get(j);
                                four.add(b);
                            }
                        }
                    }
                }
            }
            if (two.isEmpty()){
                return four;
            }else {
                return two;
            }
        }else if (key.size() == 3){
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }


            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }


            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            return StepsGame.afterSort(three);
        }else if (key.size() == 4) {
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2; String process3;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process8;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process8 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process8)){
                            two.add(process8);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12; String process13;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process13 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process13)){
                            three.add(process13);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process15; String process16; String process17; String process18;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process15 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process15)){
                            four.add(process15);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process16 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process16)){
                            four.add(process16);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process17 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process17)){
                            four.add(process17);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process18 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process18)){
                            four.add(process18);
                        }
                    }
                }
            }
            return StepsGame.afterSort(four);
        }else if (key.size() == 5) {
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2; String process3; String process4;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(4)).get(i))){
                    process4 = placement + newMap.get(keyList.get(4)).get(i);
                    one.add(process4);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process8; String process9;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process8 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process8)){
                            two.add(process8);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process9 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process9)){
                            two.add(process9);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12; String process13; String process14;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process13 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process13)){
                            three.add(process13);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process14 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process14)){
                            three.add(process14);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process15; String process16; String process17; String process18; String process19;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process15 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process15)){
                            four.add(process15);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process16 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process16)){
                            four.add(process16);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process17 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process17)){
                            four.add(process17);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process18 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process18)){
                            four.add(process18);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process19 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process19)){
                            four.add(process19);
                        }
                    }
                }
            }

            ArrayList<String> five = new ArrayList<>();
            String process20; String process21; String process22; String process23; String process24;
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process20 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process20)){
                            five.add(process20);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process21 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process21)){
                            five.add(process21);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process22 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process22)){
                            five.add(process22);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process23 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process23)){
                            five.add(process23);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process24 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process24)){
                            five.add(process24);
                        }
                    }
                }
            }
            return StepsGame.afterSort(five);
        } else if (key.size() == 6) {
            ArrayList<String> one = new ArrayList<>();
            String process0; String process1; String process2; String process3; String process4; String process100;
            for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(0)).get(i))){
                    process0 = placement + newMap.get(keyList.get(0)).get(i);
                    one.add(process0);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(1)).get(i))){
                    process1 = placement + newMap.get(keyList.get(1)).get(i);
                    one.add(process1);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(2)).get(i))){
                    process2 = placement + newMap.get(keyList.get(2)).get(i);
                    one.add(process2);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(3)).get(i))){
                    process3 = placement + newMap.get(keyList.get(3)).get(i);
                    one.add(process3);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(4)).get(i))){
                    process4 = placement + newMap.get(keyList.get(4)).get(i);
                    one.add(process4);
                }
            }
            for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                if (StepsGame.notObstruct(placement,newMap.get(keyList.get(5)).get(i))){
                    process100 = placement + newMap.get(keyList.get(5)).get(i);
                    one.add(process100);
                }
            }

            ArrayList<String> two = new ArrayList<>();
            String process5; String process6; String process7; String process8; String process9; String process101;
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process5 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process5)){
                            two.add(process5);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process6 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process6)){
                            two.add(process6);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process7 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process7)){
                            two.add(process7);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process8 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process8)){
                            two.add(process8);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process9 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process9)){
                            two.add(process9);
                        }
                    }
                }
            }
            for (String s : one){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process101 = s + newMap.get(keyList.get(5)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process101)){
                            two.add(process101);
                        }
                    }
                }
            }

            ArrayList<String> three = new ArrayList<>();
            String process10; String process11; String process12; String process13; String process14; String process102;
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process10 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process10)){
                            three.add(process10);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process11 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process11)){
                            three.add(process11);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process12 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process12)){
                            three.add(process12);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process13 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process13)){
                            three.add(process13);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process14 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process14)){
                            three.add(process14);
                        }
                    }
                }
            }
            for (String s : two){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process102 = s + newMap.get(keyList.get(5)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process102)){
                            three.add(process102);
                        }
                    }
                }
            }

            ArrayList<String> four = new ArrayList<>();
            String process15; String process16; String process17; String process18; String process19; String process103;
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process15 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process15)){
                            four.add(process15);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process16 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process16)){
                            four.add(process16);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process17 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process17)){
                            four.add(process17);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process18 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process18)){
                            four.add(process18);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process19 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process19)){
                            four.add(process19);
                        }
                    }
                }
            }
            for (String s : three){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process103 = s + newMap.get(keyList.get(5)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process103)){
                            four.add(process103);
                        }
                    }
                }
            }

            ArrayList<String> five = new ArrayList<>();
            String process20; String process21; String process22; String process23; String process24; String process104;
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process20 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process20)){
                            five.add(process20);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process21 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process21)){
                            five.add(process21);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process22 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process22)){
                            five.add(process22);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process23 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process23)){
                            five.add(process23);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process24 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process24)){
                            five.add(process24);
                        }
                    }
                }
            }
            for (String s : four){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process104 = s + newMap.get(keyList.get(5)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process104)){
                            five.add(process104);
                        }
                    }
                }
            }

            ArrayList<String> six = new ArrayList<>();
            String process25; String process26; String process27; String process28; String process29; String process105;
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(0)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(0)).get(i))){
                        process25 = s + newMap.get(keyList.get(0)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process25)){
                            six.add(process25);
                        }
                    }
                }
            }
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(1)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(1)).get(i))){
                        process26 = s + newMap.get(keyList.get(1)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process26)){
                            six.add(process26);
                        }
                    }
                }
            }
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(2)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(2)).get(i))){
                        process27 = s + newMap.get(keyList.get(2)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process27)){
                            six.add(process27);
                        }
                    }
                }
            }
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(3)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(3)).get(i))){
                        process28 = s + newMap.get(keyList.get(3)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process28)){
                            six.add(process28);
                        }
                    }
                }
            }
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(4)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(4)).get(i))){
                        process29 = s + newMap.get(keyList.get(4)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process29)){
                            six.add(process29);
                        }
                    }
                }
            }
            for (String s : five){
                for (int i = 0; i < newMap.get(keyList.get(5)).size(); i++){
                    if (StepsGame.notObstruct(s,newMap.get(keyList.get(5)).get(i))){
                        process105 = s + newMap.get(keyList.get(5)).get(i);
                        if (StepsGame.isPlacementSequenceValid(process105)){
                            six.add(process105);
                        }
                    }
                }
            }
            return StepsGame.afterSort(six);
        } else {
            return null;
        }
    }
}
