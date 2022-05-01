package org.hypothesis.utils;

import com.vaadin.ui.Alignment;
import org.apache.commons.lang3.StringUtils;
import org.hypothesis.exception.NonInstantiableClassException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.vaadin.ui.Alignment.*;
import static org.hypothesis.interfaces.dom.Constants.*;

public class StringParserUtils {

    private StringParserUtils() {
        throw new NonInstantiableClassException(this.getClass());
    }

    public static Integer parseInteger(String value) {
        return parseIntegerInternal(value, null);
    }

    public static int parseInteger(String value, int defaultValue) {
        return parseIntegerInternal(value, defaultValue);
    }

    private static Integer parseIntegerInternal(String value, Integer defaultValue) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(v -> {
                    try {
                        return Integer.parseInt(value);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .orElse(defaultValue);
    }

    public static Double parseDouble(String value) {
        return parseDoubleInternal(value, null);
    }

    public static double parseDouble(String value, double defaultValue) {
        return parseDoubleInternal(value, defaultValue);
    }

    private static Double parseDoubleInternal(String value, Double defaultValue) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(v -> {
                    try {
                        return Double.parseDouble(value);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .orElse(defaultValue);
    }

    public static LocalDate parseLocalDate(String value, DateTimeFormatter formatter) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .filter(v -> formatter != null)
                .map(v -> {
                    try {
                        return LocalDate.parse(v, formatter);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .orElse(null);
    }

    public static LocalDate parseLocalDate(String value, String format) {
        return parseLocalDate(value, Optional.ofNullable(format)
                .filter(StringUtils::isNotBlank)
                .map(DateTimeFormatter::ofPattern)
                .orElse(null)
        );
    }

    public static String parseDimension(String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .filter(v -> !(v.endsWith(STR_UNIT_PX) //
                        || v.endsWith(STR_UNIT_PT) //
                        || v.endsWith(STR_UNIT_EM) //
                        || v.endsWith(STR_UNIT_EX) //
                        || v.endsWith(STR_UNIT_MM) //
                        || v.endsWith(STR_UNIT_CM) //
                        || v.endsWith(STR_UNIT_PERCENT)))
                .map(v -> {
                    try {
                        Double doubleValue = Double.parseDouble(v);
                        return doubleValue + STR_UNIT_PX;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .orElse(value);
    }

    public static Boolean parseBoolean(String value) {
        return parseBooleanInternal(value, null);
    }

    public static boolean parseBoolean(String value, boolean defaultValue) {
        return parseBooleanInternal(value, defaultValue);
    }

    private static Boolean parseBooleanInternal(String value, Boolean defaultValue) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(Boolean::parseBoolean)
                .orElse(defaultValue);
    }

    public static Alignment parseAlignment(String value) {
        return Optional.ofNullable(value)
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(String::toLowerCase)
                .map(v -> {
                    switch (v) {
                        case "tl":
                        case "lt":
                            return TOP_LEFT;
                        case "tc":
                        case "ct":
                            return TOP_CENTER;
                        case "tr":
                        case "rt":
                            return TOP_RIGHT;
                        case "ml":
                        case "lm":
                            return MIDDLE_LEFT;
                        case "mc":
                        case "cm":
                            return MIDDLE_CENTER;
                        case "mr":
                        case "rm":
                            return MIDDLE_RIGHT;
                        case "bl":
                        case "lb":
                            return BOTTOM_LEFT;
                        case "bc":
                        case "cb":
                            return BOTTOM_CENTER;
                        case "br":
                        case "rb":
                            return BOTTOM_RIGHT;
                        default:
                            return null;
                    }
                })
                .orElse(MIDDLE_CENTER);
    }
}
