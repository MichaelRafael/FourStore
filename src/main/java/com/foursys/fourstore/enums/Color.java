package com.foursys.fourstore.enums;

public enum Color {
    BLACK("01"),
    WHITE("02"),
    GRAY("03"),
    BEIGE("04"),
    RED("05"),
    BLUE("06"),
    YELLOW("07"),
    GREEN("08"),
    ORANGE("09"),
    PURPLE("10"),
    BLUE_GREEN("11"),
    BLUE_VIOLET("12"),
    RED_ORANGE("13"),
    RED_VIOLET("14"),
    YELLOW_ORANGE("15"),
    YELLOW_GREEN("16"),
    OTHER("17");

    private final String colorNumber;

    private Color(String colorNumber) {
        this.colorNumber = colorNumber;
    }

    public String getColorNumber() {
        return colorNumber;
    }
}
