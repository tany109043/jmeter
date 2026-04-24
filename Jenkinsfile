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
                del /f /q *.jtl 2>nul
                if exist report rmdir /s /q report

                "C:\\Users\\TANISH HANDE\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t src/test/resources/jmeter/test.jmx -l result.jtl -e -o report
                '''
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'report',
                    reportFiles: 'index.html',
                    reportName: 'JMeter Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }
}
