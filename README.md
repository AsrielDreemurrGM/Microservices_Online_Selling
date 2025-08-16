<h1>Sales Microservices Platform</h1>
<p>
  Este README também está disponível em <a href="./README.pt-br.md">Português</a>.
</p>
<p>
  A <strong>Java 21</strong> <strong>Spring Boot</strong> microservices platform for managing
  <strong>clients, products, and sales.</strong>
  It integrates <strong>MongoDB</strong> for persistence, <strong>Spring Cloud Config Server</strong>
  for centralized configuration, <strong>Feign clients</strong> for inter-service communication,
  and <strong>OpenAPI/Swagger</strong> for API documentation.
</p>
<h2>🚀 Features</h2>
<ul>
  <li>Client, Product, and Sales microservices</li>
  <li>Centralized configuration via Spring Cloud Config Server</li>
  <li>MongoDB persistence with repository pattern</li>
  <li>RESTful APIs documented with OpenAPI/Swagger</li>
  <li>Inter-service communication using Feign clients and RestTemplate</li>
  <li>Structured error handling with custom exceptions and validation errors</li>
  <li>Unit and integration tests across services</li>
  <li>Detailed Javadocs generated for all classes and enums</li>
</ul>
<h2>🛠️ Tech Stack</h2>
<ul>
  <li><strong>Java:</strong> 21</li>
  <li><strong>Spring Boot:</strong> 3.5.4</li>
  <li><strong>Spring Cloud:</strong> 2025.0.0</li>
  <li><strong>Spring Cloud Config Server</strong></li>
  <li><strong>MongoDB</strong></li>
  <li><strong>Feign Clients</strong></li>
  <li><strong>Springdoc OpenAPI (Swagger UI)</strong></li>
  <li><strong>Lombok</strong></li>
  <li><strong>JUnit + Mockito</strong> for testing</li>
</ul>
<h2>📂 Project Modules</h2>
<ul>
  <li><strong>ConfigServer</strong>: Centralized configuration management for all microservices</li>
  <li><strong>ClientService</strong>: Manage clients (CRUD, search by ID/CPF, validations)</li>
  <li><strong>ProductService</strong>: Manage products (CRUD, search by code, validations)</li>
  <li><strong>SalesService</strong>: Manage sales, sale status lifecycle, and product associations</li>
</ul>
<h2>📑 Domain Overview</h2>
<ul>
  <li><strong>Client:</strong> Entity with validation, unique indexes, and Swagger annotations</li>
  <li><strong>Product:</strong> Entity with validation, status enum (ACTIVE/INACTIVE), and Lombok support</li>
  <li><strong>Sales:</strong> Entity with product management, total price calculation, and status lifecycle (STARTED,
    FINISHED, CANCELED)</li>
  <li><strong>DTOs:</strong> Transfer objects for Sales, Product, and Client</li>
  <li><strong>Status Enum:</strong> Shared across domains for consistent lifecycle states</li>
</ul>
<h2>📘 Documentation and Javadocs</h2>
<p>Includes full Javadocs for all core classes, including:</p>
<ul>
  <li>Main classes (SalesServiceApplication, ClientServiceApplication, ProductServiceApplication,
    ConfigServerApplication).</li>
  <li>Configurations (MongoConfig, OpenAPIConfig, RestTemplateConfig)</li>
  <li>Domain classes (Client, Product, ProductQuantity, Sales)</li>
  <li>DTOs (ClientDTO, ProductDTO, SalesDTO)</li>
  <li>Services and Use Cases (RegisterClient, SearchClient, RegisterProduct, SearchProduct, RegisterSale, SearchSale)
  </li>
  <li>Controllers (ClientResource, ProductResource, SalesResources)</li>
  <li>Exception handling (ApiError, ApiValidationError, BadRequestException, EntityNotFoundException,
    RestExceptionHandler)</li>
  <li>Utilities (RestRequest, RestUtils)</li>
  <li>Tests (SalesTest, SalesResourcesTest, ProductResourceTest, ClientResourceTest)</li>
</ul>
<h2>🧪 Tests</h2>
<ul>
  <li>Unit tests for domains (SalesTest, ProductQuantity logic)</li>
  <li>REST controller tests (SalesResourcesTest, ProductResourceTest, ClientResourceTest)</li>
  <li>Mockito integration for CRUD operations and validations</li>
  <li>Exception handling coverage for BadRequest and EntityNotFound</li>
</ul>
<h2>⚙️ Getting Started</h2>
<ol>
  <li>Clone the repository:
    <pre><code>git clone https://github.com/AsrielDreemurrGM/Microservices_Online_Selling.git</code></pre>
  </li>
  <li>Start the ConfigServer service.</li>
  <li>Start ClientService, ProductService, and SalesService.</li>
  <li>Access API documentation at:
    <pre><code>Client Service: http://localhost:8081/swagger-ui.html</code></pre>
    <pre><code>Product Service: http://localhost:8082/swagger-ui.html</code></pre>
    <pre><code>Sales Service: http://localhost:8083/swagger-ui.html</code></pre>
  </li>
</ol>
<h2>📜 Commit History Summary</h2>
<ul>
  <li>Initial commit with four microservices, config, and build setup</li>
  <li>Client domain, DTO, repository, use cases, resources, and tests</li>
  <li>Product domain, DTO, repository, use cases, resources, configs, and tests</li>
  <li>Sales domain, DTO, services (RegisterSale, SearchSale), resources, and repository</li>
  <li>ConfigServer setup and YML corrections for microservices</li>
  <li>Exception handling standardized across services</li>
  <li>OpenAPI and Mongo configurations added for all services</li>
  <li>Unit tests created and improved for Client, Product, and Sales services</li>
  <li>Detailed Javadocs added across all services, controllers, domains, and configs</li>
  <li>Refactoring of endpoints for clarity</li>
</ul>
