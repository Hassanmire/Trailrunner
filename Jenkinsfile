pipeline {
  agent any

  environment {
    ROBOT_PATH = "C:\\Users\\xasan\\PycharmProjects\\pythonProject\\.venv\\Scripts\\robot.exe"
  }

  stages {
    stage('Build') {
      steps {
        dir('project') {
          sh 'mvn complie'
        }
      }
    }

    stage('Test') {
      steps {
        dir('project') {
          sh 'mvn test's
        }
      }
    }

    stage('Post Test') {
      steps {
        jacoco(
          execPattern: 'target/*.exec',
          classPattern: 'target/classes',
          sourcePattern: 'src/main/java',
          exclusionPattern: 'src/test*'
        )
        junit testResults: 'project/target/surefire-reports/**/*.xml'
      }
    }

    stage('Run Robot') {
      steps {
        dir('Selenium') {
          powershell """
            \$env:PATH += ';C:\\Users\\xasan\\PycharmProjects\\pythonProject\\.venv\\Scripts'
            & \"\${env:ROBOT_PATH}\" --outputdir results --report report.html --log log.html bilen.robot
          """
        }
      }
    }
  }

  post {
    always {
      robot(
        outputPath: 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Hassanm\\Selenium/results',
        passThreshold: 80.0,
        unstableThreshold: 70.0,
        onlyCritical: false
      )
    }
  }
}
