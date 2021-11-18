package com.baek.nameinverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NameInverter {

    public String invert(String name) {
        if (name == null || name.isEmpty())
            return "";
        return formatName(removeHonorific(splitNames(name)));
    }

    private String formatName(List<String> names) {
        if (names.size() == 1)
            return names.get(0);
        return formatMultiElementName(names);
    }

    private List<String> removeHonorific(List<String> names) {
        if (names.size() > 1 && isHonorific(names))
            names.remove(0);
        return names;
    }

    private ArrayList<String> splitNames(String name) {
        return new ArrayList<>(Arrays.asList(name.trim().split("\\s+")));
    }

    private String formatMultiElementName(List<String> names) {
        String postNominals = "";
        if (names.size() > 2)
            postNominals = getPostNominals(names);
        return String.format("%s, %s %s", names.get(1), names.get(0), postNominals).trim();
    }

    private String getPostNominals(List<String> names) {
        return names.subList(2, names.size()).stream().map(s -> s + " ").collect(Collectors.joining());
    }

    private boolean isHonorific(List<String> names) {
        return names.get(0).matches("Mr\\.|Mrs\\.");
//        return names.get(0).equals("Mr.")||names.get(0).equals("Mrs.");
    }
}
