public class Aluno {
    private String nome;
    private int numeroMatricula;
    private double notas;
    private String respostas;
    private boolean matriculado;

    // constructor
    public Aluno(String nome) {
        this.nome = nome;
        this.numeroMatricula = -1;
        this.matriculado = false;
        this.notas = 0;

    }

    // metodo retornar dados do aluno:
    public String imprimirDadosAluno(int numeroDisciplina) {
        if (matriculado) {
            return ("Nome: " + this.nome + "\n" + "Matricula: " + this.numeroMatricula + "\n" + "Notas: " + this.notas);
        } else {
            return ("Nome: " + this.nome + "\n" + "Matricula: " + this.numeroMatricula + "\n");
        }
    }

    // metodos getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }

    public double getNotas() {
        return notas;
    }

    public void setNotas(Double notas) {
        this.notas = notas;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }
}