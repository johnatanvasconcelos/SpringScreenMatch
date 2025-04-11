# ScreenMatch 🎬

**ScreenMatch** é uma aplicação Java de linha de comando construída com Spring Boot que permite aos usuários buscar informações sobre filmes e séries utilizando a API pública do [OMDb](https://www.omdbapi.com/). A aplicação oferece uma interface interativa no console para explorar detalhes de títulos, temporadas e episódios.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

---

## Índice

*   [Funcionalidades](#funcionalidades)
*   [Tecnologias Utilizadas](#tecnologias-utilizadas)
*   [Pré-requisitos](#pré-requisitos-)
*   [Como Começar](#como-começar-)
    *   [Clonando o Repositório](#clonando-o-repositório)
    *   [Configurando a API Key](#configurando-a-api-key-)
    *   [Construindo o Projeto](#construindo-o-projeto)
    *   [Executando a Aplicação](#executando-a-aplicação)
*   [Como Usar](#como-usar-)
*   [Estrutura do Projeto](#estrutura-do-projeto-)
*   [Autor](#autor-)
*   [Licença](#licença-)

---

## Funcionalidades ✨

*   **Busca de Filmes:** Pesquise filmes pelo título e veja detalhes como ano, nota IMDb, diretor, duração, gênero, premiações e país.
*   **Busca de Séries:** Pesquise séries e veja detalhes como total de temporadas, nota IMDb, data de lançamento, gênero e anos de atividade.
*   **Análise de Séries:**
    *   Busca e exibe todos os episódios, agrupados por temporada.
    *   Calcula e mostra a média de avaliação dos episódios (baseado nas notas do IMDb).
    *   Identifica e exibe o episódio com a melhor avaliação.
    *   Identifica e exibe o episódio com a pior avaliação (dentre os avaliados).
*   **Interface Interativa:** Menu de console fácil de usar para navegar pelas funcionalidades.
*   **Tratamento de Dados:** Converte e trata dados da API (como datas e notas) para exibir informações formatadas incluindo um desserializador customizado para datas.

---

## Tecnologias Utilizadas 🛠️

*   **Java 17+:** Linguagem de programação principal.
*   **Spring Boot 3.x:** Framework para facilitar a criação de aplicações Java robustas.
    *   Spring Core (Injeia)
    *   Spring Configuration (`@Value`, `@Configuration`, `@Bean`)
*   **Maven:** Ferramenta de gerenciamento de dependências e build do projeto.
*   **Jackson Databind:** Biblioteca para processamento de JSON (converstre JSON e objetos Java).
*   **OMDb API:** Fonte externa de dados sobre filmes e séries.

---

## Pré-requisitos 📋

Antes de começar, você precisar ter instalado em sua máquina:

*   JDK (Java Development Kit) - Versão 17 ou superior.
*   Maven - Para gerenciamento de dependências e build.
*   Git - Para clonar o repositório.
*   Uma **API Key** da OMDb API. Você pode obter uma gratuitamente [aqui](#configurando-a-api-key-).

---

## Como Começar 🚀

Siga estes passos para configurar e rodar o projeto localmente.

### Clonando o Repositório
`bash git clone https://github.com/seu-usuario/SpringBootScreenMatch.git cd SpringBootScreenMatch`

*(Substitua `https://github.com/seu-usuario/SpringBootScreenMatch.git` pela URL real do seu repositório no GitHub)*

### Configurando a API Key 🔑

**Importante:** Para que a aplicação funcione, você precisa fornecer sua chave da API OMDb. A forma recomendada é através do arquivo de propriedades do Spring Boot.

1.  Navegue até o diretório `src/main/resources/`.
2.  Crie ou edite o arquivo `application.properties`.
3.  Adicione a seguinte linha, substituindo `SUA_CHAVE_AQUI` pela sua chave real: `omdb.api.key=SUA_CHAVE_AQUI`
4.  **Verificação no Código:** O projeto já está configurado para ler esta propriedade. A classe `ApiClient` utiliza `@Value("${omdb.api.key}")` para injetar a chave e usá-la nas requisições à API.

### Construindo o Projeto

Use o Maven para compilar o projeto e baixar as dependências:
 `bash mvn clean install`

### Executando a Aplicação

Após construir o projeto, você pode executusando o Maven:
`bash mvn spring-boot:run`     
Ou executando o arquivo JAR gerado (localizado no diretório `target/` após o `mvn install`): `bash java -jar target/screematch-0.0.1-SNAPSHOT.jar`              
*(O nome do arquivo JAR pode variar ligeiramente dependendo da versão no seu `pom.xml`)*

---

## Como Usar 🎮

Ao executar a aplicação, um menu interativo será apresentado no console:            
O que deseja fazer? Digite 1 - Analisar um filme. Digite 2 - Analisar uma série. Digite 0 - Sair
1.  **Escolha a opção:** Digite `1` para filmes ou `2` para séries.
2.  **Digite o Título:** A aplicação solicitará o nome do filme ou série.
3.  **Veja os Resultados:** As informações básicas serão exibidas.
4.  **Submenu (Séries):** Se você escolheu analisar uma série e a análise foi bem-sucedida, um submenu aparecerá:

O que mais deseja saber desta série? 1 - Mostrar todos os episódios 2 - Melhor Episódio 3 - Pior Episódio 0 - Voltar
*   Escolha as opções para ver detalhes dos episódios, estatísticas ou volte ao menu principal digitando `0`.
5.  **Sair:** Digite `0` no menu principal para encerrar a aplicação.

---

## Estrutura do Projeto 📂
```
SpringBootScreenMatch/  
├── src/ 
│ ├── main/
│ │ ├── java/ 
│ │ │ └── br/com/johnatan/screematch/ 
│ │ │ ├── ScreematchApplication.java (Classe principal Spring Boot) 
│ │ │ ├── application/ 
│ │ │ │ └── Main.java (Lógica do menu e interação com usuário) 
│ │ │ ├── client/ 
│ │ │ │ └── ApiClient.java (Responsável por chamar a API OMDb) 
│ │ │ ├── config/ 
│ │ │ │ ├── AppConfig.java (Configurações de Beans, como Scanner) 
│ │ │ │ └── LocalDateDeserializer.java (Desserializador customizado para datas) 
│ │ │ ├── model/ 
│ │ │ │ ├── DataEpisode.java (DTO para dados brutos de episódio da API) 
│ │ │ │ ├── DataMovie.java (DTO para dados brutos de filme da API) 
│ │ │ │ ├── DataSeason.java (DTO para dados brutos de temporada da API) 
│ │ │ │ ├── DataSeries.java (DTO para dados brutos de série da API) 
│ │ │ │ └── Episode.java (Modelo de domínio para episódio) 
│ │ │ ├── service/ 
│ │ │ │ ├── MovieService.java (Lógica de negócio para filmes) 
│ │ │ │ └── SeriesService.java (Lógica de negócio para séries) 
│ │ │ ├── utils/ 
│ │ │ │ ├── ConvertData.java (Utilitário para converter JSON em objetos) 
│ │ │ │ └── IConvertData.java (Interface de tipo genérico)
│ │ │ ├── resources/ 
│ │ └───└── application.properties (Configurações da aplicação, incluindo API Key) 
│ ├── test/ 
│ └── java/ (Testes unitários e de integração - opcional) 
├── .gitignore (Arquivos e diretórios ignorados pelo Git) 
├── pom.xml (Arquivo de configuração do Maven) 
└── README.md (Este arquivo)
```
---

## Autor ✒️

*   **Johnatan Costa** - *Desenvolvedor Principal* - [johnatanvasconcelos](https://github.com/johnatanvasconcelos)

---

## Licença 📄

Este projeto está licenciado sob a Licença MIT -    
Veja o arquivo [LICENSE](LICENSE) para detalhes.
