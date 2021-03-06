# customer_statement_processor


# Requirements

We receive monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.


Input

Each Record consist of Transaction reference , Account number,Start Balance,Mutation,Description,End Balance

Output

There are two validations:

    all transaction references should be unique

    the end balance needs to be validated

At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.


# How To Build the Application

Run command 'mvn clean install' on project root directory

# How To Run the Application

This is a spring console application. It makes use of classpaths, so that it can be run directly in IDE with no additional configuration

To run this locally directly run CustomerStatementProcessor.main Method. 

To run this in environment, build the application and run the application with following command

java -Dloader.path="$(System.DefaultWorkingDirectory)/resources/" -jar CustomerStatementProcessor-*.jar

Here we can configure -Dloader.path and keep our application.properties file, records.csv and records.xml file inside folder $(System.DefaultWorkingDirectory)/resources/ 

# How To make this application as a scheduler

we can make this application as a scheduler by doing the following changes

1. Enabling scheduling with @EnableScheduling annotation in CustomerStatementProcessor class
2. Adding @Scheduled(cron = "0 0 0 1 * ?") annotations to methods CsvRecordProcessor.processCsvRecords and XmlRecordProcessor.processXmlRecords

