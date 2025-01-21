pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                // Cloner le dépôt
                git branch: 'main', url: 'https://github.com/franckkoukougnon/school-app.git'
            }
        }

        stage('Build Application') {
            steps {
                // Construire l'application avec Maven
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Construire l'image Docker
                sh 'docker build -t school-bdd-microservice .'
            }
        }

        stage('Deploy Application') {
            steps {
                // Déployer sur un serveur distant
                sshagent(['my-ssh-key']) {
                    sh '''
                        ssh user@server-address "docker stop school-app || true && docker rm school-app || true"
                        ssh user@server-address "docker run -d -p 8080:8083 --name school-app school-bdd-microservice"
                    '''
                }
            }
        }
    }
}
