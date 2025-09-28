<h1>Plataforma de Microsserviços de Vendas</h1>
<p>
  This README is also available in <a href="./README.md">English</a>.
</p>
<p>
  Uma plataforma de microsserviços em <strong>Java 21</strong> <strong>Spring Boot</strong> para gerenciar
  <strong>clientes, produtos e vendas.</strong>
  Ela integra <strong>MongoDB</strong> para persistência, <strong>Spring Cloud Config Server</strong>
  para configuração centralizada, <strong>Feign clients</strong> para comunicação entre serviços
  e <strong>OpenAPI/Swagger</strong> para documentação das APIs.
</p>
<h2>🚀 Funcionalidades</h2>
<ul>
  <li>Microsserviços de Cliente, Produto e Vendas</li>
  <li>Configuração centralizada via Spring Cloud Config Server</li>
  <li>Persistência no MongoDB com padrão de repositório</li>
  <li>APIs RESTful documentadas com OpenAPI/Swagger</li>
  <li>Comunicação entre serviços usando Feign clients e RestTemplate</li>
  <li>Tratamento estruturado de erros com exceções customizadas e erros de validação</li>
  <li>Testes unitários e de integração em todos os serviços</li>
  <li>Javadocs detalhados gerados para todas as classes e enums</li>
</ul>
<h2>🛠️ Stack Tecnológica</h2>
<ul>
  <li><strong>Java:</strong> 21</li>
  <li><strong>Spring Boot:</strong> 3.5.4</li>
  <li><strong>Spring Cloud:</strong> 2025.0.0</li>
  <li><strong>Spring Cloud Config Server</strong></li>
  <li><strong>MongoDB</strong></li>
  <li><strong>Feign Clients</strong></li>
  <li><strong>Springdoc OpenAPI (Swagger UI)</strong></li>
  <li><strong>Lombok</strong></li>
  <li><strong>JUnit + Mockito</strong> para testes</li>
</ul>
<h2>🐳 Suporte a Docker (branch dockerized)</h2>
<ul>
  <li>A branch <code>dockerized</code> contém o suporte completo a Docker.</li>
  <li>Cada serviço possui um <code>Dockerfile</code> e <code>.dockerignore</code>.</li>
  <li><code>docker-compose.yml</code> orquestra todos os serviços e o MongoDB.</li>
  <li><code>wait-for-it.sh</code> garante que os serviços iniciem apenas após as dependências estarem prontas.</li>
  <li>As configurações centralizadas são obtidas do <strong>ConfigServer</strong> usando hostnames do Docker.</li>
</ul>
<h2>📂 Módulos do Projeto</h2>
<ul>
  <li><strong>ConfigServer</strong>: Gerenciamento centralizado de configuração para todos os microsserviços</li>
  <li><strong>ClientService</strong>: Gerencia clientes (CRUD, busca por ID/CPF, validações)</li>
  <li><strong>ProductService</strong>: Gerencia produtos (CRUD, busca por código, validações)</li>
  <li><strong>SalesService</strong>: Gerencia vendas, ciclo de vida de status e associações de produtos</li>
</ul>
<h2>📑 Visão Geral do Domínio</h2>
<ul>
  <li><strong>Client:</strong> Entidade com validação, índices únicos e anotações Swagger</li>
  <li><strong>Product:</strong> Entidade com validação, enum de status (ATIVO/INATIVO) e suporte a Lombok</li>
  <li><strong>Sales:</strong> Entidade com gerenciamento de produtos, cálculo de preço total e ciclo de vida de status
    (INICIADA,
    FINALIZADA, CANCELADA)</li>
  <li><strong>DTOs:</strong> Objetos de transferência para Vendas, Produto e Cliente</li>
  <li><strong>Status Enum:</strong> Compartilhado entre domínios para consistência dos estados de ciclo de vida</li>
</ul>
<h2>📘 Documentação e Javadocs</h2>
<p>Inclui Javadocs completos para todas as classes centrais, incluindo:</p>
<ul>
  <li>Classes principais (SalesServiceApplication, ClientServiceApplication, ProductServiceApplication,
    ConfigServerApplication).</li>
  <li>Configurações (MongoConfig, OpenAPIConfig, RestTemplateConfig)</li>
  <li>Classes de domínio (Client, Product, ProductQuantity, Sales)</li>
  <li>DTOs (ClientDTO, ProductDTO, SalesDTO)</li>
  <li>Serviços e Casos de Uso (RegisterClient, SearchClient, RegisterProduct, SearchProduct, RegisterSale, SearchSale)
  </li>
  <li>Controladores (ClientResource, ProductResource, SalesResources)</li>
  <li>Tratamento de exceções (ApiError, ApiValidationError, BadRequestException, EntityNotFoundException,
    RestExceptionHandler)</li>
  <li>Utilitários (RestRequest, RestUtils)</li>
  <li>Testes (SalesTest, SalesResourcesTest, ProductResourceTest, ClientResourceTest)</li>
</ul>
<h2>🧪 Testes</h2>
<ul>
  <li>Testes unitários para domínios (SalesTest, lógica de ProductQuantity)</li>
  <li>Testes de controladores REST (SalesResourcesTest, ProductResourceTest, ClientResourceTest)</li>
  <li>Integração com Mockito para operações de CRUD e validações</li>
  <li>Cobertura de tratamento de exceções para BadRequest e EntityNotFound</li>
</ul>
<h2>⚙️ Primeiros Passos</h2>
<ol>
  <li>Clone o repositório:
    <pre><code>git clone https://github.com/AsrielDreemurrGM/Microservices_Online_Selling.git</code></pre>
  </li>
  <li>Inicie o serviço ConfigServer.</li>
  <li>Inicie os serviços ClientService, ProductService e SalesService.</li>
  <li>Acesse a documentação da API em:
    <pre><code>Client Service: http://localhost:8081/swagger-ui.html</code></pre>
    <pre><code>Product Service: http://localhost:8082/swagger-ui.html</code></pre>
    <pre><code>Sales Service: http://localhost:8083/swagger-ui.html</code></pre>
  </li>
  <li>Para executar com Docker (branch <code>dockerized</code>):
    <pre><code>git checkout dockerized
docker-compose up --build</code></pre>
  </li>
</ol>
<h2>📜 Resumo do Histórico de Commits</h2>
<ul>
  <li>Commit inicial com quatro microsserviços, configuração e setup do build</li>
  <li>Domínio de Cliente, DTO, repositório, casos de uso, recursos e testes</li>
  <li>Domínio de Produto, DTO, repositório, casos de uso, recursos, configs e testes</li>
  <li>Domínio de Vendas, DTO, serviços (RegisterSale, SearchSale), recursos e repositório</li>
  <li>Setup do ConfigServer e correções em arquivos YML para microsserviços</li>
  <li>Padronização do tratamento de exceções em todos os serviços</li>
  <li>Adição de configurações OpenAPI e Mongo para todos os serviços</li>
  <li>Criação e melhoria de testes unitários para serviços de Cliente, Produto e Vendas</li>
  <li>Adição de Javadocs detalhados em todos os serviços, controladores, domínios e configs</li>
  <li>Refatoração de endpoints para maior clareza</li>
  <li>Configuração de Docker para microsserviços (branch <code>dockerized</code>)</li>
</ul>
