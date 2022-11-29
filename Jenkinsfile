pipeline {
  agent any

  options {
    ansiColor('xterm')
  }

  environment {
    SONAR = credentials('SONAR')
    SSH = credentials('SSH')
  }

  stages {

    stage('Compile Code') {
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('SonarQube Analysis') {
      steps {
        sh 'sonar-scanner -Dsonar.host.url=http://172.31.14.53:9000 -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW} -Dsonar.projectKey=ticketing -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
      }
    }

    stage('Build Package') {
      steps {
        sh '''
          mvn package
          mkdir -p terraform/ansible/roles/ticketingapp/files
          cp target/ticketingSystem-0.0.1-SNAPSHOT.war  terraform/ansible/roles/ticketingapp/files/ticketingapp.war
        '''
      }
    }

    stage('Create App Server & Deploy Application') {
      steps {
        sh '''
          cd terraform 
          terraform init 
          terraform apply -auto-approve -var SSH_USR=${SSH_USR} -var SSH_PSW=${SSH_PSW}
        '''
      }
    }

  }

}
