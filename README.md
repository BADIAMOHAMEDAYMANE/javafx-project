🧠 QCM Generator - JavaFX Desktop App
📖 Présentation
QCM Generator est une application desktop robuste conçue pour faciliter l'apprentissage par l'auto-évaluation. Grâce à une interface intuitive développée sous JavaFX, l'utilisateur peut générer des questionnaires interactifs sur des thématiques variées et obtenir un feedback instantané sur ses performances.

🚀 Fonctionnalités Clés
🎯 Sélection Thématique : Choisissez un domaine spécifique pour générer des questions ciblées.

⚡ Génération Dynamique : Les questions sont injectées dynamiquement dans l'interface (Architecture FXML).

📊 Score en Temps Réel : Calcul automatique et affichage des résultats à la fin du quiz.

🎨 UI Moderne : Interface responsive et épurée utilisant les contrôles JavaFX.

🛠️ Stack Technique
Langage : Java (JDK 17+)

GUI : JavaFX & FXML

Design : CSS personnalisé pour le styling des composants

Architecture : Pattern MVC (Model-View-Controller)

⚙️ Installation & Configuration
1. Cloner le projet
Bash
git clone https://github.com/BADIAMOHAMEDAYMANE/javafx-project.git
cd javafx-project
2. Configuration de l'IDE
IntelliJ / Eclipse : Importer le projet en tant que projet Java.

JavaFX SDK : Assurez-vous d'avoir configuré les bibliothèques JavaFX dans les paramètres du projet.

VM Options : ```bash
--module-path /votre/chemin/javafx/lib --add-modules javafx.controls,javafx.fxml


📂 Structure du Projet
Plaintext
javafx-project/
├── src/
│   ├── main/
│   │   ├── java/        # Logique métier & Contrôleurs
│   │   └── resources/   # Fichiers FXML, Images & Styles CSS
├── data/                # (Optionnel) Fichiers JSON/XML des questions
└── README.md
🔮 Roadmap (Évolutions Futures)
En tant qu'étudiant en IA & Data Science, les prochaines étapes prévues sont :

[ ] NLP Integration : Génération automatique de QCM à partir d'un fichier PDF/Texte via un modèle de langage (LLM).

[ ] Analytics Dashboard : Visualisation des statistiques de progression avec des graphiques.

[ ] Export PDF : Possibilité d'exporter le questionnaire généré.

👤 Auteur
Mohamed Aymane Badia Étudiant en 4ème année - Spécialité IA & Data Science
