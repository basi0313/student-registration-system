pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Services') {
            steps {
                dir('StudentService') {
                    sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                }

                dir('RegistrationService') {
                    sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                }

                dir('NotificationService') {
                    sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                }
            }
        }
    }
}