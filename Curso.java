import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Curso {
    ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
    Scanner scan = new Scanner(System.in);

    public void printarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            System.out.println("Não há disciplinas registradas");
            System.out.println("---------------------------");
        } else {
            for (int i = 0; i < listaDisciplinas.size(); i++) {
                System.out.println(listaDisciplinas.get(i).statusDisciplina());
                System.out.println("---------------------------");
            }

        }
    }

    public void criarDisciplina() {
        System.out.println("Digite o nome da disciplina: ");
        String addNomeDisciplina = scan.next();
        System.out.println("Digite a quantidade de vagas: ");
        int addQtdVagas = scan.nextInt();

        // int intAddQtdVagas = Integer.parseInt(addQtdVagas);

        Disciplina d = new Disciplina(addNomeDisciplina, addQtdVagas);
        listaDisciplinas.add(d);
        d.setNumeroDisciplina(listaDisciplinas.size() - 1);

        File file = new File(d.getNomeDisciplina());
        boolean mkdir = file.mkdir();
        System.out.println("Disciplina criada com sucesso!");
        System.out.println(
                "ATENÇÃO! Será criada uma pasta com o nome da disciplina, crie um documento de texto ou arquivo 'txt' com o nome 'gabarito' contendo o gabarito da prova da disciplina");
        System.out.println();

    }

    // menu
    public void menuDisciplina() {
        printarDisciplinas();

        System.out.println("Escolha uma disciplina pelo seu número de disciplina: ");
        int SearchNumeroDisciplina = scan.nextInt();
        // String menuOption;

        int menuOption;
        if(SearchNumeroDisciplina <= listaDisciplinas.size()){
        do {

            // int intSearchNumeroDisciplina = Integer.parseInt(SearchNumeroDisciplina);

            System.out.println("1 - Matricular aluno");
            System.out.println("2 - Imprimir lista de alunos matriculados");
            System.out.println("3 - Atribuir respostas dos alunos e gerar notas");
            System.out.println("4 - Gerar relatórios");
            System.out.println("0 - Retornar");
            menuOption = scan.nextInt();

            
            switch (menuOption) {
                case 1:
                    listaDisciplinas.get(SearchNumeroDisciplina).matricularAluno();

                    break;

                case 2:
                    listaDisciplinas.get(SearchNumeroDisciplina).imprimirListaAlunosMatriculados();

                    break;

                case 3:
                    try {
                        listaDisciplinas.get(SearchNumeroDisciplina).receberGabarito();
                        listaDisciplinas.get(SearchNumeroDisciplina).atribuirRespostasAlunos();
                        listaDisciplinas.get(SearchNumeroDisciplina).atribuirNotas();
                        listaDisciplinas.get(SearchNumeroDisciplina).gerarRelatorioOrdemDecrescente();
                        listaDisciplinas.get(SearchNumeroDisciplina).gerarRelatorioOrdemAlfabetica();
                    } catch (java.util.NoSuchElementException e) {
                        System.out.println("o erro é aq?");
                    }

                    break;

                case 4:
                    listaDisciplinas.get(SearchNumeroDisciplina).gerarRelatorioOrdemDecrescente();
            }
        
    } while (menuOption != 0);

    

    } else {
        System.out.println("Digite o número de uma disciplina cadastrada!");
    }
    }  
    

}
