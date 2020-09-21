# CalApp
Aplicación de calculadora que ilustra el proceso TDD.

## Config JUNIT5
Para configurar el uso de Junit5 con SpringBoot, se debe marcar y exlcuir la versión de Junit4.

pom.xml
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```
## TDD - Red-Green-Refactor
Dentro del proceso de TDD, en esta parte del código se ha usado esta metodología para realizar