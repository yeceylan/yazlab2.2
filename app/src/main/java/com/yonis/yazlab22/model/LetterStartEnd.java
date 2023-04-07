package com.yonis.yazlab22.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterStartEnd {

    private String lettername;
    private int start;
    private int end;

    private int point;

    public static final LetterStartEnd[] letterStartEnds= {
            new LetterStartEnd("a",0,6080,1),
            new LetterStartEnd("b",6081,12254,3),
            new LetterStartEnd("c",12255,13480,4),
            new LetterStartEnd("ç",13481,16581,4),
            new LetterStartEnd("d",16582,21671,3),
            new LetterStartEnd("e",21672,24401,1),
            new LetterStartEnd("f",24402,25929,7),
            new LetterStartEnd("g",25930,29111,5),
            new LetterStartEnd("h",29112,31974,5),
            new LetterStartEnd("i",31975,34825,1),
            new LetterStartEnd("ı",34826,35234,2),
            new LetterStartEnd("j",35235,35363,1),
            new LetterStartEnd("k",35364,44307,1),
            new LetterStartEnd("l",44308,45153,1),
            new LetterStartEnd("m",45154,49508,2),
            new LetterStartEnd("n",49509,50670,1),
            new LetterStartEnd("o",50671,52070,2),
            new LetterStartEnd("ö",52071,53183,7),
            new LetterStartEnd("p",53184,55638,5),
            new LetterStartEnd("r",55639,56644,1),
            new LetterStartEnd("s",56645,62730,2),
            new LetterStartEnd("ş",62731,63950,4),
            new LetterStartEnd("t",63951,68846,1),
            new LetterStartEnd("u",68847,69860,2),
            new LetterStartEnd("ü",69861,70391,3),
            new LetterStartEnd("v",70392,71315,7),
            new LetterStartEnd("y",71316,75263,3),
            new LetterStartEnd("z",75264,76073,4)
    };

    public static List<LetterStartEnd> letterStartEndArrayList = new ArrayList<LetterStartEnd>(Arrays.asList(letterStartEnds));

    public LetterStartEnd(String lettername, int start, int end,int point) {
        this.lettername = lettername;
        this.start = start;
        this.end = end;
        this.point=point;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
