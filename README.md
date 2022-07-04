
  
# thymeleaf-students-course-rest-demo
 
**Das ist ein Backend-System zur Verwaltung Studenten/Kurse **

**Verfügbare Funktionen:**

- CRUD-Funktionen für die oben genannten Daten *(sind über die Rest-Schnittstelle verfügbar)*.
- Schutzfunktionen zum Schutz von REST-Schnittstelle, damit die CRUD-Funktionen den Personen entsprechend ihrer Rollen zur Verfügung stehen *(kommt bald)*
- Verschlüsselungsfunktionen einiger sensibler Daten beim Speichern in Datenbanken *(kommt bald)*

**Verwendete Technologien:**

- Java
- Spring Boot
- MySQL-Datenbanken

# Installation
1. Clonen das Projekt. 
2. Importieren Das Projekt als Marven Datei
3. Die Datenbank konfiguration muss angepasst werden 
   1. geh zu application.properties file
   2. muss folgene Block aktualisieren: 
   
   ```  
    spring.datasource.url=jdbc:mysql://localhost:3306/MyDataBase?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false&createDatabaseIfNotExist=true
    spring.datasource.username= your root
    spring.datasource.password= password DB
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
    spring.jpa.generate-ddl=true
    spring.jpa.show-sql=true

4. Starten das Progaram als Java Application

#Das Projekt enthält folgende Route: 
  
    ```
    @RequestMapping("/api")
    @GetMapping("/courses")
    
    @GetMapping("/courses/findSorted/{field}") 
    #sortier nach Attribute (id,name,SerialNummer)
    
    @GetMapping("/courses/pagination/{offSet}/{pageSize}") 
    #Zeigen bestimmte einträge 
    
    @GetMapping("/courses/pagination/{offSet}/{pageSize}/{field}") 
    #Zeigen bestimmer einträge sortiert nach field
    
    @PostMapping("courses/addCourses")
    @PostMapping("/addStudentToCourse")
    @PostMapping("/removeStudentFromCourse")

    @GetMapping("/students")
    
    @GetMapping("/studentsLike/{partName}") 
    #suchen studenten = teilWort 
    
    @PostMapping("/student")
    @PutMapping("/student")
    @DeleteMapping("delete/{StudentId}")
    @GetMapping("/getStudentCourse")
    @GetMapping("/orderByEmail/{email}")
    @GetMapping("/getFirstLastNameAndCourses")
    @GetMapping("/getTest")
    @PostMapping("/addCourseToStudent")
    @PostMapping("/removeAllCourseFromStudent")

# Autor
**Mohammed Bakroon**
