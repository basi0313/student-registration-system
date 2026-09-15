pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Services') {
            parallel {

                stage('Build Student Service') {
                    steps {
                        dir('StudentService') {
                            sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                        }
                    }
                }

                stage('Build Registration Service') {
                    steps {
                        dir('RegistrationService') {
                            sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                        }
                    }
                }

                stage('Build Notification Service') {
                    steps {
                        dir('NotificationService') {
                            sh 'chmod +x mvnw && ./mvnw clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            parallel {

                stage('Build Student Image') {
                    steps {
                        sh 'docker build -t basi0304/student-service:latest ./StudentService'
                    }
                }

                stage('Build Registration Image') {
                    steps {
                        sh 'docker build -t basi0304/registration-service:latest ./RegistrationService'
                    }
                }

                stage('Build Notification Image') {
                    steps {
                        sh 'docker build -t basi0304/notification-service:latest ./NotificationService'
                    }
                }
            }
        }

        stage('Preparation') {
            parallel {

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
                                echo "$DOCKER_PASSWORD" | docker login \
                                    -u "$DOCKER_USERNAME" \
                                    --password-stdin
                            '''
                        }
                    }
                }

                stage('Docker Compose Check') {
                    steps {
                        sh 'docker compose version'
                    }
                }
            }
        }

        stage('Docker Push') {
            parallel {

                stage('Push Student Image') {
                    steps {
                        sh 'docker push basi0304/student-service:latest'
                    }
                }

                stage('Push Registration Image') {
                    steps {
                        sh 'docker push basi0304/registration-service:latest'
                    }
                }

                stage('Push Notification Image') {
                    steps {
                        sh 'docker push basi0304/notification-service:latest'
                    }
                }
            }
        }

        stage('Docker Logout') {
            steps {
                sh 'docker logout'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker compose pull student-service registration-service notification-service

                    docker rm -f student-service registration-service notification-service || true

                    docker compose up -d --no-deps \
                        student-service \
                        registration-service \
                        notification-service
                '''
            }
        }
    }
}