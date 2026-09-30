pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dbrowser=chrome'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*.html,target/**/*.png', allowEmptyArchive: true
        }
    }
}
