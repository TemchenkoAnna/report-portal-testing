pipeline {
 agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/TemchenkoAnna/report-portal-testing.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew.bat build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew.bat api-test:test'
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/**/*.xml'
        }
    }
}
