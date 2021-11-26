package com.choi.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteExpressionTest {
    @Test
    public void highlight_a_string_that_contains_note(){
        assertThat(highlightingNote("abc")).isEqualTo("abc");
        assertThat(highlightingNote("note")).isEqualTo("{note}");
        assertThat(highlightingNote("1 note")).isEqualTo("1 {note}");
        assertThat(highlightingNote("1 note 2")).isEqualTo("1 {note} 2");
        assertThat(highlightingNote("keynote")).isEqualTo("keynote");
        assertThat(highlightingNote("ke1note")).isEqualTo("ke1note");
        assertThat(highlightingNote("yes note1")).isEqualTo("yes note1");
        assertThat(highlightingNote("yes notea")).isEqualTo("yes notea");
        assertThat(highlightingNote("no a note")).isEqualTo("no a {note}");
        assertThat(highlightingNote("no a note note anote")).isEqualTo("no a {note} {note} anote");
        assertThat(highlightingNote("no a note note")).isEqualTo("no a {note} {note}");
        assertThat(highlightingNote("no a note  note")).isEqualTo("no a {note}  {note}");
        assertThat(highlightingNote("no a note anote note aa")).isEqualTo("no a {note} anote {note} aa");

    }

    private String highlightingNote(String expression) {
        List<String> splitExpression = new ArrayList<>(Arrays.asList(expression.split(" ")));
        return splitExpression.stream().map(s -> s.equals("note") ? s.replace("note", "{note}") : s)
                .collect(Collectors.joining(" "));
    }
}
