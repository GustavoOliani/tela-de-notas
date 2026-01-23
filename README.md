# Machine Telemetry API
API desenvolvida com Spring Boot 3 para coleta e consulta de dados de telemetria de máquinas.

## Tecnologias Utilizadas
 - Java 17 & Spring Boot 3.2.x
 - Spring Data JDBC (Persistência leve e performática)
 - PostgreSQL (Banco de dados relacional)
 - Flyway (Migração e carga inicial de dados via CSV)
 - Docker & Docker Compose (Containerização completa)
 - SpringDoc OpenAPI (Swagger) (Documentação interativa)
 - Lombok (Produtividade no desenvolvimento)
 - Angular 17 & HTML, CSS

## Como Executar o Projeto
Você só precisa ter o Docker e o Docker Compose instalados.

Clone o repositório:
```Bash 
git clone https://github.com/GustavoOliani/tela-de-notas.git
```

Criar o .env na raiz, exemplo de um
```
DB_USER="user"
DB_PASSWORD="password"
DB_NAME="telemetry_db"
DB_PORT=5432
COMPOSE_PROJECT_NAME="tela_de_notas"
```

Suba os containers:
```Bash
docker-compose up --build -d
```
Aguarde a inicialização: 
O Flyway irá criar as tabelas e importar automaticamente os dados do arquivo notes.csv para o banco de dados.
Vai ser buildado o backend e o frontend

O frontend fica disponível na porta 4200 http://localhost:4200
o backend na porta 8080 http://localhost:8080


## Principais endpoints

POST /api/v1/notes
Exemplo de Payload:
```JSON
[
    {
        "id": "006658ce-201d-433a-9612-59f8fe461ded",
        "site": "Planta Industrial A",
        "equipment": "Motor-01",
        "variable": "Temperatura",
        "timestamp": "2026-01-21T19:00:00Z",
        "author": "Engenheiro Silva",
        "message": "Aumento repentino detectado na turbina."
    }
]
```

GET /api/v1/notes

Parâmetros de Query:
 - site (String): Filtra por localidade.
 - equipment (String): Filtra por ID da máquina.
 - startDate / endDate (ISO Date): Filtra por período.
 - page: Número da página (padrão: 0).
 - size: Itens por página (padrão: 10).

## Tratamento de Erros
A API utiliza um GlobalExceptionHandler para garantir respostas consistentes:
 - 400 Bad Request: Erros de validação (campos obrigatórios, tamanhos inválidos).
 - 422 Unprocessable Entity: Erros de lógica de negócio (ex: data final menor que a inicial).
 - 500 Internal Server Error: Erros inesperados no servidor.


### Backlog

 - Finalizar as funcionalides do frontend
    - Filtros de busca com select (provavelmente usar as opções existentes para exibir elas e evitar typos)
    - Na tabela de notas, avançar para última página e retornar para a primeira
    - Ajustes visuais
 - Rever lógica de obrigatoriedade dos campos (cada camada ser responsável por gerar o dado quando for essencial e não existir)
 - Gerar documentação de regras de negócio
 - Completar o CRUD
    - Adicionar requisições de PUT e DELETE
    - Alterações na interface para ter um formulário para atualizar e botão de deleção
    - Aplicar eurísticas de nielsen para evitar erros
 - Incluir testes (unitário, integração e e2e)
 - Adicionar logs
 - Alinhar respostas da aplicação para transparência e recuperação de erros pelo lado do usuário
