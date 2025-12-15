# 🛒 Projeto E-commerce API

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

API RESTful desenvolvida em Java com Spring Boot, simulando o funcionamento de um sistema de e-commerce. O projeto contempla o cadastro e gerenciamento de usuários, produtos, categorias, pedidos, itens de pedido e pagamentos, seguindo boas práticas de arquitetura, organização de código e regras de negócio.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **Spring Web**
* **Banco de dados H2 (ambiente de desenvolvimento)**
* **Postman (testes de API)**
* **Maven**
* **Jackson (JSON)**

---

## 🧱 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

* **Controller** – Responsável por expor os endpoints REST
* **Service** – Contém as regras de negócio
* **Repository** – Acesso aos dados via JPA
* **Entities** – Mapeamento das entidades do domínio
* **DTOs** – Separação entre modelos de entrada e saída
* **Exceptions** – Tratamento centralizado de exceções

---

## 📦 Principais Funcionalidades

### 👤 Usuários

* Cadastro
* Consulta
* Atualização
* Exclusão

### 🛍️ Produtos e Categorias

* Cadastro de produtos
* Associação entre produtos e categorias
* Atualização e remoção

### 📦 Pedidos (Orders)

* Criação de pedidos com múltiplos itens
* Associação de cliente ao pedido
* Controle de status do pedido
* Cálculo de subtotal e total

### 🧾 Itens do Pedido (OrderItem)

* Chave composta (pedido + produto)
* Quantidade e preço no momento da compra

### 💳 Pagamentos

* Criação de pagamento vinculada ao pedido
* Alteração automática do status do pedido
* Remoção de pagamento com reversão de status

### 🔄 Status do Pedido

O status do pedido é controlado pela lógica de negócio, as transições de status ocorrem dentro da entidade `Order`, garantindo encapsulamento e consistência.

---

## 🔁 DTOs (Data Transfer Objects)

O projeto utiliza DTOs para:

* Evitar exposição direta das entidades
* Controlar melhor o JSON de entrada e saída
* Facilitar validações e evolução da API

---

## 🧪 Testes

Os endpoints podem ser testados utilizando o **Postman**, enviando e recebendo dados no formato JSON.

---

## ▶️ Como Executar o Projeto

1. Clone o repositório
2. Importe o projeto em sua IDE (IntelliJ / Eclipse)
3. Instale as dependências Maven
4. Execute a classe principal do Spring Boot
5. Acesse o console H2 (opcional)
6. Teste os endpoints via Postman

---

## 📄 Considerações Finais

Este projeto tem foco educacional e demonstra, na prática, conceitos essenciais do desenvolvimento backend com Spring Boot, servindo como base para projetos mais robustos e escaláveis.

Desenvolvido por [@deboracmg](https://github.com/deboracmg)  
