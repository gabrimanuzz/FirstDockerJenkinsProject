pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t drago-api .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker stop drago-api-container || true'
                sh 'docker rm drago-api-container || true'
                sh 'docker run -d -p 8082:8081 --name drago-api-container drago-api'
            }
        }
    }
}
