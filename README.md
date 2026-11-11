# 🧠 QCM Generator - JavaFX Desktop App

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/JavaFX-000000?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Architecture-MVC-green?style=for-the-badge" />
</p>

## 📖 Présentation
**QCM Generator** est une application desktop robuste conçue pour faciliter l'apprentissage par l'auto-évaluation. Grâce à une interface intuitive développée sous **JavaFX**, l'utilisateur peut générer des questionnaires interactifs sur des thématiques variées et obtenir un feedback instantané sur ses performances.

---

## 🚀 Fonctionnalités Clés
* 🎯 **Sélection Thématique** : Choisissez un domaine spécifique pour générer des questions ciblées.
* ⚡ **Génération Dynamique** : Les questions sont injectées dynamiquement dans l'interface (**Architecture FXML**).
* 📊 **Score en Temps Réel** : Calcul automatique et affichage des résultats à la fin du quiz.
* 🎨 **UI Moderne** : Interface responsive et épurée utilisant les contrôles JavaFX et des **styles CSS JavaFX** intégrés aux vues FXML.

---

## 🛠️ Stack Technique
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-%23FF0000.svg?style=for-the-badge&logo=java&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/maven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

---

## 🗄️ Données

Les matières, questions et propositions de réponses sont stockées dans **MySQL** et lues via **JDBC** (pattern **DAO** : interfaces `SubjectDao`, `QuestionDao`, `PropositionDao` et leurs implémentations).

---

## ⚙️ Installation & Configuration

### 1. Cloner le projet

```bash
git clone https://github.com/BADIAMOHAMEDAYMANE/javafx-project.git
cd javafx-project
```

### 2. Base de données

Créer une base MySQL `javafx` (port 3306), puis adapter l'utilisateur et le mot de passe dans `src/main/java/com/jmc/dao/config/DatabaseConnection.java`.

### 3. Lancer l'application (Java 21, Maven)

```bash
mvn clean javafx:run
```

---

## 📂 Structure du projet

```
javafx-project/
├── pom.xml                          # Maven : JavaFX 21, connecteur MySQL
└── src/main/
    ├── java/com/jmc/
    │   ├── Main.java                # Point d'entrée JavaFX
    │   ├── model/                   # Subject, Question, Proposition, Quiz
    │   ├── dao/                     # Interfaces DAO + impl/ + config/ (connexion JDBC)
    │   ├── service/                 # QuestionService (logique métier)
    │   └── controller/              # MainController, QuizController, ResultController
    └── resources/                   # Vues FXML : main-view, quiz-view, result-view
```

Architecture **MVC** : les vues FXML sont pilotées par les contrôleurs, qui passent par la couche service puis la couche DAO.

---

## 🔮 Roadmap

- [ ] Génération automatique de QCM à partir d'un PDF ou d'un texte avec un modèle de langage (LLM)
- [ ] Tableau de bord des statistiques de progression
- [ ] Export PDF du questionnaire

---

## 👤 Auteur

**Mohamed Aymane Badia** — élève ingénieur en 5ᵉ année, IA & Data Science (EMSI)
