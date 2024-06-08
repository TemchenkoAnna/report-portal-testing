pipeline {
     agent { docker { image 'openjdk:17' } }
       triggers { pollSCM('H 4 * * *') }

    tools {
        jdk 'jdk17'
        gradle 'Gradle'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/TemchenkoAnna/report-portal-testing.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew api-test:test'
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/**/*.xml'
        }
    }
}