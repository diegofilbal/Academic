import javax.persistence.*;

@Entity
@Table( schema = "graduacao", name = "aluno")
public class Aluno {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "aluno_seq")
    @SequenceGenerator(name = "aluno_seq", schema = "graduacao", sequenceName = "pes_seq", allocationSize = 1)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Basic
    @Column(name = "matricula")
    private String matricula;

    @Basic
    @Column(name = "ano_entrada")
    private String anoEntrada;

    public Aluno() {
    }

    public Aluno(int id, Pessoa p, String matricula, String anoEntrada) {
        this.id = id;
        this.pessoa = p;
        this.matricula = matricula;
        this.anoEntrada = anoEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(String anoEntrada) {
        this.anoEntrada = anoEntrada;
    }
}
