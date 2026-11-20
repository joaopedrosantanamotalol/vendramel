# Guia de Requisições HTTP — Vendramel
## Fluxo completo de compra e pagamento

**Base URL:** `http://localhost:8080`

**Tecnologias:** Java 21, Spring Boot, Spring Data JPA e H2 Database.

> **Atenção:** O projeto utiliza H2 em memória com `spring.jpa.hibernate.ddl-auto=create-drop`. Ao reiniciar a aplicação, os dados são apagados. Para reproduzir este roteiro, execute as requisições na ordem apresentada.

---

# 1. Criar endereço

**Método:** `POST`  
**Endpoint:** `/enderecos`

**URL completa:**
```http
POST http://localhost:8080/enderecos
```

**Body — JSON:**
```json
{
  "logradouro": "Rua das Flores",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "uf": "SP",
  "cep": "01000-000"
}
```

**Resultado esperado:**
- Endereço cadastrado.
- ID: `1`.

---

# 2. Cadastrar cliente

**Método:** `POST`  
**Endpoint:** `/clientes`

**URL completa:**
```http
POST http://localhost:8080/clientes
```

**Body — JSON:**
```json
{
  "nomeCompleto": "João Pedro",
  "cpf": "12345678901",
  "dataNascimento": "2002-05-15T00:00:00",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "senha": "123456",
  "endereco": {
    "id": 1
  }
}
```

**Resultado esperado:**
- Cliente cadastrado.
- ID: `1`.
- Endereço associado: `1`.

---

# 3. Cadastrar livro

**Método:** `POST`  
**Endpoint:** `/livros`

**URL completa:**
```http
POST http://localhost:8080/livros
```

**Body — JSON:**
```json
{
  "titulo": "Clean Code",
  "formato": "FISICO",
  "precoVenda": 89.90,
  "quantidadeEstoque": 20,
  "status": "ATIVO"
}
```

**Resultado esperado:**
- Livro cadastrado.
- ID: `1`.
- Preço: R$ 89,90.
- Estoque inicial: 20 unidades.

---

# 4. Adicionar livro ao carrinho

**Método:** `POST`  
**Endpoint:** `/carrinhos/itens`

**URL completa:**
```http
POST http://localhost:8080/carrinhos/itens
```

**Body — JSON:**
```json
{
  "idCliente": 1,
  "idLivro": 1,
  "quantidade": 6
}
```

**Resultado esperado:**
- Carrinho associado ao cliente.
- Carrinho ID: `1`.
- Item ID: `1`.
- Quantidade: 6 unidades.

---

# 5. Consultar carrinho

**Método:** `GET`  
**Endpoint:** `/carrinhos/{idCliente}`

**URL completa:**
```http
GET http://localhost:8080/carrinhos/1
```

**Resultado esperado:**
- Carrinho ID: `1`.
- Um item cadastrado.
- Quantidade: `6`.

---

# 6. Criar pedido

**Método:** `POST`  
**Endpoint:** `/pedidos`

**URL completa:**
```http
POST http://localhost:8080/pedidos?idCliente=1&idEndereco=1
```

**Body:** Sem body.

**Resultado esperado:**

| Informação | Valor |
|---|---:|
| ID do pedido | 1 |
| Status | PENDENTE |
| Subtotal | R$ 539,40 |
| Frete | R$ 15,00 |
| Total | R$ 554,40 |

O pedido deve conter:
- 6 unidades de Clean Code.
- Preço unitário de R$ 89,90.
- Endereço de entrega associado ao cliente.

---

# 7. Consultar pedido antes do pagamento

**Método:** `GET`  
**Endpoint:** `/pedidos/{id}`

**URL completa:**
```http
GET http://localhost:8080/pedidos/1
```

**Resultado esperado:**
- Pedido ID: `1`.
- Status: `PENDENTE`.
- Valor total: R$ 554,40.
- Estoque ainda em 20 unidades.

---

# 8. Realizar pagamento PIX

**Método:** `POST`  
**Endpoint:** `/pagamentos`

**URL completa:**
```http
POST http://localhost:8080/pagamentos
```

**Body — JSON:**
```json
{
  "pedidoId": 1,
  "tipo": "PIX",
  "parcelas": 1
}
```

**Resultado esperado:**

| Informação | Valor |
|---|---:|
| ID do pagamento | 1 |
| Forma de pagamento | PIX |
| Parcelas | 1 |
| Status | APROVADO |
| Valor pago | R$ 510,05 |

**Cálculo do desconto:**

- Valor original: R$ 554,40.
- Desconto PIX: 8%.
- Valor final: R$ 510,05.

> Execute esta requisição somente uma vez para o pedido. O pagamento aprovado dispara a atualização do estoque, a finalização do pedido e a limpeza do carrinho.

---

# 9. Consultar pedido após pagamento

**Método:** `GET`  
**Endpoint:** `/pedidos/{id}`

**URL completa:**
```http
GET http://localhost:8080/pedidos/1
```

**Resultado esperado:**
- Pedido ID: `1`.
- Status: `FINALIZADO`.
- Quantidade comprada: 6.
- Estoque atualizado no livro: 14 unidades.

---

# 10. Consultar livro e estoque atualizado

**Método:** `GET`  
**Endpoint:** `/livros/{id}`

**URL completa:**
```http
GET http://localhost:8080/livros/1
```

**Resultado esperado:**
```json
{
  "formato": "FISICO",
  "id": 1,
  "precoVenda": 89.90,
  "quantidadeEstoque": 14,
  "status": "ATIVO",
  "titulo": "Clean Code"
}
```

**Conferência:**

Estoque inicial: 20 unidades.  
Quantidade vendida: 6 unidades.  
Estoque final: 14 unidades.

---

# 11. Consultar carrinho após pagamento

**Método:** `GET`  
**Endpoint:** `/carrinhos/{idCliente}`

**URL completa:**
```http
GET http://localhost:8080/carrinhos/1
```

**Resultado esperado:**
```json
{
  "dataCriacao": "2026-11-20T16:42:35.970349",
  "id": 1,
  "itens": []
}
```

**Resultado:** o carrinho continua cadastrado, mas todos os seus itens foram removidos automaticamente após a aprovação do pagamento.

---

# 12. Remover todos os itens manualmente (opcional)

**Método:** `DELETE`  
**Endpoint:** `/carrinhos/{idCarrinho}/itens/todos`

**URL completa:**
```http
DELETE http://localhost:8080/carrinhos/1/itens/todos
```

**Body:** Sem body.

**Resultado esperado:**
```http
204 No Content
```

Essa requisição permite limpar manualmente os itens do carrinho, independentemente do fluxo de pagamento.

---

# 13. Remover um item específico (opcional)

**Método:** `DELETE`  
**Endpoint:** `/carrinhos/itens/{idItem}`

**URL completa:**
```http
DELETE http://localhost:8080/carrinhos/itens/1
```

**Body:** Sem body.

**Resultado esperado:**
```http
204 No Content
```

---

# 14. Alterar quantidade de um item (opcional)

**Método:** `PATCH`  
**Endpoint:** `/carrinhos/itens/{idItem}/quantidade`

**URL completa:**
```http
PATCH http://localhost:8080/carrinhos/itens/1/quantidade
```

**Body — JSON:**
```json
{
  "quantidade": 3
}
```

**Resultado esperado:**
- Quantidade do item atualizada para 3.
- Retorno do carrinho atualizado.

---

# Resumo geral das requisições

| Nº | Método | Endpoint | Finalidade |
|---:|---|---|---|
| 1 | POST | `/enderecos` | Criar endereço |
| 2 | POST | `/clientes` | Cadastrar cliente |
| 3 | POST | `/livros` | Cadastrar livro |
| 4 | POST | `/carrinhos/itens` | Adicionar item |
| 5 | GET | `/carrinhos/1` | Consultar carrinho |
| 6 | POST | `/pedidos` | Criar pedido |
| 7 | GET | `/pedidos/1` | Consultar pedido |
| 8 | POST | `/pagamentos` | Realizar pagamento |
| 9 | GET | `/pedidos/1` | Conferir finalização |
| 10 | GET | `/livros/1` | Conferir estoque |
| 11 | GET | `/carrinhos/1` | Conferir limpeza |
| 12 | DELETE | `/carrinhos/1/itens/todos` | Limpar carrinho manualmente |
| 13 | DELETE | `/carrinhos/itens/1` | Remover item |
| 14 | PATCH | `/carrinhos/itens/1/quantidade` | Alterar quantidade |

---

# Resultado final do fluxo testado

| Verificação | Resultado |
|---|---|
| Cadastro de endereço | Sucesso |
| Cadastro de cliente | Sucesso |
| Cadastro de livro | Sucesso |
| Adição ao carrinho | Sucesso |
| Criação do pedido | Sucesso |
| Aprovação do PIX | Sucesso |
| Aplicação do desconto | Sucesso |
| Finalização do pedido | Sucesso |
| Atualização do estoque | Sucesso |
| Limpeza automática do carrinho | Sucesso |

**Conclusão:** o fluxo principal de compra do Vendramel foi executado com sucesso. O pagamento foi aprovado, o pedido foi finalizado, o estoque foi atualizado corretamente e os itens do carrinho foram removidos automaticamente.

**Observação:** os IDs `1` e os valores apresentados correspondem à execução de teste realizada com o banco H2 recém-inicializado. Em outras execuções, os identificadores podem ser diferentes.
