pipeline {
    agent any

    stages {
        stage('PULIZIA E BUILD') {
            // Diciamo a Jenkins di usare temporaneamente un'immagine che contiene il client Docker
            agent {
                docker { image 'docker:cli' }
            }
            steps {
                script {
                    // 1. Pulizia vecchi container (se non esistono, non blocca la build)
                    try {
                        sh 'docker stop drago-container'
                        sh 'docker rm drago-container'
                    } catch (Exception e) {
                        echo "Nessun container precedente da rimuovere."
                    }

                    // 2. Build della nuova immagine
                    sh 'docker build -t drago-api .'
                }
            }
        }

        stage('DOCKER RUN') {
            // Anche qui usiamo il client Docker per lanciare il run definitivo sul tuo PC
            agent {
                docker { image 'docker:cli' }
            }
            steps {
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