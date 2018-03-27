package pl.seremak.misc;

import java.util.HashMap;
import java.util.Map;

public class DesignationTypeMapping {
    public static final Map<String, String> MAP = new HashMap<String, String>() {{
            put("park narodowy", "PL01");
            put("rezerwat przyrody", "PL02");
            put("park krajobrazowy", "PL03");
            put("obszar chronionego krajobrazu", "PL04");
    }};
}
