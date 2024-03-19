pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Hassanmire/Trailrunner.git'
            }
        }

        stage('Build') {
            steps {
                dir('project') {
                    powershell '''
                    & "C:\\Users\\xasan\\OneDrive\\Dokument\\mvn\\apache-maven-3.9.6\\bin\\mvn" clean install
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                dir('project') {
                    powershell '''
                    & "C:\\Users\\xasan\\OneDrive\\Dokument\\mvn\\apache-maven-3.9.6\\bin\\mvn" test
                    '''
                }
            }
        }

        stage('Post Test') {
            steps {
                jacoco(execPattern: '**/target/jacoco.exec')
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Run Robot') {
            steps {
                dir('Selenium') {
                    powershell '''
                    & "C:\\Users\\xasan\\OneDrive\\Dokument\\mvn\\apache-maven-3.9.6\\bin\\mvn" robotframework:run
                    '''
                }
            }
        }
          stage('Publish Test Results') {
            steps {
                junit '**/results/*.xml'
            }
        }
    }
}
       
