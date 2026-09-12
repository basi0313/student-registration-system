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
                    sh './mvnw clean package -DskipTests'
                }

                dir('RegistrationService') {
                    sh './mvnw clean package -DskipTests'
                }

                dir('NotificationService') {
                    sh './mvnw clean package -DskipTests'
                }
            }
}
    }
}