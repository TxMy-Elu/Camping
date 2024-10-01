package com.example.camping;

import java.util.ArrayList;
import java.util.List;

public class Horaire {
    public static List<String> getHoraires() {
        List<String> horaires = new ArrayList<>();
        for (int i = 8; i <= 18; i++) {
            horaires.add(String.format("%02d:00", i));
        }
        return horaires;
    }
}
