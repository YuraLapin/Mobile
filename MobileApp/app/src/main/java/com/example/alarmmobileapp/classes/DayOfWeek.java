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
}
