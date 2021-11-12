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

            String nome, cpf, matricula, anoEntrada;
            ArrayList<Pessoa> listaPessoas;
            ArrayList<Aluno> listaAlunos;
            Pessoa auxP, pessoa;
            Aluno auxA;
            int idP, idA;
            boolean flag;

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
                                //scan.nextLine();
                                cpf = scan.nextLine();

                                pessoa = pessoaServico.buscaPorCPF(cpf);

                                // Verifica a existência da pessoa antes de continuar
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

                                            if(pessoaServico.alteraPessoa(pessoa)){
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

                                                if(pessoaServico.alteraPessoa(pessoa)) {
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
                                    System.out.println("\nNão existe pessoa com o ID informado. Tente novamente!");
                                }
                                break;

                            case 3: // Remover pessoa
                                System.out.print("Digite o CPF da pessoa que deseja remover: ");
                                cpf = scan.nextLine();
                                System.out.println(cpf);

                                // Verifica a existência da pessoa antes de continuar
                                pessoa = pessoaServico.buscaPorCPF(cpf);
                                if(pessoa != null){

                                    /*
                                     * Fazer verificação dos alunos associados
                                     */

                                    System.out.println("\nDados do cadastro:");
                                    System.out.println("ID: " + pessoa.getId());
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
                                        System.out.println("ID: " + p.getId());
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
                        System.out.println();

                        switch (op2) {
                            case 1: // Inserir aluno
                                System.out.print("Digite o ID da pessoa associada ao aluno: ");
                                idP = scan.nextInt();

                                // Verifica a existência da pessoa
                                auxP = null;
                                for (Pessoa p : pessoas) {
                                    if (idP == p.getId()) {
                                        auxP = p;
                                        break;
                                    }
                                }

                                if (auxP != null) {
                                    System.out.print("Digite o ID do aluno (tmp): ");
                                    idA = scan.nextInt();
                                    scan.nextLine();
                                    System.out.print("Digite a matrícula: ");
                                    matricula = scan.nextLine();
                                    System.out.print("Digite o ano de entrada (DD-MM-AAAA): ");
                                    anoEntrada = scan.nextLine();

                                    // Verifica a existência do aluno
                                    auxA = null;
                                    for (Aluno a : alunos) {
                                        if (idA == a.getId() || matricula.equals(a.getMatricula())) {
                                            auxA = a;
                                            break;
                                        }
                                    }
                                    System.out.println();

                                    if (auxA == null) {
                                        Aluno novoA = new Aluno(idA, auxP, matricula, anoEntrada);
                                        alunos.add(novoA);
                                        System.out.println("Inserção realizada com sucesso!");

                                    } else {
                                        System.out.println("Não foi possível inserir esse aluno. Esta matrícula/ID(tmp) já foi cadastrada!");
                                    }

                                } else {
                                    System.out.println("\nNão foi possível inserir esse aluno. O ID de pessoa informado não existe!");
                                }
                                break;

                            case 2: // Alterar aluno
                                System.out.print("Digite o ID do aluno que deseja alterar: ");
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
                                    System.out.println("\nQue campo deseja alterar?");
                                    System.out.println("1 - ID da Pessoa: " + auxA.getPessoa().getId());
                                    System.out.println("2 - Matrícula: " + auxA.getMatricula());
                                    System.out.println("3 - Ano de entrada: " + auxA.getAnoEntrada());
                                    System.out.println("4 - Cancelar");
                                    System.out.println("-----------------------------------");
                                    System.out.print("Escolha uma opção: ");
                                    op3 = scan.nextInt();
                                    scan.nextLine();
                                    System.out.println();

                                    switch (op3) {
                                        case 1: // ID da Pessoa
                                            System.out.print("Digite o novo ID de Pessoa: ");
                                            idP = scan.nextInt();

                                            // Verifica se o ID é valido
                                            auxP = null;
                                            for (Pessoa p : pessoas) {
                                                if (idP == p.getId()) {
                                                    auxP = p;
                                                    break;
                                                }
                                            }
                                            if (auxP != null) {
                                                auxA.setPessoa(auxP);
                                                System.out.println("\nID de Pessoa alterado com sucesso!");
                                            } else {
                                                System.out.println("\nNão existe pessoa com o ID informado. Tente novamente!");
                                            }
                                            break;

                                        case 2: // Matrícula
                                            System.out.print("Digite a nova matrícula: ");
                                            matricula = scan.nextLine();

                                            // Verifica se a matrícula não está cadastrada
                                            flag = false;
                                            for (Aluno a : alunos) {
                                                if (matricula.equals(a.getMatricula())) {
                                                    flag = true;
                                                    break;
                                                }
                                            }
                                            if (!flag) {
                                                auxA.setMatricula(matricula);
                                                System.out.println("\nMatrícula alterada com sucesso!");
                                            } else {
                                                System.out.println("\nEsta matrícula já está cadastrada. Tente novamente!");
                                            }
                                            break;

                                        case 3: // Ano de entrada
                                            System.out.print("Digite o novo ano de entrada (DD-MM-AAAA): ");
                                            anoEntrada = scan.nextLine();
                                            auxA.setAnoEntrada(anoEntrada);
                                            System.out.println("\nAno de entrada alterado com sucesso!");
                                            break;

                                        case 4: // Cancelar
                                            System.out.println("Operação cancelada!");
                                            break;
                                    }
                                }else{
                                    System.out.println("\nNão existe aluno com o ID informado. Tente novamente!");
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
                                if (alunos.isEmpty()) {
                                    System.out.println("Não existem alunos cadastrados!");
                                } else {
                                    for (Aluno a : alunos) {
                                        System.out.println("--------------------------");
                                        System.out.println("ID Aluno: " + a.getId());
                                        System.out.println("ID Pessoa: " + a.getPessoa().getId());
                                        System.out.println("Matrícula: " + a.getMatricula());
                                        System.out.println("Ano de Entrada: " + a.getAnoEntrada());
                                    }
                                    System.out.println("--------------------------");
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

    }
}