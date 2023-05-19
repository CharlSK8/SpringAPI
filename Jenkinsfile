pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Clone') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    git 'https://github.com/CharlSK8/SpringAPI.git'
                }
            }
        }
        stage('Build') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    sh "mvn -DskipTests clean package"
                }
            }
        }
        stage('Test') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    sh "mvn clean install"
                }
            }
        }
        stage('Sonar') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    withSonarQubeEnv('sonarqube'){
                        sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Pcoverage"
                    }
                }
            }
        }
        stage('Quality gate') {
            steps {

                sleep(10) //seconds

                timeout(time: 2, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy') {
            steps {
                echo "mvn spring-boot:run"
            }
        }
    }
}