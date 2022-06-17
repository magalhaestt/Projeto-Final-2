
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Curso uece = new Curso();

        String option;

        do {
            System.out.println("===========================");
            System.out.println("=== Menu de disciplinas ===");
            System.out.println("1 - Criar Disciplina");
            System.out.println("2 - Menu Disciplinas");
            System.out.println("3 - Ver Disciplinas");
            System.out.println("0 - Fechar programa");

            option = scan.next();

            switch (option) {
                case "1":
                    uece.criarDisciplina();
                    break;

                case "2":
                    uece.menuDisciplina();
                    break;

                case "3":
                    uece.printarDisciplinas();
                    break;

                case "0":
                    System.out.println("Obrigado, volte sempre!");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (option != "0");

        scan.close();
        /*
         * uece.criarDisciplina();
         * uece.printarDisciplinas();
         * uece.menuDisciplina();
         */
    }

}
