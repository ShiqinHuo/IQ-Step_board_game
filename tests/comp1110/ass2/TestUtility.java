package comp1110.ass2;

import java.util.Random;

public class TestUtility {
    static final int BASE_ITERATIONS = 100;
    static final int PIECES = 8;
    static final int ORIENTATIONS = 8;
    static final char BAD = Character.MAX_VALUE - 'z';

    static final String[] PLACEMENTS = {
            "BFMCGOHHnGAkFGQDBiAAgEDI",
            "HBLADgDFOCEnGGQEDIFElBAi",
            "HBLADgBHnCGOGElFGQEDIDAi",
            "BFMAFnDFOGAkFGQCAiEDIHCg",
            "CGOBFqGDLAGjHEQDFSFHlEAo",
            "EBxFCLAESGEQHBNCAkDBiBBg",
    };

    static final String[][] VIABLE1 = {
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCL", "AES"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCLAES", "GEQ"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCLAESGEQ", "HBN"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCLAESGEQHBN", "CAk"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCLAESGEQHBNCAk", "DBi"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBxFCLAESGEQHBNCAkDBi", "BBg"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFq", "GDL"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFqGDL", "AGj"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFqGDLAGj", "HEQ"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFqGDLAGjHEQ", "DFS"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFqGDLAGjHEQDFS", "FHl"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGOBFqGDLAGjHEQDFSFHl", "EAo"},
    };
    static final String[][] VIABLE2 = {
            {"BFMCGOHHnGAkFGQDBiAAgEDI", "", "BFM", "HHn"},
            {"CGOBFqGDLAGjHEQDFSFHlEAo", "CGO", "BFq", "GDL"},
            {"EBxFCLAESGEQHBNCAkDBiBBg", "EBx", "FCL", "AES"},
            {"BFMAFnDFOGAkFGQCAiEDIHCg", "BFM", "AFn", "DFO"},
            {"BFMAFnDFOGAkFGQCAiEDIHCg", "BFMAFnDFOGAk", "FGQ", "CAi"},
            {"BFMAFnDFOGAkFGQCAiEDIHCg", "BFMAFnDFOGAkFGQ", "CAi", "EDI"}
    };
    static final String[][] VIABLE3 = {
            {"HBLADgDFOCEnGGQEDIFElBAi", "", "HBL", "DFO", "CEn"},
            {"HBLADgDFOCEnGGQEDIFElBAi", "HBL", "ADg", "DFO", "CEn"},
            {"HBLADgBHnCGOGElFGQEDIDAi", "", "HBL", "BHn", "CGO"},
            {"HBLADgBHnCGOGElFGQEDIDAi", "HBL", "ADg", "BHn", "CGO"},
    };
    final static String[] GOOD_PAIRS = {
            "CGOBFq",
            "BFMAFn",
            "HBLADg",
            "DFOCEn",
            "BFMCGO",
            "AAgEDI",
            "FElBAi",
            "EDIHCg",
            "FHlEAo",
            "EDIDAi"
    };

    final static String[] BAD_PAIRS = {
            "OEASIJ",
            "OEAHDE",
            "OEACFE",
            "OEAJBG",
            "OEAHKD",
            "OEAHHB",
            "OEAPGG",
            "OEACJK",
            "OEAGKD"
    };

    final static String[][] SOLUTIONS_ONE = {
            { "ADgBAiCEnDFOEDIFElGGQ", "ADgBAiCEnDFOEDIFElGGQHBL" },
            { "ADgBAiCEnDFOEDIFEl", "ADgBAiCEnDFOEDIFElGGQHBL" },
            { "AESBBgCAkDBiEBxFCLGEQ", "AESBBgCAkDBiEBxFCLGEQHBN" },
            { "AESBBgCAkDBiEBxFCL", "AESBBgCAkDBiEBxFCLGEQHBN" },
            { "ADgBHnCGODAiEDIFGQGEl", "ADgBHnCGODAiEDIFGQGElHBL" },
            { "ADgBHnCGODAiEDIFGQ", "ADgBHnCGODAiEDIFGQGElHBL" },
            { "AGjBFqCGODFSEAoFHlGDL", "AGjBFqCGODFSEAoFHlGDLHEQ" },
    };

    final static String[][] SOLUTIONS_MULTI = {
            {"AFnBFMCAiDFOEDIFGQGAk", "AFnBFMCAiDFOEDIFGQGAkHCg", "AFnBFMCAiDFOEDIFGQGAkHDg"},
            {"AFnBFMCAiDFOEDIFGQ", "AFnBFMCAiDFOEDIFGQGAgHEl", "AFnBFMCAiDFOEDIFGQGCgHEl", "AFnBFMCAiDFOEDIFGQGAkHCg", "AFnBFMCAiDFOEDIFGQGAkHDg"},
            {"AGjBFqCGODFSEAoFHl", "AGjBFqCGODFSEAoFHlGALHEQ", "AGjBFqCGODFSEAoFHlGDLHEQ", "AGjBFqCGODFSEAoFHlGCLHEQ", "AGjBFqCGODFSEAoFHlGBLHEQ"},
            {"AAgBFMCGODBiEDIFGQ", "AAgBFMCGODBiEDIFGQGElHGn", "AAgBFMCGODBiEDIFGQGAkHGn", "AAgBFMCGODBiEDIFGQGAkHHn"},
            {"ADgBAiCEnDFOEDI", "ADgBAiCEnDFOEDIFElGGQHBL", "ADgBAiCEnDFOEDIFGQGElHBL"},
            {"AAgBFMCGODBiEDIFGQ", "AAgBFMCGODBiEDIFGQGElHGn", "AAgBFMCGODBiEDIFGQGAkHGn", "AAgBFMCGODBiEDIFGQGAkHHn"}
     };


    static final char[] CENTRAL_PEGS = {'L', 'N', 'P', 'R', 'W', 'Y', 'b', 'g', 'i', 'k', 'm'};
    static final char[] CENTRAL_NONPEGS = {'M', 'O', 'Q', 'V', 'X', 'a', 'c', 'h', 'j', 'l'};

    static char randomShape(Random r) {
        return (char) ('A' + r.nextInt(8));
    }

    static char randomOrientation(Random r) {
        return (char) ('A' + r.nextInt(8));
    }

    static char randomLocation(Random r) {
        int l = r.nextInt(50);
        if (l < 25)
            return (char) ('A' + l);
        else
            return (char) ('a' + l - 25);
    }

    static String shufflePlacement(String placement) {
        Random r = new Random();
        int pieces = placement.length() / 3;
        if (pieces == 1) return placement;

        int order[] = new int[pieces];
        for (int i = 1; i < pieces; i++) {
            int slot = r.nextInt(pieces - 1);
            while (order[slot] != 0) slot = (slot + 1) % pieces;
            order[slot] = i;
        }

        String shuffled = "";
        for (int i = 0; i < pieces; i++) {
            shuffled += placement.substring(3 * order[i], 3 * (order[i] + 1));
        }
        return shuffled;
    }

    static String badlyFormedPiecePlacement(Random r) {
        char a = randomShape(r);
        char bada = (char) ('A' + PIECES + r.nextInt(BAD));
        char b = randomOrientation(r);
        char badb = (char) ('A' + (ORIENTATIONS) + r.nextInt(BAD));
        char c = randomLocation(r);
        char badc = (char) ('Z' + r.nextInt('a'-'Z'));

        String test = "";
        switch (r.nextInt(4)) {
            case 0:
                test += "" + a + b + badc;
                break;
            case 1:
                test += "" + bada + b + c;
                break;
            case 2:
                test += "" + a + badb + c;
                break;
            default:
                test += "" + a + badb + badc;
        }
        return test;
    }

    static String invalidPiecePlacement(Random r) {
        int location = 0;
        int shape = 0;
        int orientation = 0;
        switch (r.nextInt(2)) {
            case 0:
                shape = r.nextInt(6);
                if (shape == 1) shape++; // skip over 'B' (one-sided shape)
                if (shape == 4) shape++; // skip over 'E' (one-sided shape)
                location = r.nextBoolean() ? r.nextInt(10) : 40 + r.nextInt(10);
                orientation = r.nextInt(8);
                break;
            case 1:
                shape = r.nextInt(8);
                boolean flip = r.nextBoolean();
                orientation = r.nextInt(4) + (flip ? 4 : 0);
                location = flip ? (CENTRAL_NONPEGS[r.nextInt(CENTRAL_NONPEGS.length)]) : (CENTRAL_PEGS[r.nextInt(CENTRAL_PEGS.length)]);
                break;
            default:
                shape = r.nextInt(12);
                boolean top = r.nextBoolean();
                location = r.nextInt(6) + (top ? 0 : 40);
                orientation = 2*r.nextInt(4);
        }
        return "" + (char) ('A' + shape) + (char) ('A' + orientation) + (char) (location + (location < 25 ? 'A' : 'a'));
    }


    static String normalize(String placement) {
        String[] pp = new String[8];
        boolean flip = false;
        for (int i = 0; i < placement.length(); i += 3) {
            int idx = placement.charAt(i) - 'A';
            pp[idx] = placement.substring(i, i + 3);
        }
        String norm = "";
        for (int i = 0; i < pp.length; i++) {
            if (pp[i] != null) norm += pp[i];
        }
        return norm;
    }
}
