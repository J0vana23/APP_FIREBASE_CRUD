[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=1877F2&size=35&center=true&vCenter=true&width=1000&lines=App+Firebase+Crud;Aula+de+Pam+II)](https://git.io/typing-svg)

<p align="center">
  <img width="250" height="250" alt="image1" src="https://firebase.google.com/downloads/brand-guidelines/PNG/logo-logomark.png" />
  <img width="250" height="250" alt="image2" src="https://github.com/user-attachments/assets/6bebcdfa-5c77-4c17-8e5c-f8602354d9db" />
  <img width="250" height="250" alt="image3" src="https://github.com/user-attachments/assets/e74e4f57-01dd-4ca2-8141-327c9142ef1e" />
</p>

# 📱 App Firebase CRUD - Kotlin + Jetpack Compose


---

## 🚀 Sobre o Projeto

Este é um **aplicativo Android com CRUD completo usando Firebase**, feito com **Kotlin** e **Jetpack Compose**.  
Ele permite que usuários se cadastrem, façam login, visualizem seus dados e vejam a lista completa de registros salvos no Firestore.

> 🎯 Projeto desenvolvido na disciplina **PAM II** como parte do 3º bimestre.

---

## 📱 Funcionalidades

### 🔐 Autenticação de Usuários

* Login e cadastro usando **Firebase Firestore**
* Validação de e-mail e senha diretamente no banco
* Mensagens de erro amigáveis  
🔗 **[Ir para Login/Register](#-loginregister---tela-de-autenticação-e-cadastro)**

---

### 🧑‍💼 Cadastro e Consulta de Dados

* Campos: Nome, Sobrenome, E-mail, Senha e Telefone
* Dados salvos no **Firestore**
* Consulta e listagem completa dos registros salvos  
🔗 **[Ir para Tela Home/Listagem](#-home---boas-vindas-e-listagem-de-usuários)**

---

### 📲 Navegação Entre Telas

* App dividido em 3 partes:

  * 🏠 **MainActivity**: controla a navegação entre as telas
  * 🔐 **Login/Register**: telas de autenticação
  * 📄 **Home**: boas-vindas e listagem de usuários
* Gerenciado via `NavHost` e `rememberNavController`

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia                  | Função                                        |
| ---------------------------| ---------------------------------------------|
| 🔹 Kotlin                  | Linguagem principal do projeto                |
| 🎨 Jetpack Compose         | Criação da interface moderna e responsiva     |
| ☁️ Firebase Firestore      | Armazenamento e leitura de dados dos usuários |
| 🔐 Firebase Auth (simulado)| Validação de login usando Firestore           |
| 🧪 Android Studio          | Ambiente de desenvolvimento                   |

---

## 📁 Estrutura de Telas

### 🔹 `MainActivity.kt` - Navegação

Controla qual tela será exibida:

```kotlin
startDestination = "login"
````

### 🚦 Navegação Principal

- Ao logar, vai para: `home/{userName}`
- Ao cadastrar, volta para login  

🔗 **[Ver código completo da MainActivity](#mainactivity---controle-de-navegação)**
Controla qual tela será exibida:

---

### 🔐 Login/Register – Tela de Autenticação e Cadastro

#### ✅ Login

- Autenticação feita via Firestore (`.whereEqualTo()`)
- Mostra erro se usuário não existe
- Ícone para mostrar/esconder senha 🔒👁️  

<img src="https://github.com/user-attachments/assets/a1ac7234-53bd-4f51-a69b-78c1c4ad5060" alt="icon" width="16" height="16" style="vertical-align:middle; margin-right:6px;" /> **[Ver função LoginScreen()](#loginscreen)**

#### ✍️ Cadastro

- Campos obrigatórios: Nome, Sobrenome, Email, Senha
- Salva os dados no Firebase (coleção `"banco"`)
- Interface amigável e responsiva  

🔗 **[Ver função RegisterScreen()](#registerscreen)**

---

### 🏠 Home – Boas-vindas e Listagem de Usuários

#### 🎉 Tela de boas-vindas:

- Exibe o nome do usuário logado
- Botão de sair com `AlertDialog` de confirmação

#### 📋 Lista de usuários:

- Mostra todos os cadastros salvos na coleção `"banco"`
- Campos exibidos: nome, sobrenome, email, senha, telefone
- Navegação via barra inferior com ícones personalizados  

🔗 **[Ver função HomeScreen()](#homescreen)**

---

### 🧩 Extras

- 🌙 Dark mode visual
- 🖼️ Imagens e ícones personalizados
- 💬 Feedback visual para ações e erros
- 🔐 Senha protegida com `PasswordVisualTransformation`
- 🧪 Estrutura escalável com `enum class HomeViewState`

---

### 📸 Imagens do App

<p align="center">
  <img width="250" src="https://github.com/user-attachments/assets/6fd640ff-1a66-4702-a459-92b9ac7008dc" />
  <img width="250" src="https://github.com/user-attachments/assets/6bebcdfa-5c77-4c17-8e5c-f8602354d9db" />
  <img width="250" src="https://github.com/user-attachments/assets/e74e4f57-01dd-4ca2-8141-327c9142ef1e" />
</p>

---

### 🔚 Considerações Finais

Este projeto mostra como é possível usar **Firebase como backend** para apps Android simples, sem precisar configurar servidores externos. Ele cobre as principais bases:

- 🔐 Autenticação  
- 📁 Armazenamento  
- 🧭 Navegação moderna  
- 🎨 Interface responsiva com Compose


