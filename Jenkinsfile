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

        stage('Docker Build') {
            steps {
                sh 'docker build -t basi0304/student-service:latest ./StudentService'
                sh 'docker build -t basi0304/registration-service:latest ./RegistrationService'
                sh 'docker build -t basi0304/notification-service:latest ./NotificationService'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push basi0304/student-service:latest'
                sh 'docker push basi0304/registration-service:latest'
                sh 'docker push basi0304/notification-service:latest'
            }
        }

        stage('Docker Logout') {
            steps {
                sh 'docker logout'
            }
        }

        stage('Docker Compose Check') {
            steps {
                sh 'docker compose version'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker compose pull student-service registration-service notification-service
                    docker rm -f student-service registration-service notification-service || true
                    docker compose up -d --no-deps student-service registration-service notification-service
                '''
            }
        }

        
    }
}