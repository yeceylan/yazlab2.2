package com.yonis.yazlab22.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterStartEnd {

    private String lettername;
    private int start;
    private int end;

    public static final LetterStartEnd[] letterStartEnds= {
            new LetterStartEnd("a",0,6080),
            new LetterStartEnd("b",6081,12254),
            new LetterStartEnd("c",12255,13480),
            new LetterStartEnd("ç",13481,16581),
            new LetterStartEnd("d",16582,21671),
            new LetterStartEnd("e",21672,24401),
            new LetterStartEnd("f",24402,25929),
            new LetterStartEnd("g",25930,29111),
            new LetterStartEnd("h",29112,31974),
            new LetterStartEnd("i",31975,34825),
            new LetterStartEnd("ı",34826,35234),
            new LetterStartEnd("j",35235,35363),
            new LetterStartEnd("k",35364,44307),
            new LetterStartEnd("l",44308,45153),
            new LetterStartEnd("m",45154,49508),
            new LetterStartEnd("n",49509,50670),
            new LetterStartEnd("o",50671,52070),
            new LetterStartEnd("ö",52071,53183),
            new LetterStartEnd("p",53184,55638),
            new LetterStartEnd("r",55639,56644),
            new LetterStartEnd("s",56645,62730),
            new LetterStartEnd("ş",62731,63950),
            new LetterStartEnd("t",63951,68846),
            new LetterStartEnd("u",68847,69860),
            new LetterStartEnd("ü",69861,70391),
            new LetterStartEnd("v",70392,71315),
            new LetterStartEnd("y",71316,75263),
            new LetterStartEnd("z",75264,76073)
    };

    public static List<LetterStartEnd> letterStartEndArrayList = new ArrayList<LetterStartEnd>(Arrays.asList(letterStartEnds));

    public LetterStartEnd(String lettername, int start, int end) {
        this.lettername = lettername;
        this.start = start;
        this.end = end;
    }

    public String getLettername() {
        return lettername;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
