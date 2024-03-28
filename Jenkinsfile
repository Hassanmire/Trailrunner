pipeline {
  agent any

  environment {
    ROBOT_PATH = "C:\\Users\\xasan\\PycharmProjects\\pythonProject\\.venv\\Scripts\\robot.exe"
    SELENIUM_PATH = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Hassanm\\Selenium"
  }

  stages {
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
            & "$env:ROBOT_PATH" --outputdir results --report report.html --log log.html "$env:SELENIUM_PATH\\bilen.robot"
          '''
        }
      }
    }

    stage('Publish Report') {
      steps {
        publishHTMLReport target: 'results/report.html'
      }
    }

    stage('Archive Robot Reports') {
      steps {
        archiveArtifacts artifacts: 'results/*.xml, results/*.html'
      }
    }
  }

  post {
    always {
      step([$class: 'JUnitResultArchiver', testResults: 'project/target/surefire-reports/*.xml'])
    }
  }
}
