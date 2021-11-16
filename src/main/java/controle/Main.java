package controle;

import dominio.Aluno;
import dominio.Pessoa;
import servico.AlunoServico;
import servico.PessoaServico;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Lista de pessoas e alunos
        ArrayList <Pessoa> pessoas = new ArrayList<>();
        ArrayList <Aluno> alunos = new ArrayList<>();

        // Objetos de serviço
        PessoaServico pessoaServico = new PessoaServico();
        AlunoServico alunoServico = new AlunoServico();

        Scanner scan = new Scanner(System.in);
        int op1, op2, op3;

        // Menus
        do{
            System.out.println("---------- MENU ----------");
            System.out.println("1 - Pessoa");
            System.out.println("2 - Aluno");
            System.out.println("3 - Sair");
            System.out.println("--------------------------");
            System.out.print("Escolha uma opção: ");
            op1 = scan.nextInt();

            String nome, cpf, matricula;
            ArrayList<Pessoa> listaPessoas;
            ArrayList<Aluno> listaAlunos;
            Pessoa pessoa;
            Aluno auxA, aluno;
            int idA, anoEntrada;

            switch (op1){
                case 1:
                    do {
                        System.out.println("\n---------- PESSOA ----------");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Remover");
                        System.out.println("4 - Listar");
                        System.out.println("5 - Voltar");
                        System.out.println("----------------------------");
                        System.out.print("Escolha uma opção: ");
                        op2 = scan.nextInt();
                        scan.nextLine();
                        System.out.println();

                        switch (op2) {
                            case 1: // Inserir pessoa
                                System.out.print("Digite o CPF: ");
                                scan.nextLine();
                                cpf = scan.nextLine();

                                // Verifica a existência da pessoa antes de continuar
                                if(!pessoaServico.existePessoa(cpf)){
                                    System.out.print("Digite o nome: ");
                                    nome = scan.nextLine();

                                    Pessoa novaP = new Pessoa(nome, cpf);
                                    if(pessoaServico.inserir(novaP)){
                                        System.out.println("\nInserção realizada com sucesso!");
                                    }else{
                                        System.out.println("\nErro inesperado ao inserir pessoa!");
                                    }

                                }else{
                                    System.out.println("\nNão foi possível inserir essa pessoa. Este CPF já foi cadastrado!");
                                }
                                break;

                            case 2: // Alterar pessoa
                                System.out.print("Digite o CPF da pessoa que deseja alterar: ");
                                cpf = scan.nextLine();

                                // Verifica a existência da pessoa antes de continuar
                                pessoa = pessoaServico.buscaPorCPF(cpf);
                                if (pessoa != null) {
                                    System.out.println("\nQue campo deseja alterar?");
                                    System.out.println("1 - Nome: " + pessoa.getNome());
                                    System.out.println("2 - CPF: " + pessoa.getCPF());
                                    System.out.println("3 - Cancelar");
                                    System.out.println("-----------------------------------");
                                    System.out.print("Escolha uma opção: ");
                                    op3 = scan.nextInt();
                                    scan.nextLine();
                                    System.out.println();

                                    switch (op3) {
                                        case 1: // Nome
                                            System.out.print("Digite o novo nome: ");
                                            nome = scan.nextLine();
                                            pessoa.setNome(nome);

                                            if(pessoaServico.altera(pessoa)){
                                                System.out.println("\nNome alterado com sucesso!");
                                            }else{
                                                System.out.println("\nErro inesperado ao alterar CPF!");
                                            }
                                            break;

                                        case 2: // CPF
                                            System.out.print("Digite o novo CPF: ");
                                            cpf = scan.nextLine();

                                            // Verifica se CPF não está cadastrado antes de continuar
                                            if (!pessoaServico.existePessoa(cpf)) {
                                                pessoa.setCPF(cpf);

                                                if(pessoaServico.altera(pessoa)) {
                                                    System.out.println("\nCPF alterado com sucesso!");
                                                }else{
                                                    System.out.println("\nErro inesperado ao alterar nome!");
                                                }

                                            }else{
                                                System.out.println("\nEste CPF já está cadastrado. Tente novamente!");
                                            }
                                            break;

                                        case 3: // Cancelar
                                            System.out.println("Operação cancelada!");
                                            break;

                                        default: break;
                                    }
                                }else{
                                    System.out.println("\nNão existe pessoa com o CPF informado. Tente novamente!");
                                }
                                break;

                            case 3: // Remover pessoa
                                System.out.print("Digite o CPF da pessoa que deseja remover: ");
                                cpf = scan.nextLine();
                                System.out.println(cpf);

                                // Verifica a existência da pessoa antes de continuar
                                pessoa = pessoaServico.buscaPorCPF(cpf);
                                if(pessoa != null){

                                    // TODO Fazer verificação dos alunos associados

                                    System.out.println("\nDados do cadastro:");
                                    System.out.println("Nome: " + pessoa.getNome());
                                    System.out.println("CPF: " + pessoa.getCPF());
                                    System.out.println("-----------------------------------");
                                    System.out.print("Deseja mesmo remover essa pessoa (1 - Sim, 2 - Não)? ");
                                    op3 = scan.nextInt();

                                    if(op3 == 1){
                                        if(pessoaServico.remover(pessoa)){
                                            System.out.println("\nRemoção realizada com sucesso!");
                                        }else{
                                            System.out.println("\nErro inesperado ao remover pessoa!");
                                        }

                                    }else{
                                        System.out.println("\nOperação cancelada!");
                                    }

                                }else{
                                    System.out.println("\nNão existe pessoa com o CPF informado. Tente novamente!");
                                }
                                break;

                            case 4: // Listar pessoas
                                listaPessoas = pessoaServico.getPessoas();
                                if(!listaPessoas.isEmpty()){
                                    for (Pessoa p : listaPessoas) {
                                        System.out.println("--------------------------");
                                        System.out.println("Nome: " + p.getNome());
                                        System.out.println("CPF: " + p.getCPF());
                                    }
                                    System.out.println("--------------------------");
                                }else{
                                    System.out.println("Não existem pessoas cadastradas!");
                                }
                                break;

                            case 5: // Voltar
                                break;

                            default:
                                System.out.println("Digite uma opção válida!");
                        }
                    }while (op2 != 5);
                    break;

                case 2:
                    do {
                        System.out.println("\n---------- ALUNO ----------");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Remover ");
                        System.out.println("4 - Listar");
                        System.out.println("5 - Voltar");
                        System.out.println("---------------------------");
                        System.out.print("Escolha uma opção: ");
                        op2 = scan.nextInt();
                        scan.nextLine();
                        System.out.println();

                        switch (op2) {
                            case 1: // Inserir aluno
                                System.out.print("Digite o CPF da pessoa associada ao aluno: ");
                                cpf = scan.nextLine();

                                // Verifica a existência da pessoa antes de continuar
                                pessoa = pessoaServico.buscaPorCPF(cpf);
                                if (pessoa != null) {
                                    System.out.print("Digite a matrícula: ");
                                    matricula = scan.nextLine();

                                    // Verifica a existência do aluno antes de continuar
                                    if(!alunoServico.existeAluno(matricula)){
                                        System.out.print("Digite o ano de entrada: ");
                                        anoEntrada = scan.nextInt();

                                        Aluno novoA = new Aluno(pessoa, matricula, anoEntrada);
                                        if(alunoServico.inserir(novoA)) {
                                            System.out.println("\nInserção realizada com sucesso!");
                                        }else{
                                            System.out.println("\nErro inesperado ao inserir aluno!");
                                        }

                                    }else{
                                        System.out.println("\nNão foi possível inserir esse aluno. Esta matrícula já foi cadastrada!");
                                    }

                                } else {
                                    System.out.println("\nNão foi possível inserir o aluno. Não existe pessoa com o CPF informado!");
                                }
                                break;

                            case 2: // Alterar aluno
                                System.out.print("Digite a matrícula do aluno que deseja alterar: ");
                                matricula = scan.nextLine();

                                // Verifica a existência do aluno antes de continuar
                                aluno = alunoServico.buscaPorMatricula(matricula);
                                if(aluno != null){
                                    System.out.println("\nQue campo deseja alterar?");
                                    System.out.println("1 - Pessoa vinculada (CPF): " + aluno.getPessoa().getCPF());
                                    System.out.println("2 - Matrícula: " + aluno.getMatricula());
                                    System.out.println("3 - Ano de entrada: " + aluno.getAnoEntrada());
                                    System.out.println("4 - Cancelar");
                                    System.out.println("-----------------------------------");
                                    System.out.print("Escolha uma opção: ");
                                    op3 = scan.nextInt();
                                    scan.nextLine();
                                    System.out.println();

                                    switch (op3) {
                                        case 1: // CPF da Pessoa
                                            System.out.print("Digite o CPF da pessoa para qual deseja alterar o vínculo: ");
                                            cpf = scan.nextLine();

                                            // Verifica se o CPF é valido
                                            pessoa = pessoaServico.buscaPorCPF(cpf);
                                            if(pessoa != null){
                                                aluno.setPessoa(pessoa);

                                                if(alunoServico.altera(aluno)){
                                                    System.out.println("\nPessoa vinculada alterada com sucesso!");
                                                }else{
                                                    System.out.println("\nErro inesperado ao alterar a pessoa vinculada a este aluno!");
                                                }

                                            }else{
                                                System.out.println("\nNão existe pessoa com o CPF informado. Tente novamente!");
                                            }
                                            break;

                                        case 2: // Matrícula
                                            System.out.print("Digite a nova matrícula: ");
                                            matricula = scan.nextLine();

                                            // Verifica se a matrícula não está cadastrada antes de continuar
                                            if(!alunoServico.existeAluno(matricula)){
                                                aluno.setMatricula(matricula);

                                                if(alunoServico.altera(aluno)) {
                                                    System.out.println("\nMatrícula alterada com sucesso!");
                                                }else{
                                                    System.out.println("\nErro inesperado ao alterar matrícula");
                                                }

                                            }else{
                                                System.out.println("\nEsta matrícula já está cadastrada. Tente novamente!");
                                            }
                                            break;

                                        case 3: // Ano de entrada
                                            System.out.print("Digite o novo ano de entrada: ");
                                            anoEntrada = scan.nextInt();

                                            aluno.setAnoEntrada(anoEntrada);

                                            if(alunoServico.altera(aluno)){
                                                System.out.println("\nAno de entrada alterado com sucesso!");
                                            }else{
                                                System.out.println("\nErro inesperado ao alterar o ano de entrada!");
                                            }
                                            break;

                                        case 4: // Cancelar
                                            System.out.println("Operação cancelada!");
                                            break;
                                    }
                                }else{
                                    System.out.println("\nNão existe aluno com a matrícula informada. Tente novamente!");
                                }
                                break;

                            case 3: // Remover aluno
                                System.out.print("Digite o ID do aluno que deseja remover: ");
                                idA = scan.nextInt();

                                // Verifica a existência do aluno
                                auxA = null;
                                for (Aluno a : alunos) {
                                    if (idA == a.getId()) {
                                        auxA = a;
                                        break;
                                    }
                                }
                                if (auxA != null) {
                                    System.out.println("\nDados do cadastro:");
                                    System.out.println("ID de Pessoa: " + auxA.getPessoa().getId());
                                    System.out.println("Matrícula: " + auxA.getMatricula());
                                    System.out.println("Ano de entrada: " + auxA.getAnoEntrada());
                                    System.out.println("-----------------------------------");
                                    System.out.print("Deseja mesmo remover esse aluno (1 - Sim, 2 - Não)? ");
                                    op3 = scan.nextInt();
                                    System.out.println();

                                    if (op3 == 1) {
                                        alunos.remove(auxA);
                                        System.out.println("\nAluno removido com sucesso!");
                                    } else {
                                        System.out.println("\nOperação cancelada!");
                                    }
                                }else{
                                    System.out.println("\nNão existe aluno com o ID informado. Tente novamente!");
                                }
                                break;

                            case 4: // Listar alunos
                                listaAlunos = alunoServico.getAlunos();
                                if(!listaAlunos.isEmpty()){
                                    for (Aluno a : listaAlunos) {
                                        System.out.println("--------------------------");
                                        System.out.println("Pessoa vinculada (CPF): " + a.getPessoa().getCPF());
                                        System.out.println("Matrícula: " + a.getMatricula());
                                        System.out.println("Ano de Entrada: " + a.getAnoEntrada());
                                    }
                                    System.out.println("--------------------------");
                                }else{
                                    System.out.println("Não existem alunos cadastrados!");
                                }
                                break;

                            case 5: // Voltar
                                break;

                            default:
                                System.out.println("Digite uma opção válida!");
                        }
                    }while (op2 != 5);
                    break;

                case 3: // Sair
                    System.out.println("\nPROGRAMA ENCERRADO!");
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;
            }
        }while (op1 != 3);

        pessoaServico.fechaEntidades();
        alunoServico.fechaEntidades();

    }
}
