import java.util.Scanner;

public class Main {

    static boolean isRome = false;
    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        String mathOperation = scanner.nextLine();

        System.out.println(calc(mathOperation));

    }
    public static String calc(String input) throws Exception{

        Main main = new Main();
        int numbers[];
        String result = "";
        int mathResult = 0;

        String inputElements[] = input.split(" ");



        main.detectRomeNumbers(inputElements);

        main.checkLastElement(inputElements);
        main.detectNumbersAmount(inputElements);

        if (main.detectOperatorsAmount(inputElements) !=1){
            throw new Exception(WrongMathOperationFormat());
        }


        numbers = main.convertNumbersToInt(inputElements);
        if (numbers[0] < 1 || numbers[0] > 10 || numbers[2] < 1 || numbers[2] > 10){
            throw new Exception(UnfitNumberException());
        }


        String operator = inputElements[1];
        switch (operator){
            case "+":
                mathResult = numbers[0] + numbers[2];
                break;
            case  "-":
                mathResult = numbers[0] - numbers[2];
                break;
            case  "*":
                mathResult = numbers[0] * numbers[2];
                break;
            case "/":
                mathResult = numbers[0] / numbers[2];
                break;
            default:
                throw new Exception(NotArithmeticOperation());
        }

        if (isRome == true){
            result = main.convertNumToRoman(mathResult);
            return result;
        }

        result = Integer.toString(mathResult);
        return result;
    }

    public int detectOperatorsAmount(String stringElements[]) throws Exception{

        String operators[] = {"+", "-", "*", "/"};
        int detectedOperators = 0;

        for(int i = 1; i <= stringElements.length - 1; i = i + 2){
            boolean isOperator = false;
            for (String op: operators){
                if (stringElements[i].equals(op)){
                    detectedOperators++;
                    isOperator = true;
                }
            }
            if (!isOperator){
                throw new Exception(NotArithmeticOperation());
            }
        }
        return detectedOperators;
    }

    public void detectNumbersAmount(String stringElements[]) throws Exception{
        int detectedNumbers = 0;

        if(stringElements.length < 3){
            throw new Exception(NotArithmeticOperation());
        }
        for (int i = 0; i < stringElements.length; i = i + 2){
            try {
                int number = Integer.valueOf(stringElements[i]);
                detectedNumbers++;
            }
            catch (Exception e){
                throw new Exception(NotArithmeticOperation());
            }

            if(detectedNumbers > 2){
                throw new Exception(WrongMathOperationFormat());
            }
        }
    }

    public void detectRomeNumbers(String stringElements[]) throws Exception{

        int romeNumbersCount = 0;
        for(int i = 0; i < stringElements.length; i++){
            switch (stringElements[i]){
                case ("I"):
                    stringElements[i] = "1";
                    romeNumbersCount++;
                    break;
                case ("II"):
                    stringElements[i] = "2";
                    romeNumbersCount++;
                    break;
                case ("III"):
                    stringElements[i] = "3";
                    romeNumbersCount++;
                    break;
                case ("IV"):
                    stringElements[i] = "4";
                    romeNumbersCount++;
                    break;
                case ("V"):
                    stringElements[i] = "5";
                    romeNumbersCount++;
                    break;
                case ("VI"):
                    stringElements[i] = "6";
                    romeNumbersCount++;
                    break;
                case ("VII"):
                    stringElements[i] = "7";
                    romeNumbersCount++;
                    break;
                case ("VIII"):
                    stringElements[i] = "8";
                    romeNumbersCount++;
                    break;
                case ("IX"):
                    stringElements[i] = "9";
                    romeNumbersCount++;
                    break;
                case ("X"):
                    stringElements[i] = "10";
                    romeNumbersCount++;
                    break;
            }
        }

        if(romeNumbersCount == 1){
            throw new Exception(DifferentNumberKindsException());
        }
        else if (romeNumbersCount == 2){
            isRome = true;
        }

    }
    private String convertNumToRoman (int numArabian) throws Exception{

        try {
            String[] romanNumbers = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                    "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                    "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                    "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                    "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
            };
            final String numRoman = romanNumbers[numArabian];

            if (numRoman.equals(romanNumbers[0])) throw new Exception(RomeNumberZeroException());

            return numRoman;
        }
        catch (Exception e){
            throw new Exception(WrongRomeNumberException());
        }
    }

    public void checkLastElement(String stringElements[]) throws Exception{
        try{
            int lastNumber = Integer.valueOf(stringElements[stringElements.length-1]);
        }
        catch (Exception e){
            throw new Exception(NotArithmeticOperation());
        }
    }

    public int[] convertNumbersToInt(String inputElements[]){
        int[] numbers = new int[3];
        try {
            for (int i = 0; i < inputElements.length; i = i + 2){
                numbers[i] = Integer.valueOf(inputElements[i]);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return numbers;
    }

    public static String NotArithmeticOperation(){
        return "т.к. строка не является математической операцией";
    }
    public static String UnfitNumberException(){
        return "т.к калькулятор должен принимать на вход числа от 1 до 10 включительно";
    }
    public static String WrongMathOperationFormat(){
        return "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
    }
    public static String RomeNumberZeroException(){
        return "т.к в римской системе нет нуля";
    }
    public static String DifferentNumberKindsException(){
        return "т.к. используются одновременно разные системы счисления";
    }
    public static String WrongRomeNumberException(){
        return "т.к. в римской системе нет отрицательных чисел";
    }
}