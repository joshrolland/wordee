# Wordee installation and use guide

### Requirements
* Java 17
* Maven 3.8.4

### To install
1. Clone git repo; using either HTTPS or SSH link)
2. Build project; using either IDE button or 'mvn clean install'
3. Launch project; using either IDE button or 'mvn spring-boot:run'

### To use
1. Upload file via POST to http://localhost:8080/analyseWords; either using Postman or similar or:
   * 'curl -F "filePath=ENTER_PATH_HERE" http://localhost:8080/analyseWords'
2. Check the response for your analysis :)

### Happy wordee-ing!
