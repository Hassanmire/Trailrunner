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
                    & "C:\\Users\\xasan\\Documents\\mvn\\apache-maven-3.9.6\\bin\\mvn" clean install
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                dir('project') {
                    powershell '''
                    & "C:\\Users\\xasan\\Documents\\mvn\\apache-maven-3.9.6\\bin\\mvn" test
                    '''
                }
            }
        }
        
        stage('Post Test') {
            steps {
                jacoco(execPattern: '**/target/jacoco.exec')
            }
        }
        
        stage('Run Robot') {
            steps {
                dir('Selenium') {
                    powershell '''
                    & "C:\\Users\\xasan\\PycharmProjects\\pythonProject\\.venv\\Scripts\\robot.exe" --outputdir results bilen.robot
                    '''
                }
            }
        }
    }

    post {
        always {
            step([$class: 'JUnitResultArchiver', testResults: 'project/target/surefire-reports/*.xml'])
        }
    }
}

