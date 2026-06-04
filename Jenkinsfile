pipeline {
    agent any

    stages {
        stage('PULIZIA CONTAINER PRECEDENTI') {
            steps {
                script {
                    // Ferma e cancella il container se esiste già, così non va in errore per il nome duplicato
                    sh 'docker stop drago-container || true'
                    sh 'docker rm drago-container || true'
                }
            }
        }

        stage('DOCKER BUILD') {
            steps {
                // Compila il codice e crea l'immagine direttamente sfruttando il Dockerfile multi-stage
                sh 'docker build -t drago-api .'
            }
        }

        stage('DOCKER RUN') {
            steps {
                // Avvia il nuovo container sulla porta 8082
                sh 'docker run -d -p 8082:8081 --name drago-container drago-api'
            }
        }
    }

    post {
        success {
            echo '🐉 Il drago è stato buildato e deployato con successo da Jenkins!'
        }
        failure {
            echo '❌ Qualcosa è andato storto nella pipeline.'
        }
    }
}