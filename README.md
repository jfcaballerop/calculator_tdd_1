# CalApp
---
Aplicación de calculadora que ilustra el proceso TDD.

## 1. Config JUNIT5
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
## 2. TDD - Red-Green-Refactor
Dentro del proceso de TDD, en esta parte del código se ha usado esta metodología para realizar el proceso de codificación.
Cada persona puede elegir empezar por donde mejor crea o le resulte más cómodo, pero a mí me resulta más cómodo hacerlo dedse los @services.

### TDD - RGF @Services
Vamos a definir todas las operaciones que queremos que haga nuestra calculadora.

**CalculatorService.java**
```java
public interface CalculatorService {

    public Integer sumaInt(int num1, int num2);
    public Integer restaInt(int num1, int num2);
    public Integer sumatorioArrInt(int[] arr);
    public String sumaStrings(String val1, String val2);
}
```

Y así vamos a ir creando primero el Test, esperamos que falle (RED). Lo codificamos probamos hasta que funcione (GREEN) y por ultimo según vayamos codificando vamos mejorando el código (REFACTOR)
```java
public class CalcServicesTest {

    @Autowired
    private CalculatorService calcService = new CalculatorServiceImpl();

    @Test
    public void sumaEnteros() {
        int num1 = 1;
        int num2 = 97;
        assertEquals(98, calcService.sumaInt(num1, num2));
    }
}
```
Ahora codificamos el método:

**CalculatorServiceImpl.java**
```java
    @Override
    public Integer sumaInt(int num1, int num2) {
        return num1 + num2;
    }
```

En un segundo caso podremos pensar que esto solo vale para INTEGERS y nos da por refactorizarlo y admitir más tipos de NUMEROS o dentro de Java NUMBERS.
Volvemos a generar el TEST
```java
public class CalcServicesTest {

    @Autowired
    private CalculatorService calcService = new CalculatorServiceImpl();

  @Test
    public void sumaStrings() {
        String val1 = "34";
        String val2 = "36";
        assertEquals("70.0", calcService.sumaStrings(val1, val2));

    }

    @Test
    public void sumaStringsNumber() {
        String val1 = "35,5";
        String val2 = "35,5";
        assertEquals("71.0", calcService.sumaStrings(val1, val2));

    }
}
```

Y su código:

**CalculatorServiceImpl.java**
```java
    @Override
    public String sumaStrings(String val1, String val2) {
        try {
            Number num1;
            Number num2;
            num1 = NumberFormat.getInstance().parse(val1);
            num2 = NumberFormat.getInstance().parse(val2);
            return "" + (num1.doubleValue() + num2.doubleValue());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
```
### TDD - RGF @RestController

En una segunda parte, se testearía la parte de Controllers.
Aquí entran 2 tipos de pruebas:
* Pruebas unitarias.

* Pruebas de integracion.

### Pruebas unitarias
Por una lado debemos probar sólo el código del Controller, revisando los datos recibidos, y testeando los tipos de datos, si falta, si el tipo no coincide, o se modifica alguno ...

### Pruebas de Integracion
Con @WebMvcTest y MockMVC podremos testear esas llamadas al controller.


```java
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class OperacionesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CalculatorService calcService;

     @Test
    public void startCalc() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/poweron").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("powerOn"));
    }

    @Test
    @DisplayName("POST /sumar")
    public void sumar() throws Exception {
        /*sumar int*/
        JSONObject json = new JSONObject();
        json.put("num1", 1);
        json.put("num2", 2);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/sumar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString())
        )
        // Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //validate operation
        .andExpect(jsonPath("$.res", is(3)));
        
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
```