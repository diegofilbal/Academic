import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "aluno_seq")
    @SequenceGenerator(name = "aluno_seq", schema = "graduacao", sequenceName = "pes_seq", allocationSize = 1)
    private int id;


    private int idPessoa;

    @Basic
    @Column(name = "matricula")
    private String matricula;

    @Basic
    @Column(name = "ano_entrada")
    private String anoEntrada;

    public Aluno() {
    }

    public Aluno(int id, int idPessoa, String matricula, String anoEntrada) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.matricula = matricula;
        this.anoEntrada = anoEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
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
