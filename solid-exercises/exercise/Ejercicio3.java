import java.util.ArrayList;
import java.util.List;

// ============================================================
//  EJERCICIO 3
//  Contexto: sistema de gestión de empleados de una empresa
//
//  Analiza el código, identifica qué problemas de diseño
//  presenta y explica cómo los corregirías.
// ============================================================

public class Ejercicio3 {

    static class Empleado {
        String nombre;
        String cargo;
        double salarioBase;
        String email;

        Empleado(String nombre, String cargo, double salarioBase, String email) {
            this.nombre = nombre;
            this.cargo = cargo;
            this.salarioBase = salarioBase;
            this.email = email;
        }
    }

    // Esta clase acumula múltiples responsabilidades y fabrica
    // sus propias dependencias internas sin posibilidad de cambiarlas.

    static class GestorEmpleados {

        private List<Empleado> empleados = new ArrayList<>();

        // --- Responsabilidad 1: administración de la lista ---

        void agregar(Empleado e) {
            empleados.add(e);
            System.out.println("Empleado agregado: " + e.nombre);
        }

        List<Empleado> listarTodos() {
            return empleados;
        }

        // --- Responsabilidad 2: cálculo de nómina ---

        double calcularSalarioNeto(Empleado e) {
            double descuentoSalud = e.salarioBase * 0.04;
            double descuentoPension = e.salarioBase * 0.04;
            double descuentoSindical = e.salarioBase * 0.01;
            return e.salarioBase - descuentoSalud - descuentoPension - descuentoSindical;
        }

        void imprimirNomina() {
            System.out.println("\n--- NOMINA DEL MES ---");
            for (Empleado e : empleados) {
                System.out.printf("%-15s | Bruto: $%10.2f | Neto: $%10.2f%n",
                        e.nombre, e.salarioBase, calcularSalarioNeto(e));
            }
        }

        // --- Responsabilidad 3: persistencia (acoplada a MySQL) ---
        //
        // Si se cambia la base de datos, hay que modificar esta clase.
        // Además es imposible probar GestorEmpleados sin una BD real.

        void guardarEmpleado(Empleado e) {
            // Dependencia concreta instanciada aquí mismo — no hay forma de reemplazarla
            MySQLConector db = new MySQLConector();
            db.conectar();
            db.ejecutar("INSERT INTO empleados (nombre, cargo, salario) VALUES ('"
                    + e.nombre + "', '" + e.cargo + "', " + e.salarioBase + ")");
            db.desconectar();
        }

        void cargarEmpleados() {
            MySQLConector db = new MySQLConector();
            db.conectar();
            db.ejecutar("SELECT * FROM empleados");
            // Simulación: en la vida real aquí se mapearían los resultados
            System.out.println("[BD] Empleados cargados desde MySQL.");
            db.desconectar();
        }

        // --- Responsabilidad 4: notificaciones (acoplada a SMTP) ---
        //
        // Si se cambia el proveedor de correo, hay que modificar esta clase.

        void notificarDesprendimientoPago(Empleado e) {
            ServidorSMTP smtp = new ServidorSMTP();
            smtp.conectar("smtp.empresa.com", 587);
            smtp.enviar(
                    e.email,
                    "Desprendimiento de nomina",
                    "Hola " + e.nombre + ", tu pago neto es $" + calcularSalarioNeto(e));
            smtp.desconectar();
        }
    }

    // --- Clases de infraestructura concretas ---

    static class MySQLConector {
        void conectar() {
            System.out.println("[MySQL] Conectando a jdbc:mysql://localhost:3306/rrhh");
        }

        void ejecutar(String sql) {
            System.out.println("[MySQL] " + sql);
        }

        void desconectar() {
            System.out.println("[MySQL] Conexion cerrada.");
        }
    }

    static class ServidorSMTP {
        void conectar(String host, int puerto) {
            System.out.println("[SMTP] Conectando a " + host + ":" + puerto);
        }

        void enviar(String destino, String asunto, String cuerpo) {
            System.out.println("[SMTP] Para: " + destino);
            System.out.println("[SMTP] Asunto: " + asunto);
            System.out.println("[SMTP] Cuerpo: " + cuerpo);
        }

        void desconectar() {
            System.out.println("[SMTP] Sesion cerrada.");
        }
    }

    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();

        Empleado e1 = new Empleado("Laura Gomez", "Desarrolladora", 4500000, "laura@empresa.com");
        Empleado e2 = new Empleado("Pedro Vargas", "Disenador", 3200000, "pedro@empresa.com");
        Empleado e3 = new Empleado("Ana Rios", "Analista", 3800000, "ana@empresa.com");

        gestor.agregar(e1);
        gestor.agregar(e2);
        gestor.agregar(e3);

        gestor.imprimirNomina();

        System.out.println();
        gestor.guardarEmpleado(e1);

        System.out.println();
        gestor.notificarDesprendimientoPago(e1);
    }
}
