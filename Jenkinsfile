pipeline {
    agent any

    // Questo dice a Jenkins di usare il tool Docker che gestisce il plugin
    tools {
        dockerTool 'Moby Market' // Jenkins scaricherà il client docker se necessario
    }

    stages {
        stage('PULIZIA CONTAINER PRECEDENTI') {
            steps {
                script {
                    // Usiamo una cattura degli errori nativa per evitare il blocco se il container non esiste
                    try {
                        sh 'docker stop drago-container'
                        sh 'docker rm drago-container'
                    } catch (Exception e) {
                        echo "Nessun container precedente da rimuovere: ${e.message}"
                    }
                }
            }
        }

        stage('DOCKER BUILD') {
            steps {
                script {
                    // Il plugin gestisce la build dell'immagine leggendo il Dockerfile nella root
                    docker.build('drago-api')
                }
            }
        }

        stage('DOCKER RUN') {
            steps {
                script {
                    // Il plugin fa girare il container mappando le porte richieste
                    docker.image('drago-api').run('-d -p 8082:8081 --name drago-container')
                }
            }
        }
    }

    post {
        success {
            echo '🐉 Il drago è stato buildato e deployato con successo da Jenkins usando il plugin nativo!'
        }
        failure {
            echo '❌ Qualcosa è andato storto nella pipeline.'
        }
    }
}