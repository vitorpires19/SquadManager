import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class SquadManager {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        String nomeClube = "";
        String campeonatos = "";
        String temporada = "";
        File arquivoElenco = new File("elenco.csv");
        File arquivoHistorico = new File("historico.csv");
        if (!arquivoElenco.exists() && !arquivoHistorico.exists()){
            System.out.println("Nenhum arquivo de carreira encontrado.");
            System.out.println("Deseja iniciar uma nova carreira? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.println("Nova carreira autorizada!");
                try {
                    arquivoElenco.createNewFile();
                    arquivoHistorico.createNewFile();

                    System.out.println("Arquivos da nova carreira criados!");
                } catch (IOException e) {
                    System.out.println("Erro ao criar nova carreira: " + e.getMessage());
                    return;
                }
            } else {
                System.out.println("Inicialização cancelada pelo usuário.");
                return;
            }
        }
        System.out.println("Elenco existe? " + arquivoElenco.exists());
        System.out.println("Histórico existe? " + arquivoHistorico.exists());
        Jogador[] elenco = new Jogador[30];
        int quantidadeJogadores = 0;
        boolean elencoCarregado = false;
        Competicao[] historico = new Competicao[100];
        int quantidadeCompeticoes = 0;
        boolean historicoCarregado = false;
        try (BufferedReader leitor = new BufferedReader(new FileReader("elenco.csv"))) {

            String linha = leitor.readLine();

            while (linha != null) {

                String[] dados = linha.split(";", -1);
                if (dados.length != 4) {
                    throw new IllegalArgumentException("Linha inválida no elenco.csv: " + linha);
                }
                if (dados[0].isBlank() || dados[1].isBlank() || dados[2].isBlank() || dados[3].isBlank()) {
                    throw new IllegalArgumentException("Campo vazio no elenco.csv: " + linha);
                }
                if (quantidadeJogadores < elenco.length) {

                    int idadeCarregada = Integer.parseInt(dados[1]);
                    int overallCarregado = Integer.parseInt(dados[3]);

                    if (idadeCarregada < 1 || idadeCarregada > 100 || overallCarregado < 1 || overallCarregado > 99) {
                        throw new IllegalArgumentException("Idade ou overall inválido no elenco.csv: " + linha);
                    }

                    Jogador jogadorCarregado =
                            new Jogador(dados[0], idadeCarregada, dados[2], overallCarregado);

                    elenco[quantidadeJogadores] = jogadorCarregado;
                    quantidadeJogadores++;
                }

                linha = leitor.readLine();
            }

            elencoCarregado = true;
            System.out.println("Jogadores carregados: " + quantidadeJogadores);

        } catch (IOException e) {
            System.out.println("Erro ao carregar elenco: " + e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Erro nos dados numéricos do elenco: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
                System.out.println("Erro no formato do elenco: " + e.getMessage());
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader("historico.csv"))) {

            String linha = leitor.readLine();

            while (linha != null) {

                String[] dados = linha.split(";", -1);
                if (dados.length != 3) {
                    throw new IllegalArgumentException("Linha inválida no historico.csv: " + linha);
                    }
                if (dados[0].isBlank() || dados[1].isBlank() || dados[2].isBlank()) {
                    throw new IllegalArgumentException("Campo vazio no historico.csv: " + linha);
                }

                if (quantidadeCompeticoes < historico.length) {

                    Competicao competicaoCarregada = new Competicao(dados[0],dados[1] , dados[2]);

                    historico[quantidadeCompeticoes] = competicaoCarregada;
                    quantidadeCompeticoes++;
                }

                linha = leitor.readLine();
            }

            historicoCarregado = true;
            System.out.println("Competições carregadas: " + quantidadeCompeticoes);

        } catch (IOException e) {
            System.out.println("Erro ao carregar historico: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Erro no formato do historico: " + e.getMessage());
        }
        String nomeJogador = "";
        int idade = 0;
        String posicao = "";
        int overall = 0;
        int opcaoElenco = 0;
        String nomePesquisa = "";
        int novoOverall = 0;
        String posicaoPesquisa = "";
        int opcaoTransferencia;
        String jogadorContratado = "";
        int opcaoHistorico;
        String temporadaHistorico = "";
        String campeonatoHistorico = "";
        String resultadoCompeticao = "";


        do {
            System.out.println("Seja bem-vindo(a) ao Squad Manager. Selecione sua opçao para prosseguir: \n"
                    + "1 - Gerenciar clube\n"
                    + "2 - Gerenciar elenco\n"
                    + "3 - Mercado de transferencias\n"
                    + "4 - Historico da carreira\n"
                    + "5 - Salvar carreira\n"
                    + "0 - Encerrar programa\n"
                    + "\n"
                    + "Escolha uma opcao:");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Voce escolheu: " + opcao);
            } else {
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }

            if (opcao == 1) {
                System.out.println("Vamos gerenciar seu clube");
                if (nomeClube.isEmpty()) {
                    System.out.println("Escreva o nome de seu time: ");
                    nomeClube = scanner.nextLine();
                    System.out.println("Clube cadastrado: " + nomeClube);
                    System.out.println("Qual a temporada atual?");
                    temporada = scanner.nextLine();
                    System.out.println("Temporada cadastrada: " + temporada);
                    System.out.println("Quais campeonatos disputados na temporada?");
                    campeonatos = scanner.nextLine();
                    System.out.println("Campeonatos cadastrados: " + campeonatos);
                } else {
                    System.out.println("CLUBE ATUAL\n" + "Clube: " + nomeClube);
                    System.out.println("TEMPORADA ATUAL\n" + "Temporada: " + temporada);
                    System.out.println("CAMPEONATOS\n" + "Campeonatos: " + campeonatos);
                }


            } else if (opcao == 2) {
                do {
                    System.out.println("GERENCIAMENTO DE ELENCO: \n" + "1 - Cadastrar jogador\n"
                            + "2 - Listar elenco\n"
                            + "3 - Pesquisar Jogador\n"
                            + "4 - Editar jogador\n"
                            + "5 - Remover jogador\n"
                            + "6 - Filtro de elenco\n"
                            + "0 - Voltar ao menu principal\n"
                            + "Escolha uma das opções: ");
                    if (scanner.hasNextInt()) {
                        opcaoElenco = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Voce escolheu: " + opcaoElenco);
                    } else {
                        System.out.println("Opção inválida! Digite um número.");
                        scanner.nextLine();
                        opcaoElenco = -1;
                        continue;
                    }
                    if (opcaoElenco == 1) {
                        if (quantidadeJogadores < elenco.length) {
                            System.out.println("Nome do jogador: ");
                            nomeJogador = scanner.nextLine();
                            boolean idadeValida = false;
                            do {
                                System.out.println("Idade do jogador: ");
                                if (scanner.hasNextInt()) {
                                    idade = scanner.nextInt();
                                    scanner.nextLine();
                                    if (idade >= 1 && idade <= 100) {
                                        idadeValida = true;
                                    } else {
                                        System.out.println("Idade inválida! Digite um número entre 1 e 100.");
                                    }
                                } else {
                                    System.out.println("Idade inválida! Digite um número entre 1 e 100.");
                                    scanner.nextLine();
                                }
                            } while (!idadeValida);
                            System.out.println("Digite a posicao: ");
                            posicao = scanner.nextLine();
                            boolean overallValido = false;
                            do {
                                System.out.println("Digite o overall: ");
                                if (scanner.hasNextInt()) {
                                    overall = scanner.nextInt();
                                    scanner.nextLine();
                                    if (overall >= 1 && overall <= 99) {
                                        overallValido = true;
                                    } else {
                                        System.out.println("Overall inválido! Digite um número entre 1 e 99.");
                                    }
                                } else {
                                    System.out.println("Overall inválido! Digite um número entre 1 e 99.");
                                    scanner.nextLine();
                                }
                            } while (!overallValido);
                            System.out.println("Jogador: " + nomeJogador
                                    + "\nIdade: "
                                    + idade
                                    + "\nPosicao: "
                                    + posicao
                                    + "\nOverall: "
                                    + overall);
                            Jogador novoJogador = new Jogador(nomeJogador, idade, posicao, overall);
                            elenco[quantidadeJogadores] = novoJogador;
                            quantidadeJogadores++;
                        } else {
                            System.out.println("Elenco completo!");
                        }
                    } else if (opcaoElenco == 2) {
                        if (quantidadeJogadores == 0) {
                            System.out.println("Nenhum jogador cadastrado.");
                        } else {
                            for (int i = 0; i < quantidadeJogadores; i++) {
                                System.out.println("JOGADOR " + (i + 1) + "\n" + "Nome: " + elenco[i].getNome());
                                System.out.println("Idade: " + elenco[i].getIdade());
                                System.out.println("Posicao: " + elenco[i].getPosicao());
                                System.out.println("Overall: " + elenco[i].getOverall());
                            }
                        }
                    } else if (opcaoElenco == 3) {
                        System.out.println("Digite o nome do jogador: ");
                        nomePesquisa = scanner.nextLine();
                        if (quantidadeJogadores == 0) {
                            System.out.println("Nenhum jogador cadastrado!");
                        } else {
                            boolean jogadorEncontrado = false;
                            for (int i = 0; i < quantidadeJogadores; i++) {
                                if (elenco[i].getNome().equalsIgnoreCase(nomePesquisa)) {
                                    jogadorEncontrado = true;
                                    System.out.println("Jogador pesquisado: "
                                            + "Nome: "
                                            + elenco[i].getNome()
                                            + "\n"
                                            + "Overall: "
                                            + elenco[i].getOverall());
                                }
                            }
                            if (jogadorEncontrado == false) {
                                System.out.println("Jogador não encontrado no elenco!");
                            }
                        }
                    } else if (opcaoElenco == 4) {
                        System.out.println("Digite o jogador desejado: ");
                        nomePesquisa = scanner.nextLine();
                        boolean jogadorEncontrado = false;
                        for (int i = 0; i < quantidadeJogadores; i++) {
                            if (elenco[i].getNome().equalsIgnoreCase(nomePesquisa)) {
                                jogadorEncontrado = true;
                                System.out.println("Jogador encontrado para edição\n"
                                        + "Nome: "
                                        + elenco[i].getNome()
                                        + "\nOverall atual: "
                                        + elenco[i].getOverall());
                                boolean novoOverallValido = false;
                                do {
                                    System.out.println("Digite o novo overall: ");
                                    if (scanner.hasNextInt()) {
                                        novoOverall = scanner.nextInt();
                                        scanner.nextLine();
                                        if (novoOverall >= 1 && novoOverall <= 99) {
                                            novoOverallValido = true;
                                        } else {
                                            System.out.println("Novo overall inválido! Digite um número entre 1 e 99.");
                                        }
                                    } else {
                                        System.out.println("Novo overall inválido! Digite um número entre 1 e 99.");
                                        scanner.nextLine();
                                    }
                                } while (!novoOverallValido);
                                elenco[i].setOverall(novoOverall);
                                System.out.println("Overall atualizado com sucesso: \n" + "Novo overall: " + elenco[i].getOverall());
                            }
                        }
                        if (jogadorEncontrado == false) {
                            System.out.println("Jogador nao encontrado no elenco");
                        }
                    } else if (opcaoElenco == 5) {
                        System.out.println("Digite o jogador que deseja remover: ");
                        nomePesquisa = scanner.nextLine();
                        boolean jogadorRemovido = false;
                        System.out.println("Jogador selecionado para remoção: " + nomePesquisa);
                        for (int i = 0; i < quantidadeJogadores; i++) {
                            if (elenco[i].getNome().equalsIgnoreCase(nomePesquisa)) {
                                System.out.println("Jogador encontrado: " + elenco[i].getNome() + "\nPosicao no vetor: " + i);
                                for (int j = i; j < quantidadeJogadores - 1; j++) {
                                elenco[j] = elenco[j + 1];
                            }

                                quantidadeJogadores--;
                                elenco[quantidadeJogadores] = null;
                                System.out.println("Jogador removido com sucesso!");
                                jogadorRemovido = true;
                                break;
                        }
                    }
                        if (jogadorRemovido == false) {
                            System.out.println("Jogador não encontrado para remoção!");
                        }
                } else if (opcaoElenco == 6) {
                        System.out.println("Digite uma posição: ");
                        posicaoPesquisa = scanner.nextLine();
                        System.out.println("Posição escolhida: " + posicaoPesquisa);
                        int quantidadeEncontrada = 0;
                        System.out.println("Jogadores encontrados: ");
                        for (int i = 0; i < quantidadeJogadores; i++) {
                            if (elenco[i].getPosicao().equalsIgnoreCase(posicaoPesquisa)) {
                                System.out.println("Nome: " + elenco[i].getNome()
                                        + "\nPosição: "
                                        + elenco[i].getPosicao()
                                        + "\nOverall: "
                                        + elenco[i].getOverall());
                                quantidadeEncontrada++;
                            }
                        }
                        if (quantidadeEncontrada == 0) {
                            System.out.println("Nenhum jogador encontrado para essa posição!");
                        } else {
                            System.out.println("Jogadores encontrados: " + quantidadeEncontrada);
                        }
                    }

                } while (opcaoElenco != 0);


            } else if (opcao == 3) {
                do {
                    System.out.println("MERCADO DE TRANSFERÊNCIAS"
                            + "\n1 - Contratar jogador"
                            + "\n2 - Vender jogador"
                            + "\n0 - Voltar ao menu principal"
                            + "\nEscolha uma opção: ");
                    if (scanner.hasNextInt()) {
                        opcaoTransferencia = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Opção inválida! Digite um número.");
                        scanner.nextLine();
                        opcaoTransferencia = -1;
                        continue;
                    }
                    if (opcaoTransferencia == 1) {
                        if (quantidadeJogadores < elenco.length) {
                            System.out.println("Digite o nome do jogador contratado: ");
                            jogadorContratado = scanner.nextLine();
                            boolean idadeValida = false;
                            do {
                                System.out.println("Idade do jogador: ");
                                if (scanner.hasNextInt()) {
                                    idade = scanner.nextInt();
                                    scanner.nextLine();
                                    if (idade >= 1 && idade <= 100) {
                                        idadeValida = true;
                                    } else {
                                        System.out.println("Idade inválida! Digite um número entre 1 e 100.");
                                    }
                                } else {
                                    System.out.println("Idade inválida! Digite um número entre 1 e 100.");
                                    scanner.nextLine();
                                }
                            } while (!idadeValida);
                            System.out.println("Digite a posição: ");
                            posicao = scanner.nextLine();
                            boolean overallValido = false;
                            do {
                                System.out.println("Digite o overall: ");
                                if (scanner.hasNextInt()) {
                                    overall = scanner.nextInt();
                                    scanner.nextLine();
                                    if (overall >= 1 && overall <= 99) {
                                        overallValido = true;
                                    } else {
                                        System.out.println("Overall inválido! Digite um número entre 1 e 99.");
                                    }
                                } else {
                                    System.out.println("Overall inválido! Digite um número entre 1 e 99.");
                                    scanner.nextLine();
                                }
                            } while (!overallValido);
                            System.out.println("Jogador contratado: "
                                    + jogadorContratado
                                    + "\nIdade: "
                                    + idade
                                    + "\nPosiçao: "
                                    + posicao
                                    + "\nOverall: "
                                    + overall);
                            Jogador novoContratado = new Jogador(jogadorContratado, idade, posicao, overall);
                            elenco[quantidadeJogadores] = novoContratado;
                            quantidadeJogadores++;
                        } else {
                            System.out.println("Não é possível contratar, elenco completo!");
                        }
                    } else if (opcaoTransferencia == 2) {
                        System.out.println("Digite o nome do jogador que deseja vender: ");
                        nomePesquisa = scanner.nextLine();
                        boolean jogadorVendido = false;
                        for (int i = 0; i < quantidadeJogadores; i++) {
                            if (elenco[i].getNome().equalsIgnoreCase(nomePesquisa)) {
                                System.out.println("Jogador encontrado: " + elenco[i].getNome() + "\nPosicao no vetor: " + i);
                                for (int j = i; j < quantidadeJogadores - 1; j++) {
                                    elenco[j] = elenco[j + 1];
                                }
                                quantidadeJogadores--;
                                elenco[quantidadeJogadores] = null;
                                System.out.println("Jogador vendido com sucesso!");
                                jogadorVendido = true;
                                break;
                            }
                        }
                        if (jogadorVendido == false) {
                            System.out.println("Jogador não encontrado para venda!");
                        }
                    }
                } while (opcaoTransferencia != 0);



            } else if (opcao == 4) {
                do {
                    System.out.println("HISTORICO DA CARREIRA"
                            + "\n1 - Registrar resultado de competição"
                            + "\n2 - Consultar histórico"
                            + "\n0 - Voltar ao menu principal"
                            + "\nEscolha uma opção: ");
                    if (scanner.hasNextInt()) {
                        opcaoHistorico = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Opção inválida! Digite um número.");
                        scanner.nextLine();
                        opcaoHistorico = -1;
                        continue;
                    }
                    if (opcaoHistorico == 1) {
                        if (quantidadeCompeticoes < historico.length) {
                            System.out.println("Registre sua temporada: ");
                            temporadaHistorico = scanner.nextLine();
                            System.out.println("Digite o nome do campeonato: ");
                            campeonatoHistorico = scanner.nextLine();
                            System.out.println("Digite o resultado da competição: ");
                            resultadoCompeticao = scanner.nextLine();
                            System.out.println("Temporada registrada: "
                                    + temporadaHistorico
                                    + "\nCampeonato: "
                                    + campeonatoHistorico
                                    + "\nResultado: "
                                    + resultadoCompeticao);
                            Competicao novaCompeticao = new Competicao(temporadaHistorico, campeonatoHistorico, resultadoCompeticao);
                            historico[quantidadeCompeticoes] = novaCompeticao;
                            quantidadeCompeticoes++;
                            System.out.println("Competição registrada com sucesso!");
                        } else {
                            System.out.println("Histórico completo!");
                        }
                    } else if (opcaoHistorico == 2) {
                        if (quantidadeCompeticoes == 0) {
                            System.out.println("Nenhuma competição cadastrada.");
                        } else {
                            for (int i = 0; i < quantidadeCompeticoes; i++) {
                                System.out.println("Temporada: " + historico[i].getTemporada());
                                System.out.println("Competição: " + historico[i].getNome());
                                System.out.println("Resultado: " + historico[i].getResultado());
                                System.out.println();
                            }
                        }
                        }
                } while (opcaoHistorico != 0);


            } else if (opcao == 5) {
                System.out.println("Iniciando salvamento da carreira...");
                if (!elencoCarregado) {
                    System.out.println("Salvamento bloqueado: o elenco não foi carregado corretamente.");
                } else {
                    try (FileWriter arquivo = new FileWriter("elenco.csv")) {
                        for (int i = 0; i < quantidadeJogadores; i++) {
                            arquivo.write(elenco[i].getNome()
                                    + ";"
                                    + elenco[i].getIdade()
                                    + ";"
                                    + elenco[i].getPosicao()
                                    + ";"
                                    + elenco[i].getOverall()
                                    + "\n");
                        }
                        System.out.println("Elenco salvo com sucesso!");
                    } catch (IOException e) {
                    System.out.println("Erro ao salvar arquivo: " + e.getMessage());
                }
                    }

                if (!historicoCarregado) {
                    System.out.println("Salvamento bloqueado: o histórico não foi carregado corretamente.");
                } else {
                    try (FileWriter gravadorHistorico = new FileWriter("historico.csv")) {
                        for (int i = 0; i < quantidadeCompeticoes; i++) {
                            gravadorHistorico.write(historico[i].getTemporada()
                                    + ";"
                                    + historico[i].getNome()
                                    + ";"
                                    + historico[i].getResultado()
                                    + "\n");
                        }
                        System.out.println("Historico salvo com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar arquivo: " + e.getMessage());
                    }
                }
            } else if (opcao == 0) {
                System.out.println("Programa encerrado, obrigado por utilizar!");
            } else {
                System.out.println("Opcao invalida! Escolha um numero entre 0 e 5.");
            }
        }
        while (opcao != 0);
    }

}
