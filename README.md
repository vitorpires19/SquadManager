# ⚽ SquadManager

Sistema de gerenciamento de carreira no futebol, desenvolvido em **Java**, com execução pelo terminal.

O projeto foi criado para aplicar conceitos de programação orientada a objetos, estruturas de repetição, vetores, manipulação de arquivos e tratamento de exceções.

## Funcionalidades

- Cadastro e consulta de informações do clube.
- Cadastro, listagem, pesquisa, edição e remoção de jogadores.
- Filtro de jogadores por posição.
- Contratação e venda de jogadores.
- Registro e consulta de resultados de competições por temporada.
- Salvamento e carregamento do elenco e do histórico em arquivos CSV.
- Validação de entradas numéricas e tratamento de erros de leitura.

## Tecnologias utilizadas

- Java 21
- Programação orientada a objetos
- IntelliJ IDEA
- Arquivos CSV para persistência de dados

## Como executar

1. Instale o JDK 21 ou uma versão compatível.
2. Abra o projeto em uma IDE Java, como o IntelliJ IDEA.
3. Execute a classe `SquadManager.java`.
4. Na primeira execução, caso não existam arquivos de carreira, confirme a criação de uma nova carreira.
5. Utilize os menus do terminal para gerenciar os jogadores e as competições.
6. Selecione `5 - Salvar carreira` para gravar as alterações.

## Estrutura do projeto

```text
SquadManager/
├── src/
│   ├── SquadManager.java
│   ├── Jogador.java
│   └── Competicao.java
├── .gitignore
└── README.md
```

Os arquivos `elenco.csv` e `historico.csv` são criados e utilizados localmente, não sendo incluídos no repositório.

## Limitações da versão 1.0

- Os dados do clube ainda não são salvos em arquivo.
- O gerenciamento de jogadores utiliza um vetor com capacidade máxima de 30 atletas.
- O histórico possui capacidade máxima de 100 registros.
- Jogadores com nomes iguais podem gerar ambiguidades nas operações por nome.
- A aplicação é executada pelo terminal, sem interface gráfica.
- A ausência de apenas um dos arquivos CSV ainda não possui um fluxo completo de recuperação.

## Próximas melhorias

- Persistência dos dados do clube.
- Identificador único para cada jogador.
- Histórico de transferências.
- Melhor organização do código em métodos e classes.
- Evolução da persistência de dados.

## Objetivo educacional

Projeto desenvolvido para consolidar conhecimentos de Java por meio da construção progressiva de uma aplicação funcional.