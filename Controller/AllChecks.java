package Controller;

import Exceptions.*;

import java.util.Objects;
import java.util.regex.Pattern;

public class AllChecks {

    public String[] getArrayFromString(String data) {
        String[] dataArray = data.split(" ");
        return dataArray;
    }

    public int checkAmoundData(String data) {
        String[] dataArray = getArrayFromString(data);
        int result = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray.length > 6) {
                result = -1;
            } else if (dataArray.length < 6) {
                result = -2;
            } else if (dataArray.length == 6) {
                result = 1;
            }
        }
        return result;
    }

    public void checkDate(String birthDate) throws BirthDateException {
        String[] date = birthDate.split("\\.");

        if (date.length != 3) {
            throw new BirthDateException("Вы забыли поставить разделитель '.'");
        }

        String day = date[0];
        String month = date[1];
        String year = date[2];
        int dayNumber;
        int monthNumber;
        int yearNumber;

        boolean isDay = day.matches("-?\\d+");
        boolean isMonth = month.matches("-?\\d+");
        boolean isYear = year.matches("-?\\d+");

        if (isDay && isMonth && isYear) {
            dayNumber = Integer.parseInt(day);
            monthNumber = Integer.parseInt(month);
            yearNumber = Integer.parseInt(year);
        } else {
            throw new BirthDateException("Вы ввели не числовое значение");
        }
        if (day.length() != 2) {
            throw new BirthDateException("День рождения необходимо указать в формате 'dd'");
        } else if (month.length() != 2) {
            throw new BirthDateException("Месяц рождения необходимо указать в формате 'MM'");
        } else if (year.length() != 4) {
            throw new BirthDateException("Год рождения необходимо указать в формате 'yyyy'");
        } else if (dayNumber < 1) {
            throw new BirthDateException("День не может быть меньше 1 числа");
        } else if (dayNumber > 31) {
            throw new BirthDateException("День не может быть больше 31 числа");
        } else if (monthNumber < 1) {
            throw new BirthDateException("Месяц не может быть меньше 1");
        } else if (monthNumber > 12) {
            throw new BirthDateException("Месяц не может быть больше 12");
        } else if (monthNumber == 02 && dayNumber == 29) {
            if (yearNumber % 4 != 0 || yearNumber % 400 != 0 && yearNumber % 100 == 0) {
                throw new BirthDateException("В феврале этого года 28 дней");
            }
        } else if (monthNumber == 02 && dayNumber > 28) {
            throw new BirthDateException("В феврале нет столько дней");
        } else if (dayNumber > 30) {
            if (monthNumber == 04 || monthNumber == 06 || monthNumber == 9 || monthNumber == 11) {
                throw new BirthDateException("В этом месяце 30 дней");
            }
        }
    }

    public boolean checkName(String name) {
        char firstChar = name.charAt(0);
        String firstPartName = Character.toString(firstChar);
        String secondPartName = "";
        for (int i = 1; i < name.length(); i++) {
            secondPartName += name.charAt(i);
        }
        boolean isName = Pattern.matches("[A-Z]+", firstPartName)
                && Pattern.matches("[a-z]+", secondPartName) ||
                Pattern.matches("[А-Я]+", firstPartName)
                        && Pattern.matches("[а-я]+", secondPartName);
        return isName;
    }

    public boolean checkLanguage(String lastName, String firstName, String secondName){
        boolean isLanguage = Pattern.matches("[a-zA-Z]+", lastName)
                && Pattern.matches("[a-zA-Z]+", firstName) &&
                Pattern.matches("[a-zA-Z]+", secondName) ||
                Pattern.matches("[а-яА-Я]+", lastName) &&
                        Pattern.matches("[а-яА-Я]+", firstName) &&
                        Pattern.matches("[а-яА-Я]+", secondName);
        return isLanguage;
    }

    public void checkLanguageName(String lastName, String firstName, String secondName) throws LanguageNameException {
        if (!checkLanguage(lastName, firstName, secondName)){
            throw new LanguageNameException("Языки ввода фамилии, имени и отчества отличаются");
        }
    }

    public void checkLastName(String lastName) throws NameException {
        if(!checkName(lastName)){
            throw new NameException("Неверный формат фамилии");
        }
    }

    public void checkFirstName(String firstName) throws NameException {
        if(!checkName(firstName)){
            throw new NameException("Неверный формат имени");
        }
    }

    public void checkSecondName(String secondName) throws NameException {
        if(!checkName(secondName)){
            throw new NameException("Неверный формат отчества");
        }
    }

    public void checkTelephoneNumber(String telephoneNumber) throws TelephoneNumberException {
        boolean isTelNum = telephoneNumber.matches("-?\\d+");
        if (!isTelNum) {
            throw new TelephoneNumberException();
        }
    }

    public void checkGender(String gender) throws GenderException {
        if (!Objects.equals(gender, "m") && !Objects.equals(gender, "f")) {
            throw new GenderException();
        }
    }
}
