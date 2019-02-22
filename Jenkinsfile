pipeline {
    agent any

    tools {
      maven 'Maven 3.6.0'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -f ./nms-dashboard/pom.xml -Dmaven.test.failure.ignore=true clean compile'
            }
            }

        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn -f ./nms-dashboard/pom.xml -Dmaven.test.failure.ignore=true test'
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging....'
                sh 'mvn -f ./nms-dashboard/pom.xml -DskipTests package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
