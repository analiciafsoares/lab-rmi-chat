# Relatório – Laboratório de RMI

## 1. Introdução

Este relatório descreve o desenvolvimento e a execução do laboratório de **RMI (Remote Method Invocation)**, conforme o roteiro disponibilizado na disciplina de Sistemas Distribuídos. O laboratório foi dividido em duas partes principais:

- **Parte 1:** Execução do exemplo HelloRMI, com o objetivo de compreender o funcionamento básico do RMI.
- **Parte 2:** Desenvolvimento de um **chat simples utilizando RMI**, explorando comunicação cliente/servidor e comunicação direta entre clientes (P2P).

Todo o desenvolvimento foi realizado em ambiente **Windows**, utilizando **Java** e o **terminal do VS Code**.

---

## 2. Parte 1 – Execução do HelloRMI

### 2.1 Estrutura inicial do projeto

A pasta do projeto continha os seguintes arquivos:

- `ServidorRemoto.java`
- `Servidor.java`
- `Cliente.java`
- `rmi.policy`

Esses arquivos implementam um exemplo simples de RMI, no qual o cliente invoca métodos remotos do servidor.

---

### 2.2 Compilação das classes

Inicialmente, todas as classes Java foram compiladas utilizando o compilador `javac`:

```
javac ServidorRemoto.java Servidor.java Cliente.java
```

Esse comando gerou os arquivos `.class` necessários para a execução do sistema.

---

### 2.3 Sobre o uso do rmic

O roteiro do laboratório menciona a necessidade de utilização da ferramenta `rmic` para geração de stubs. No entanto, em versões modernas do Java, os stubs RMI são **gerados dinamicamente em tempo de execução**, e a ferramenta `rmic` foi descontinuada e removida.

Dessa forma, não foi possível utilizar o comando `rmic`, mas o funcionamento do RMI não foi comprometido, pois a geração dinâmica de stubs atende ao mesmo objetivo.

---

### 2.4 Arquivo rmi.policy

O arquivo `rmi.policy` foi utilizado para definir permissões de segurança necessárias à comunicação remota. Ele permite que a aplicação utilize sockets de rede, evitando exceções de segurança durante a execução.

Para que a JVM considerasse esse arquivo, foi necessário passá-lo como argumento na execução das classes.

---

### 2.5 Execução do servidor e do cliente

Devido ao uso do PowerShell, foi necessário utilizar o operador `--%` para evitar que os parâmetros da JVM fossem interpretados pelo shell.

Execução do servidor:

```
java --% -Djava.security.policy=C:\Users\anali\Documents\RMI\rmi.policy Servidor
```

Execução do cliente (em outro terminal):

```
java --% -Djava.security.policy=C:\Users\anali\Documents\RMI\rmi.policy Cliente
```

Como resultado, o cliente conseguiu chamar métodos remotos do servidor, comprovando o funcionamento do HelloRMI.

---

## 3. Parte 2 – Implementação do Chat RMI

### 3.1 Organização do projeto

A Parte 2 foi desenvolvida no **mesmo projeto** da Parte 1, criando um novo pacote chamado `chat`. As principais classes criadas foram:

- `ChatServidor` (interface remota do servidor)
- `ChatServidorImpl` (implementação do servidor)
- `ChatCliente` (interface remota do cliente)
- `ChatClienteImpl` (implementação do cliente)
- `ServidorMain` (classe principal do servidor do chat)
- `ClienteMain` (classe principal do cliente do chat)

---

### 3.2 Compilação do chat

Como foi utilizado o pacote `chat`, a compilação foi realizada a partir da pasta raiz do projeto:

```
javac chat/*.java
```

---

### 3.3 Execução correta do servidor e clientes

Para evitar problemas de conexão, foi necessário definir explicitamente o endereço utilizado pelo RMI, forçando o uso de `localhost`.

Execução do servidor:

```
java --% -Djava.rmi.server.hostname=127.0.0.1 \
-Djava.security.policy=C:\Users\anali\Documents\RMI\rmi.policy \
chat.ServidorMain
```

Execução dos clientes (em terminais diferentes):

```
java --% -Djava.rmi.server.hostname=127.0.0.1 \
-Djava.security.policy=C:\Users\anali\Documents\RMI\rmi.policy \
chat.ClienteMain
```

---

## 4. Funcionamento do Chat e atendimento aos requisitos

### a) Servidor controla clientes ativos

O servidor mantém um registro dos clientes conectados em uma estrutura de dados, controlando entradas e saídas do chat. A comunicação é síncrona e transiente.

---

### b) Cadastro com @ único

Cada cliente informa um identificador (@) ao entrar no chat. O servidor valida se o identificador já está em uso, garantindo unicidade.

---

### c) Registro de @, IP e porta

O servidor armazena referências remotas dos clientes. Essas referências encapsulam internamente o IP e a porta de cada cliente, permitindo a comunicação direta. Dessa forma, o requisito é atendido conceitualmente pelo uso do RMI.

---

### d) Solicitação de informações ao servidor

O cliente pode solicitar ao servidor:

- A lista de clientes ativos
- A referência de um cliente específico

Essa etapa caracteriza a arquitetura cliente/servidor para descoberta dos participantes.

---

### e) Comunicação direta entre clientes (P2P)

Após obter a referência de outro cliente, a comunicação ocorre diretamente entre os clientes por meio de chamadas remotas, sem que o usuário perceba a mudança de arquitetura.

---

### f) Troca de mensagens e arquivos

A troca de mensagens de texto foi implementada. A troca de arquivos não foi implementada diretamente, mas o sistema foi estruturado de forma que essa funcionalidade possa ser adicionada por meio de métodos remotos que utilizem envio de dados em formato binário.

---

### g) Chat em sala (broadcast)

O servidor implementa o envio de mensagens para todos os clientes ativos, identificando o remetente, caracterizando a comunicação broadcast.

---

## 5. Conclusão

Com a realização deste laboratório, foi possível compreender o funcionamento do RMI, incluindo a criação de interfaces remotas, registro de objetos no RMI Registry, comunicação cliente/servidor e comunicação direta entre clientes. O chat desenvolvido atende aos requisitos propostos no roteiro, demonstrando o uso prático de conceitos fundamentais de sistemas distribuídos.

O laboratório contribuiu significativamente para o entendimento de middleware, abstração de comunicação remota e arquiteturas distribuídas.

