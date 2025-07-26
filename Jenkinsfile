pipeline
{
    agent any
    tools {
        maven 'maven_3_8_1'
    }

    stages
    {
        stage('Build')
        {
            steps
            {
                echo ("Build by developers!!")
            }
            post
            {
                success
                {
                    echo ("Running unit test cases by devs!!")
                }
            }
        }

        stage('Deploy to QA')
        {
            steps
            {
                echo("deploy to QA")
            }
        }

        stage ('Regression Automation test cases by QA')
        {
            steps
            {
                catchError (buildResult : 'SUCCESS', stageResult: 'FAILURE' ){
                    git 'https://github.com/MorningEvening/Playwright_July2025'
                    sh "mvn clean test -DsuiteXmlFile=src/resources/config/testrunners/testng_regression.xml"
                }
            }
        }

        stage ('Publish Extent report')
        {
            steps
            {
                publishHTML ([allowMissing:false,
                alwaysLinkToLastBuild:false,
                keepAll:true,
                reportDir: 'build',
                reportFiles: 'TestExecutionReport.html',
                reportName: 'HTML Extent Report',
                reportTitles: ''])
            }
        }

    }

}