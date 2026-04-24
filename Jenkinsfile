pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/tany109043/jmeter.git'
            }
        }

        stage('Run JMeter Test') {
            steps {
                bat '''
                cd %WORKSPACE%
                del /f /q *.jtl
                if exist report rmdir /s /q report
                "C:\\apache-jmeter\\bin\\jmeter.bat" -n -t src/test/resources/jmeter/test.jmx -l result.jtl -e -o report
                '''
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'report',
                    reportFiles: 'index.html',
                    reportName: 'JMeter Report'
                ])
            }
        }
    }
}
