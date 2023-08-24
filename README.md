# sanlo-risk-calculator


This project is a single container using:

Java 17 </br>
MAVEN 3.8.4</br>
Spring boot 2.6.4 </br>


The structure of the project is divide in package by feature strategy: 

1) riskcalculator feature</br>
2) riskengine feature

I decide to choose it thinking in future split in new microservices. 
It can be easier to apply strangler pattern when we are using package by feature layers. 


### build

To build the project use: 

mvn clean install</br>

### test

The test strategy was developing using spring embedded container. 
Following this strategy, its possible test all integrations between layers. 



To execute all tests, just use: 

mvn test


### run

You can run this project as a desktop application: 

where: {abosolute_path} = the absolute path dir. </br>
obs: You need path the absolute dir to application find input and output repository. 

```console
java -jar -Dsanlo.riskRating={absolute_path}/sanlo-risk-calculator/input/risk_rating_data.csv -Dsanlo.ltvCacRatio={absolute_path}/sanlo-risk-calculator/input/ltv_cac_ratio.csv -Dsanlo.paybackIndicator={absolute_path}/sanlo-risk-calculator/input/payback.csv -Dsanlo.metricsFilePath={absolute_path}/sanlo-risk-calculator/input/example-app-financial-metrics.csv -Dsanlo.companyFilePath={absolute_path}/sanlo-risk-calculator/input/example-app-companies.csv -Dsanlo.ratingFilePath={absolute_path}/sanlo-risk-calculator/output/rating build/sanlo-risk-calculator.jar
```

An example using my path to execute it: 

```console
java -jar -Dsanlo.riskRating=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/input/risk_rating_data.csv -Dsanlo.ltvCacRatio=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/input/ltv_cac_ratio.csv -Dsanlo.paybackIndicator=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/input/payback.csv -Dsanlo.metricsFilePath=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/input/example-app-financial-metrics.csv -Dsanlo.companyFilePath=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/input/example-app-companies.csv -Dsanlo.ratingFilePath=/Users/eduardo.silva.br/sanlo/sanlo-risk-calculator/output/rating build/sanlo-risk-calculator.jar
 ```   



