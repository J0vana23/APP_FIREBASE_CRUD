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
🔗 **[Ir para Login/Register](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/pages/LoginRegister.kt)**

---

### 🧑‍💼 Cadastro e Consulta de Dados

* Campos: Nome, Sobrenome, E-mail, Senha e Telefone
* Dados salvos no **Firestore**
* Consulta e listagem completa dos registros salvos  
🔗 **[Ir para Tela Home/Listagem](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/pages/Home.kt)**

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

🔗 **[Ver código completo da MainActivity](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/MainActivity.kt)**
Controla qual tela será exibida:

---

### 🔐 Login/Register – Tela de Autenticação e Cadastro

#### ✅ Login

- Autenticação feita via Firestore (`.whereEqualTo()`)
- Mostra erro se usuário não existe
- Ícone para mostrar/esconder senha 🔒👁️  

🔗 **[Ver função LoginScreen()](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/pages/LoginRegister.kt)**

#### ✍️ Cadastro

- Campos obrigatórios: Nome, Sobrenome, Email, Senha
- Salva os dados no Firebase (coleção `"banco"`)
- Interface amigável e responsiva  

🔗 **[Ver função RegisterScreen()](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/pages/LoginRegister.kt)**

---

### 🏠 Home – Boas-vindas e Listagem de Usuários

#### 🎉 Tela de boas-vindas:

- Exibe o nome do usuário logado
- Botão de sair com `AlertDialog` de confirmação

#### 📋 Lista de usuários:

- Mostra todos os cadastros salvos na coleção `"banco"`
- Campos exibidos: nome, sobrenome, email, senha, telefone
- Navegação via barra inferior com ícones personalizados  

🔗 **[Ver função HomeScreen()](https://github.com/J0vana23/APP_FIREBASE_CRUD/blob/main/app/src/main/java/com/example/app_firebase_crud/pages/Home.kt)**

---

### 🧩 Extras

- 🌙 Dark mode visual
- 🖼️ Imagens e ícones personalizados
- 💬 Feedback visual para ações e erros
- 🔐 Senha protegida com `PasswordVisualTransformation`
- 🧪 Estrutura escalável com `enum class HomeViewState`

---

### 📸 Imagens do App
<table align="center">
  <tr>
    <td><img src="https://github.com/user-attachments/assets/5c1cd92b-fba2-42d9-ba90-323ec806da7d" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/d0379f09-1fd9-4681-9338-594524dee8b0" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/ab67eeac-4b39-42e4-ba33-6a189fe4574a" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/8a37dcdc-d408-4cda-bb56-0d8fa51c8800" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/10e4b761-3e12-417d-8def-9591840f1523" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/f99b79b7-fbe7-4d19-b130-ad8f34ebc274" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/ecca571c-b2e1-45f0-98fd-1882d351baa0" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
    <td><img src="https://github.com/user-attachments/assets/f4c79b46-fa3c-4dab-80c9-3c0729b556b6" width="180" style="background:white; padding:4px; border-radius:10px;"></td>
  </tr>
</table>


---

### 🔚 Considerações Finais

Este projeto mostra como é possível usar **Firebase como backend** para apps Android simples, sem precisar configurar servidores externos. Ele cobre as principais bases:

- 🔐 Autenticação  
- 📁 Armazenamento  
- 🧭 Navegação moderna  
- 🎨 Interface responsiva com Compose


