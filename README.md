# devops-case-study
DevOps Case Study


CICD stages
1. Download code
2. Build code
3. Run automated unit tests
4. Run automated UI/API tests
5. Package code
6. Deploy package

And few more...


Prerequisites
1. Jenkins server up & running
  2. Configure maven, junit, docker, docker hub account, gcp
3. Jenkins slave with maven, docker, etc
4. Jenkins slave with chrome and chromedriver installed. This is to run automated selenium UI tests


ToDo Steps
1. Have your app code ready in github
2. write a simple pipeline code to download the app code
3. Create a pipeline job in Jenkins and configure the above pipeline and try running it.
