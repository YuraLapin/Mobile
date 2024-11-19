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
    }
}
