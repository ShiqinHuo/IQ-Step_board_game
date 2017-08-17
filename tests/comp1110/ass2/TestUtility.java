package comp1110.ass2;

import java.util.Random;

public class TestUtility {
    static final int BASE_ITERATIONS = 100;
    static final int PIECES = 8;
    static final int ORIENTATIONS = 8;
    static final char BAD = Character.MAX_VALUE - 'z';

    static final String[] PLACEMENTS = {
            "CEQEHuGEOBDxFGSHCiAALDBg",
            "CEQEHuGEOBDxFGSHCiAALDBg",
            "BGKEGOCGQAGlHCiDBgGGnFGS",
            "BHFFCLHBNAGlCAiDBgGGnEDI",
            "CHSAHQGFjHCNBGKDBgFHlEAo",
            "GDLADgHAiEFFCGcDAkBDxFGS",
            "DFOCGQGDLADgHFjBGSFHlEAo",
            "HBLADgBHnCGODAiGElFGQEDI",
            "BGKFCNCHSAHQHFnEBvGAiDBg",
            "DFOAALHHnGAkFGQCAiBBgEDI",
            "AALBAkCGODBgEDIFHnGGQHCi",
            "ADgBAkCGODAiEDIFHnGGQHBL",
            "AALBBgCAkDFQEBxFDNGGSHCi",
            "AALBAmCAkDFQEAgFDNGGSHCi",
            "AALBBgCAkDFQEHwFDNGGSHCi",
            "ADgBBGCDkDAiEAoFCLGHSHBN",
            "ADgBBGCDkDAiEAoFDNGHSHBL",
            "AALBBGCAkDBgEAoFDNGHSHCi",
            "ADgBBGCDkDAiEAoFDNGFSHBL",
            "ADgBBGCDkDAiEAoFCLGFSHBN",
            "AALBBGCAkDBgEAoFDNGFSHCi",
            "AALBBgCAkDFOEDIFHnGGQHCi",
            "AALBAkCAgDFOEDIFHnGGQHCi",
            "AALBAkCGQDBgEGOFGSGEnHCi",
            "ADgBCTCGQDAiEGOFElGEnHBL",
            "AALBCTCGQDBgEGOFElGEnHCi",
            "ADgBAkCGQDAiEGOFGSGEnHBL",
    };

    static final String[][] VIABLE1 = {
            {"DFOGGQEDIBAkFHnHCiAALCAg", "", "DFO"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFO", "GGQ"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDI", "BAk"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHn", "HCi"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCi", "AAL"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCiAAL", "CAg"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "", "DFO"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFO", "GGQ"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDI", "BAk"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHn", "HCi"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCi", "AAL"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCiAAL", "CAg"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "", "DFO"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFO", "GGQ"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDI", "BAk"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHn", "HCi"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCi", "AAL"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCiAAL", "CAg"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "", "DFO"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFO", "GGQ"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHn", "HCi"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCi", "AAL"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDIBAkFHnHCiAAL", "CAg"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "", "CGO"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGO", "GGQ"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDI", "BAk"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHn", "HCi"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCi", "AAL"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCiAAL", "DBg"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "", "CGO"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGO", "GGQ"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDI", "BAk"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHn", "HCi"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCi", "AAL"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCiAAL", "DBg"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "", "CGO"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGO", "GGQ"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDI", "BAk"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHn", "HCi"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCi", "AAL"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCiAAL", "DBg"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "", "CGO"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGO", "GGQ"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDI", "BAk"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHn", "HCi"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCi", "AAL"},
            {"CGOGGQEDIBAkFHnHCiAALDBg", "CGOGGQEDIBAkFHnHCiAAL", "DBg"},
            {"DFOGGQEDIBAkFHnHCiAALCAg", "DFOGGQEDI", "BAk"},
    };
    static final String[][] VIABLE2 = {
            {"EEfCHSAHQFDNGBLDAiHFlBDx", "", "EEf", "CHS"},
            {"BGKFCNCFlAFnHHSGAiECPDBg", "BGK", "FCN", "CFl"},
            {"BGKFCNCFlAFnHHSGAiECPDBg", "BGKFCNCFl", "AFn", "GAi"},
            {"BGKFCNCFlAFnHHSGAiECPDBg", "BGKFCNCFlAFn", "HHS", "GAi"},
            {"BGKFCNCFlAFnHHSGAiECPDBg", "BGKFCNCFlAFnHHSGAi", "ECP", "DBg"},
            {"CFjBGKGAgHElAFnDDNFGQEDI", "CFj", "BGK", "HEl"},
            {"CFjBGKGAgHElAFnDDNFGQEDI", "CFjBGK", "GAg", "HEl"},
            {"CFjBGKGAgHElAFnDDNFGQEDI", "CFjBGKGAg", "HEl", "DDN"},
            {"CFjBGKGAgHElAFnDDNFGQEDI", "CFjBGKGAgHEl", "AFn", "DDN"},
            {"EEfDHnBCTHGQFBiGBkABNCBL", "", "EEf", "DHn"},
            {"EEfDHnBCTHGQFBiGBkABNCBL", "EEf", "DHn", "FBi"},
            {"EEfDHnBCTHGQFBiGBkABNCBL", "EEfDHn", "BCT", "FBi"},
            {"EEfDHnBCTHGQFBiGBkABNCBL", "EEfDHnBCT", "HGQ", "FBi"},
            {"BEcCEjABPHCNEDIFHnGBgDCL", "", "BEc", "CEj"},
    };
    static final String[][] VIABLE3 = {
            {"EFBCFlAFnGFSBFqFGhHANDDP", "", "EFB", "CFl", "BFq"},
            {"DFQFDNEHuGGnBCTHCiAALCAg", "DFQ", "FDN", "EHu", "GGn"},
            {"CGOGDLAGjHEQDBgFHlBDxEDI", "CGOGDLAGjHEQ", "DBg", "FHl", "EDI"},
            {"BGKAFnDFOGGQEDICAkFEXHBg", "", "BGK", "AFn", "DFO"},
            {"BFOHBLADgCEnGGQEDIFElDAi", "BFOHBLADgCEnGGQ", "EDI", "FEl", "DAi"},
            {"BGSHGQEHuGEOCAiAALDBgFHn", "BGSHGQEHuGEO", "CAi", "AAL", "FHn"},
            {"GGQFDNBDxEDICAkHCiAALDBg", "GGQ", "FDN", "BDx", "EDI"},
            {"DFQFDNGHlBDxEDIHCiAALCAg", "DFQFDNGHl", "BDx", "EDI", "HCi"},
            {"DFOCGQGDLADgHFjBGSFHlEAo", "DFOCGQGDL", "ADg", "HFj", "BGS"},
    };

    final static String[] GOOD_PAIRS = {
            "CEQEHu",
            "GEOBDx",
            "BGKEGO",
            "BHFFCL",
            "CHSAHQ",
            "GDLADg",
            "DFOCGQ",
            "HBLADg",
            "BGKFCN",
            "DFOAAL",
            "DFOGGQ",
            "DFOGGQ",
            "EGOHBL",
            "EGOCGQ",
            "CGOGGQ",
            "HBLADg",
            "GFSEAo",
            "EAoBBG",
            "BBGFDN",
            "DFQFDN",
            "FDNGGS",
            "GGSEHw",
            "GHSEAo",
            "EAoBBG",
            "FDNCAk",
            "EGOCGQ",
            "EGOHBL",
            "EGOCGQ",
            "EGOHBL"
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
            {"CEQEHu", "CEQEHuGEOBDxFGSHCiAALDBg"},
            {"CEQEHuGEO", "CEQEHuGEOBDxFGSHCiAALDBg"},
            {"FDNCGQBAk", "FDNCGQBAkGGnEDIHCiAALDBg"},
            {"BGKEGOCGQAGl", "BGKEGOCGQAGlHCiDBgGGnFGS"},
            {"BHFFCLHBNAGl", "BHFFCLHBNAGlCAiDBgGGnEDI"},
            {"CHSAHQGFjHCNBGK", "CHSAHQGFjHCNBGKDBgFHlEAo"},
            {"GDLADgHAiEFFCGc", "GDLADgHAiEFFCGcDAkBDxFGS"},
            {"DFOCGQGDLADgHFjBGS", "DFOCGQGDLADgHFjBGSFHlEAo"},
            {"HBLADgBHnCGODAiGEl", "HBLADgBHnCGODAiGElFGQEDI"},
            {"BGKFCNCHSAHQHFnEBvGAi", "BGKFCNCHSAHQHFnEBvGAiDBg"},
            {"DFOAALHHnGAkFGQCAiBBg", "DFOAALHHnGAkFGQCAiBBgEDI"},
    };

    final static String[][] SOLUTIONS_MULTI = {
            {"DFOGGQEDI", "DFOGGQEDICAkFHnHCiAALBBg", "DFOGGQEDIBAkFHnHCiAALCAg"},
            {"EGOCGQGGS", "EGOHBLADgCGQGGSDAiFEnBAk", "EGOCGQGGSFEnBAkHCiAALDBg"},
            {"CGOGGQEDI", "CGOGGQEDIBAkFHnHCiAALDBg", "HBLADgCGOGGQEDIDAiBAkFHn"},
            {"GFSEAoBBG", "GFSEAoBBGFDNHBLADgDAiCDk", "GFSEAoBBGFCLHBNADgDAiCDk", "GFSEAoBBGFDNCAkHCiAALDBg"},
            {"DFQFDNGGS", "DFQFDNGGSEBxCAkHCiAALBBg", "DFQFDNGGSBAmCAkHCiAALEAg", "DFQFDNGGSEHwCAkHCiAALBBg"},
            {"GHSEAoBBG", "GHSEAoBBGFCLHBNADgDAiCDk", "GHSEAoBBGFDNHBLADgDAiCDk", "GHSEAoBBGFDNCAkHCiAALDBg"},
            {"EGOCGQGEn", "EGOCGQGEnFGSBAkHCiAALDBg", "EGOHBLADgCGQGEnBCTFElDAi", "EGOCGQGEnBCTFElHCiAALDBg", "EGOHBLADgCGQGEnFGSDAiBAk"},
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
