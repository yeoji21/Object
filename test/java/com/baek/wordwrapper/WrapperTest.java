package com.baek.wordwrapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WrapperTest {
    @Test
    public void should_wrap(){
        assertWraps(null, 1, "");
        assertWraps("", 1, "");
        assertWraps("x", 1, "x");
        assertWraps("xx", 1, "x\nx");
        assertWraps("xxx", 1, "x\nx\nx");
        assertWraps("x x", 1, "x\nx");
        assertWraps("x xx", 3, "x\nxx");
        assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7, "four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt");
    }

    private void assertWraps(String s, int width, String expected) {
        assertThat(wrap(s, width)).isEqualTo(expected);
    }

    private String wrap(String s, int width) {
        if (s==null)
            return "";
        else if(s.length() <= width)
            return s;
        else{
            int breakPoint = s.lastIndexOf(" ", width);
//            int breakPoint = s.substring(0, width).indexOf(" ");
            if(breakPoint == -1)
                breakPoint = width;
            return s.substring(0, breakPoint) + "\n" + wrap(s.substring(breakPoint).trim(), width);
        }
    }


}
