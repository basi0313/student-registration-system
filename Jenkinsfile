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
                    bat 'mvn clean package -DskipTests'
                }

                dir('RegistrationService') {
                    bat 'mvn clean package -DskipTests'
                }

                dir('NotificationService') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }
    }
}