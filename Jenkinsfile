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

                stage('Kubernetes Check') {
                    steps {
                        sh 'kubectl version --client'
                        sh 'kubectl config current-context'
                        sh 'kubectl get nodes'
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

        stage('Deploy Student Service to Kubernetes') {
            steps {
                sh '''
                    kubectl set image deployment/student-service \
                        student-service=basi0304/student-service:latest

                    kubectl rollout status deployment/student-service --timeout=120s

                    kubectl get deployment student-service -o wide
                    kubectl get pods -l app=student-service
                '''
            }
        }
    }
}
