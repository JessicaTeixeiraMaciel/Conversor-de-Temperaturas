import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            initialize();
            List<UnityTemp> unitsTemps = new ArrayList<UnityTemp>(List.of(UnityTemp.values()));
            UnityTemp unityInput = menuOption(sc, "entrada: ", "", unitsTemps);
            System.out.println("\n2) Escolha uma unidade de saída:");
            unitsTemps.remove(unityInput);
            UnityTemp unityOutput = menuOption(sc, "saída: ", String.valueOf(unityInput), unitsTemps);

            System.out.println();
            int amount = getAmountTemp(sc);

            if (amount == 0) {
                Close();
                return;
            }

            System.out.println("\n--------------------------------------------------------------\n               OK! VAMOS FAZER " + amount + " CONVERSÕES.");
            System.out.println("         VOCÊ VAI CONVERTER " + unityInput + " EM " + unityOutput + "!\n--------------------------------------------------------------\n\n4) Insira os valores das temperaturas que serão convertidas:\n");

            Data data = new Data();
            data.unitInput = data.symbolTemps.get(String.valueOf(unityInput));
            data.unitOutput = data.symbolTemps.get(String.valueOf(unityOutput));

            int a = 1;
            while (a < amount + 1) {
                data.tempsImput.add(getTemp(sc, a, data.unitInput));
                a++;
            }
            System.out.println("\nA média das temperaturas inseridas é de " + averageTemp(data.tempsImput) + " " + data.unitInput + ".");
            System.out.println("\n5) Resultado:\n");


            for (int i = 0; i < data.tempsImput.size(); i++) {
                    double result = convertionTemp(unityInput, unityOutput, data.tempsImput.get(i), data.unitOutput, i);
                    data.tempsOutput.add(result);
                }

            System.out.println("\nA média das temperaturas convertidas é de " + averageTemp(data.tempsOutput) + " " + data.unitOutput + ".");

            Close();
        } catch (Exception e) {
            System.out.printf("\nExceção %s", e.getMessage());
        }
    }

    private static void Close() {
        System.out.println("\n--------------------------------------------------------------\n              OBRIGADA POR USAR O CONVERSOR! =)\n--------------------------------------------------------------\n");
    }

    private static double convertionTemp(UnityTemp unityInput, UnityTemp unityOutput, double temp, String unit, int i) {
        double result;
        switch (unityOutput) {
            case CELSIUS:
                result = toCelsiusTransform(unityInput, temp);
//                System.out.printf("O resultado da conversão é %f\n", result);
                break;

            case FAHRENHEIT:
                result = toFahrenheitTransform(unityInput, temp);
//                System.out.printf("O resultado da conversão é %f\n", result);
                break;

            case KELVIN:
                result = toKelvinTransform(unityInput, temp);
//                System.out.printf("O resultado da conversão é %f\n", result);
                break;

            default:
                result = 0;
//                System.out.printf("O resultado da conversão é %f\n", result);
                break;
        }
        System.out.printf("O resultado da %sº conversão é %.1f %s\n", i+1, result, unit);
        return result;
    }

    private static void initialize() {
        System.out.println("\n--------------------------------------------------------------\n       BEM VINDO(A) AO NOSSO CONVERSOR DE TEMPERATURAS!\n--------------------------------------------------------------\n\n1) Escolha uma unidade de entrada:");
    }


    private static int getAmountTemp(Scanner sc) {
        int tempsConverter;

        while (true) {
            System.out.print("3) Informe a quantidade de conversões que deseja fazer: ");

            if (sc.hasNextInt()) {
                tempsConverter = sc.nextInt();
                if (tempsConverter > 0) {
                    return tempsConverter;
                } else if (tempsConverter == 0) {
                    if (QuestionExit(sc))
                        return tempsConverter;
                } else
                    System.out.println("Valor digitado é incorreto.\n");
            } else {
                System.out.println("Valor digitado é incorreto.\n");
                sc.next();
            }
        }
    }

    private static boolean QuestionExit(Scanner sc) {
        System.out.print("Você digitou '0'. Para encerrar o programa digite 's': ");

        return sc.next().equalsIgnoreCase("s");
    }

    private static double getTemp(Scanner sc, int i, String unit) {

        while (true) {
            System.out.printf("Digite a %sº temperatura (em %s): ", i, unit);

            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                System.out.println("Valor digitado é incorreto.\n");
                sc.next();
            }
        }
    }

    public static double toFahrenheitTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.CELSIUS) {
            return ((9 * temp) / 5) + 32;
        } else if (type == UnityTemp.KELVIN) {
            return ((9 * temp - 273.15) / 5) + 32;
        } else {
            return temp;
        }
    }

    public static double toCelsiusTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.FAHRENHEIT) {
            return (temp - 32) / 1.8;
        } else if (type == UnityTemp.KELVIN) {
            return temp - 273.15;
        } else {
            return temp;
        }
    }

    public static double toKelvinTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.FAHRENHEIT) {
            return 1.8 * (temp - 273);
        } else if (type == UnityTemp.CELSIUS) {
            return temp + 273.15;
        } else {
            return temp;
        }
    }

    public static double averageTemp(List<Double> tempsOutput) {
        return tempsOutput.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
    }

    public static UnityTemp menuOption(Scanner sc, String question, String filter, List<UnityTemp> listFilter) {

        for (int i = 1; i <= listFilter.size(); i++) {
            System.out.printf("Opção %s - %s.\n", i, listFilter.get(i - 1));
        }
        System.out.println();
        int option;

        while (true) {
            System.out.printf("Digite sua opção de " + question);

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                if (option > 0 && option <= listFilter.size()) {
                    String choice = String.valueOf(listFilter.get(option - 1));
                    if (choice == filter)
                        System.out.println("Essa unidade já foi selecionada.");
                    else
                        return listFilter.get(option - 1);
                } else
                    System.out.println("Valor digitado é incorreto.\n");
            } else {
                System.out.println("Valor digitado é incorreto.\n");
                sc.next();
            }
        }
    }

}