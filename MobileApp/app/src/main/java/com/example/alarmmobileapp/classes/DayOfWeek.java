package com.example.alarmmobileapp.classes;

public enum DayOfWeek {
    MONDAY{
        @Override
        public String toString() {
            return "Понедельник";
        }
    },
    TUESDAY{
        @Override
        public String toString() {
            return "Вторник";
        }
    },
    WEDNESDAY{
        @Override
        public String toString() {
            return "Среда";
        }
    },
    THURSDAY{
        @Override
        public String toString() {
            return "Четверг";
        }
    },
    FRIDAY{
        @Override
        public String toString() {
            return "Пятница";
        }
    },
    SATURDAY{
        @Override
        public String toString() {
            return "Суббота";
        }
    },
    SUNDAY{
        @Override
        public String toString() {
            return "Воскресенье";
        }
    };
    public static DayOfWeek fromString(String day) {
        switch (day) {
            case "Понедельник":
                return MONDAY;
            case "Вторник":
                return TUESDAY;
            case "Среда":
                return WEDNESDAY;
            case "Четверг":
                return THURSDAY;
            case "Пятница":
                return FRIDAY;
            case "Суббота":
                return SATURDAY;
            case "Воскресенье":
                return SUNDAY;
            default:
                throw new IllegalArgumentException("Неизвестный день: " + day);
        }
    }
    public static String dayToShortString(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return "Пн";
            case TUESDAY:
                return "Вт";
            case WEDNESDAY:
                return "Ср";
            case THURSDAY:
                return "Чт";
            case FRIDAY:
                return "Пт";
            case SATURDAY:
                return "Сб";
            case SUNDAY:
                return "Вс";
            default:
                throw new IllegalArgumentException("Неизвестный день: " + day);
        }
    }

    public static int dayToInt(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 5;
            case SATURDAY:
                return 6;
            case SUNDAY:
                return 7;
            default:
                throw new IllegalArgumentException("Неизвестный день: " + day);
        }
    }
}
