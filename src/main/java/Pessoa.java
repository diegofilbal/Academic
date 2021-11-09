import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "pessoa_seq")
    @SequenceGenerator(name = "pessoa_seq", schema = "comum", sequenceName = "pessoa_seq", allocationSize = 1)
    private int id;

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "cpf")
    private String cpf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa")
    private List<Aluno> aluno;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }
}
