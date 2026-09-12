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
        stage('Docker Check') {
            steps {
                sh 'docker version'
            }
        }
        stage('Docker Checking') {
            steps {
                sh 'docker info'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t student-service ./StudentService'
                sh 'docker build -t registration-service ./RegistrationService'
                sh 'docker build -t notification-service ./NotificationService'
            }
        }
    }
}