import java.util.ArrayList;
import java.util.List;

// ============================================================
// EJERCICIO 3 - REFACTORIZADO COMPLETO
// Principios SOLID
// ============================================================

public class Ejercicio3Refactorizado {

    // ============================================================
    // 1. ENTIDAD DE DOMINIO
    // SRP: Empleado únicamente representa los datos del empleado.
    // ============================================================

    static class Empleado {
        private final String nombre;
        private final String cargo;
        private final double salarioBase;
        private final String email;

        public Empleado(
                String nombre,
                String cargo,
                double salarioBase,
                String email) {

            this.nombre = nombre;
            this.cargo = cargo;
            this.salarioBase = salarioBase;
            this.email = email;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCargo() {
            return cargo;
        }

        public double getSalarioBase() {
            return salarioBase;
        }

        public String getEmail() {
            return email;
        }
    }

    // ============================================================
    // 2. ABSTRACCIONES
    // DIP + OCP + ISP
    // ============================================================

    // ------------------------------------------------------------
    // Contrato para persistencia
    // ------------------------------------------------------------

    interface EmpleadoRepository {

        void guardar(Empleado empleado);

        List<Empleado> obtenerTodos();
    }

    // ------------------------------------------------------------
    // Contrato para notificaciones
    // ------------------------------------------------------------

    interface ServicioNotificacion {

        void enviarNotificacion(
                String destino,
                String asunto,
                String cuerpo);
    }

    // ------------------------------------------------------------
    // Contrato para cálculo de nómina
    // ------------------------------------------------------------

    interface CalculadorNomina {

        double calcularSalarioNeto(Empleado empleado);
    }

    // ============================================================
    // 3. IMPLEMENTACIONES DE INFRAESTRUCTURA
    // ============================================================

    // ------------------------------------------------------------
    // Implementación de persistencia MySQL
    // OCP + LSP
    // ------------------------------------------------------------

    static class MySQLRepository implements EmpleadoRepository {

        private final List<Empleado> empleados = new ArrayList<>();

        @Override
        public void guardar(Empleado empleado) {

            System.out.println("[MySQL] Conectando a base de datos...");

            /*
             * En una aplicación real se utilizaría JDBC con
             * PreparedStatement para evitar SQL Injection.
             *
             * Ejemplo:
             *
             * PreparedStatement ps = connection.prepareStatement(
             * "INSERT INTO empleados " +
             * "(nombre, cargo, salario, email) " +
             * "VALUES (?, ?, ?, ?)"
             * );
             *
             * ps.setString(1, empleado.getNombre());
             * ps.setString(2, empleado.getCargo());
             * ps.setDouble(3, empleado.getSalarioBase());
             * ps.setString(4, empleado.getEmail());
             */

            // Simulación de persistencia
            empleados.add(empleado);

            System.out.printf(
                    "[MySQL] INSERT INTO empleados " +
                            "(nombre, cargo, salario, email) " +
                            "VALUES ('%s', '%s', %.2f, '%s')%n",
                    empleado.getNombre(),
                    empleado.getCargo(),
                    empleado.getSalarioBase(),
                    empleado.getEmail());

            System.out.println("[MySQL] Conexión cerrada.");
        }

        @Override
        public List<Empleado> obtenerTodos() {

            System.out.println(
                    "[MySQL] Ejecutando SELECT * FROM empleados...");

            // Copia defensiva
            return new ArrayList<>(empleados);
        }
    }

    // ============================================================
    // Implementación de notificaciones SMTP
    // OCP + LSP
    // ============================================================

    static class SMTPNotificador implements ServicioNotificacion {

        private final String host;
        private final int puerto;

        public SMTPNotificador(String host, int puerto) {
            this.host = host;
            this.puerto = puerto;
        }

        @Override
        public void enviarNotificacion(
                String destino,
                String asunto,
                String cuerpo) {

            System.out.printf(
                    "[SMTP] Conectado a %s:%d%n",
                    host,
                    puerto);

            System.out.printf(
                    "[SMTP] Para: %s | Asunto: %s%n",
                    destino,
                    asunto);

            System.out.printf(
                    "[SMTP] Mensaje: %s%n",
                    cuerpo);

            System.out.println("[SMTP] Sesión cerrada.");
        }
    }

    // ============================================================
    // Implementación estándar del cálculo de nómina
    // SRP + OCP + LSP
    // ============================================================

    static class CalculadorNominaEstandar
            implements CalculadorNomina {

        @Override
        public double calcularSalarioNeto(Empleado empleado) {

            double salario = empleado.getSalarioBase();

            double descuentoSalud = salario * 0.04;
            double descuentoPension = salario * 0.04;
            double descuentoSindical = salario * 0.01;

            return salario
                    - descuentoSalud
                    - descuentoPension
                    - descuentoSindical;
        }
    }

    // ============================================================
    // 4. SERVICIO DE EMPLEADOS
    // SRP + DIP
    //
    // Su única responsabilidad es gestionar las operaciones
    // relacionadas con empleados.
    // ============================================================

    static class ServicioEmpleado {

        private final EmpleadoRepository repository;

        public ServicioEmpleado(EmpleadoRepository repository) {
            this.repository = repository;
        }

        public void agregar(Empleado empleado) {

            repository.guardar(empleado);

            System.out.println(
                    "Empleado agregado: "
                            + empleado.getNombre());
        }

        public List<Empleado> listarTodos() {

            return repository.obtenerTodos();
        }
    }

    // ============================================================
    // 5. RESULTADO DE NÓMINA
    //
    // Representa el resultado del cálculo.
    // Permite separar cálculo de presentación.
    // ============================================================

    static class ReciboNomina {

        private final Empleado empleado;
        private final double salarioBruto;
        private final double salarioNeto;

        public ReciboNomina(
                Empleado empleado,
                double salarioBruto,
                double salarioNeto) {

            this.empleado = empleado;
            this.salarioBruto = salarioBruto;
            this.salarioNeto = salarioNeto;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public double getSalarioBruto() {
            return salarioBruto;
        }

        public double getSalarioNeto() {
            return salarioNeto;
        }
    }

    // ============================================================
    // 6. SERVICIO DE NÓMINA
    // SRP + DIP
    //
    // Se encarga de generar la información de nómina y coordinar
    // el envío del desprendimiento.
    // ============================================================

    static class ServicioNomina {

        private final CalculadorNomina calculador;
        private final ServicioNotificacion notificador;

        public ServicioNomina(
                CalculadorNomina calculador,
                ServicioNotificacion notificador) {

            this.calculador = calculador;
            this.notificador = notificador;
        }

        // --------------------------------------------------------
        // Generar recibo de nómina
        // --------------------------------------------------------

        public ReciboNomina generarRecibo(Empleado empleado) {

            double salarioBruto = empleado.getSalarioBase();

            double salarioNeto = calculador.calcularSalarioNeto(empleado);

            return new ReciboNomina(
                    empleado,
                    salarioBruto,
                    salarioNeto);
        }

        // --------------------------------------------------------
        // Enviar desprendimiento de pago
        // --------------------------------------------------------

        public void enviarDesprendimiento(Empleado empleado) {

            ReciboNomina recibo = generarRecibo(empleado);

            String mensaje = String.format(
                    "Hola %s, tu pago neto es $%.2f",
                    empleado.getNombre(),
                    recibo.getSalarioNeto());

            notificador.enviarNotificacion(
                    empleado.getEmail(),
                    "Desprendimiento de nómina",
                    mensaje);
        }
    }

    // ============================================================
    // 7. IMPRESOR DE NÓMINA
    // SRP
    //
    // Únicamente se encarga de presentar la información.
    // No calcula salarios ni accede a la base de datos.
    // ============================================================

    static class ImpresorNomina {

        public void imprimir(List<ReciboNomina> recibos) {

            System.out.println("\n--- NÓMINA DEL MES ---");

            for (ReciboNomina recibo : recibos) {

                Empleado empleado = recibo.getEmpleado();

                System.out.printf(
                        "%-15s | Bruto: $%10.2f | Neto: $%10.2f%n",
                        empleado.getNombre(),
                        recibo.getSalarioBruto(),
                        recibo.getSalarioNeto());
            }
        }
    }

    // ============================================================
    // 8. PUNTO DE ENTRADA
    //
    // Composition Root:
    // Aquí se crean las implementaciones concretas y se inyectan
    // las dependencias.
    // ============================================================

    public static void main(String[] args) {

        // --------------------------------------------------------
        // Creación de implementaciones concretas
        // --------------------------------------------------------

        EmpleadoRepository repository = new MySQLRepository();

        ServicioNotificacion notificador = new SMTPNotificador(
                "smtp.empresa.com",
                587);

        CalculadorNomina calculador = new CalculadorNominaEstandar();

        // --------------------------------------------------------
        // Inyección de dependencias
        // --------------------------------------------------------

        ServicioEmpleado servicioEmpleado = new ServicioEmpleado(repository);

        ServicioNomina servicioNomina = new ServicioNomina(
                calculador,
                notificador);

        ImpresorNomina impresorNomina = new ImpresorNomina();

        // --------------------------------------------------------
        // Creación de empleados
        // --------------------------------------------------------

        Empleado e1 = new Empleado(
                "Laura Gómez",
                "Desarrolladora",
                4500000,
                "laura@empresa.com");

        Empleado e2 = new Empleado(
                "Pedro Vargas",
                "Diseñador",
                3200000,
                "pedro@empresa.com");

        Empleado e3 = new Empleado(
                "Ana Ríos",
                "Analista",
                3800000,
                "ana@empresa.com");

        // --------------------------------------------------------
        // Registrar empleados
        // --------------------------------------------------------

        servicioEmpleado.agregar(e1);
        servicioEmpleado.agregar(e2);
        servicioEmpleado.agregar(e3);

        // --------------------------------------------------------
        // Obtener empleados
        // --------------------------------------------------------

        List<Empleado> empleados = servicioEmpleado.listarTodos();

        // --------------------------------------------------------
        // Generar recibos de nómina
        // --------------------------------------------------------

        List<ReciboNomina> recibos = new ArrayList<>();

        for (Empleado empleado : empleados) {

            ReciboNomina recibo = servicioNomina.generarRecibo(empleado);

            recibos.add(recibo);
        }

        // --------------------------------------------------------
        // Imprimir nómina
        // --------------------------------------------------------

        impresorNomina.imprimir(recibos);

        // --------------------------------------------------------
        // Enviar desprendimiento de pago
        // --------------------------------------------------------

        System.out.println();

        servicioNomina.enviarDesprendimiento(e1);
    }
}
