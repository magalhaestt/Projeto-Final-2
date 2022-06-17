import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Disciplina {
    private String nomeDisciplina;
    private int numeroDisciplina;
    private int qtdMaxAlunos;
    private int alunosMatriculados;
    ArrayList<Aluno> listaAlunosMatriculados = new ArrayList<Aluno>();
    private String gabarito;
    Scanner scan = new Scanner(System.in);

    // construtor
    public Disciplina(String nomeDisciplina, int qtdMaxAlunos) {
        this.nomeDisciplina = nomeDisciplina;
        this.numeroDisciplina = -1;
        this.qtdMaxAlunos = qtdMaxAlunos;
        this.alunosMatriculados = listaAlunosMatriculados.size();
        this.gabarito = null;
    }

   

    // metodo string que retorna o status da disciplina
    public String statusDisciplina() {
        return ("Número da disciplina: " + this.numeroDisciplina + "\n" + "Nome da disciplina: " + this.nomeDisciplina
                + "\n" + "Quantidade de vagas: " + this.qtdMaxAlunos + "\n" + "Quantidade de alunos matriculados: "
                + this.alunosMatriculados + "\n");
    }

    public void receberGabarito() {
        FileReader fr;
        try {
            fr = new FileReader(nomeDisciplina + "\\gabarito.txt");

            BufferedReader br = new BufferedReader(fr);

            setGabarito(br.readLine());
            String linha = br.readLine();
            while (linha != null) {
                linha = br.readLine();
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    // matricular aluno na disciplina
    public boolean matricularAluno() {
        if (this.alunosMatriculados < this.qtdMaxAlunos) {
            System.out.println("Digite o nome do aluno: ");
            String addNome = scan.next();

            Aluno a = new Aluno(addNome);
            listaAlunosMatriculados.add(a);
            a.setMatriculado(true);
            a.setNumeroMatricula(listaAlunosMatriculados.size() - 1);
            System.out.println("Aluno matriculado com sucesso! ");
            return true;
        } else {
            System.out.println(
                    "Não é possível realizar a matrícula pois a disciplina já está lotada ou aluno já está matriculado em outra disciplina!");
            return false;
        }
    }

    // metodo para imprimir lista dos alunos matriculados na disciplina
    public void imprimirListaAlunosMatriculados() {
        if (listaAlunosMatriculados.isEmpty()) {
            System.out.println("Não há alunos matriculados ainda");
            System.out.println("---------------------------");
        } else {
            for (int i = 0; i < listaAlunosMatriculados.size(); i++) {
                System.out.println(listaAlunosMatriculados.get(i).imprimirDadosAluno(this.numeroDisciplina));
                System.out.println("---------------------------");

            }
        }
    }

    // atribuir respostas do aluno
    public void atribuirRespostaAlunos(Aluno a) {
        System.out.println("Digite as respostas do Aluno " + a.getNome());
        System.out.println("ATENÇÃO! Será levado em consideração apenas respostas V ou F e apenas 10 caracteres!");
        a.setRespostas(scan.next());
        // a.setRespostas(addRespostas);
        for (int i = 0; i < alunosMatriculados; i++) {
            if (getGabarito().regionMatches(true, i + 1, a.getRespostas(), i, 1)) {
                a.setNotas(a.getNotas() + 1);
            }
        }
        System.out.println(a.getNome() + " " + a.getNotas());

        try {
            FileWriter fw;
            fw = new FileWriter(nomeDisciplina + "\\respostas.txt", true);

            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(a.getRespostas() + " " + a.getNome());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void atribuirRespostasAlunos() {

        try {
            FileWriter fw;
            fw = new FileWriter(nomeDisciplina + "\\respostas.txt", true);

            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < listaAlunosMatriculados.size(); i++) {
                System.out.println("Atribua as respostas do aluno " + listaAlunosMatriculados.get(i).getNome() + ": ");
                listaAlunosMatriculados.get(i).setRespostas(scan.next());
                // listaAlunosMatriculados.get(i).setRespostas(respostasExe);
            }

            for (int i = 0; i < this.listaAlunosMatriculados.size(); i++) {
                bw.write(
                        listaAlunosMatriculados.get(i).getRespostas() + " " + listaAlunosMatriculados.get(i).getNome());
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (java.lang.NullPointerException e) {
            System.out.println("Notas atribuidas com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atribuirNotas() {

        for (int i = 0; i < listaAlunosMatriculados.size(); i++) {
            if (listaAlunosMatriculados.get(i).getRespostas().equals("VVVVVVVVVV")
                    || listaAlunosMatriculados.get(i).getRespostas().equals("FFFFFFFFFF")) {
                listaAlunosMatriculados.get(i).setNotas(0.0);
                System.out.println("tentou chutar né fella, favela perdeu :(");
            } else {
                System.out.println(listaAlunosMatriculados.get(i).getRespostas());
                for (int j = 0; j < 10; j++) {

                    if (getGabarito().regionMatches(true, j, listaAlunosMatriculados.get(i).getRespostas(), j, 1)) {
                        listaAlunosMatriculados.get(i).setNotas(listaAlunosMatriculados.get(i).getNotas() + 1);
                    }
                }
            }
            System.out.println(
                    listaAlunosMatriculados.get(i).getNome() + " " + listaAlunosMatriculados.get(i).getNotas());
        }
    }

    public void gerarRelatorioOrdemAlfabetica() {
        try {
            FileWriter fw;
            fw = new FileWriter(nomeDisciplina + "\\relatorioOrdemAlfabetica.txt");

            BufferedWriter bw = new BufferedWriter(fw);
            Aluno auxiliar;
            bw.write("=== Alunos e suas respectivas notas em ordem Alfabetica");
            bw.newLine();

            for (int i = 0; i < this.listaAlunosMatriculados.size(); i++) {
                for (int j = i + 1; j < this.listaAlunosMatriculados.size(); j++) {
                    if (this.listaAlunosMatriculados.get(i).getNome()
                            .compareTo(this.listaAlunosMatriculados.get(j).getNome()) > 0) {
                        auxiliar = this.listaAlunosMatriculados.get(i);
                        this.listaAlunosMatriculados.set(i, listaAlunosMatriculados.get(j));
                        this.listaAlunosMatriculados.set(j, auxiliar);
                    }
                }
            }

            for (int i = 0; i < listaAlunosMatriculados.size(); i++) {
                bw.write(this.listaAlunosMatriculados.get(i).imprimirDadosAluno(this.numeroDisciplina));
                bw.newLine();
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gerar relatorios da disciplina
    public void gerarRelatorioOrdemDecrescente() {
        try {
            FileWriter fw;
            fw = new FileWriter(
                    nomeDisciplina + "\\relatorioNotasDecrescente.txt");

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("=== Alunos e suas respecivas notas em ordem decrescente ===");
            bw.newLine();
            Aluno aux;
            for (int cont = 0; cont < this.listaAlunosMatriculados.size(); cont++) {
                for (int i = 0; i < this.listaAlunosMatriculados.size() - 1; i++) {
                    if (this.listaAlunosMatriculados.get(i).getNotas() < this.listaAlunosMatriculados.get(i + 1)
                            .getNotas()) {
                        aux = this.listaAlunosMatriculados.get(i);
                        this.listaAlunosMatriculados.set(i, this.listaAlunosMatriculados.get(i + 1));
                        this.listaAlunosMatriculados.set(i + 1, aux);
                    }
                }
            }
            for (int i = 0; i < this.listaAlunosMatriculados.size(); i++) {
                bw.write(this.listaAlunosMatriculados.get(i).imprimirDadosAluno(this.numeroDisciplina));
                bw.newLine();
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodos getters and setters
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getNumeroDisciplina() {
        return numeroDisciplina;
    }

    public void setNumeroDisciplina(int numeroDisciplina) {
        this.numeroDisciplina = numeroDisciplina;
    }

    public int getQtdMaxAlunos() {
        return qtdMaxAlunos;
    }

    public void setQtdMaxAlunos(int qtdMaxAlunos) {
        this.qtdMaxAlunos = qtdMaxAlunos;
    }

    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(int alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }
}
