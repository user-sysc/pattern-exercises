package despues;

import despues.domain.Request;
import despues.domain.User;
import despues.exception.ForbiddenException;
import despues.exception.UnauthorizedException;
import despues.handler.AttentionHandler;
import despues.handler.AuthHandler;
import despues.handler.CacheHandler;
import despues.handler.Handler;
import despues.handler.RoleHandler;
import despues.handler.SanitizeHandler;
import despues.service.AttentionService;
import despues.service.AuthService;
import despues.service.Cache;
import despues.service.Repo;
import despues.service.Sanitizer;

/**
 * DESPUÉS: Procesamiento de atenciones médicas con Chain of Responsibility.
 *
 * Cada responsabilidad (autenticación, rol, saneamiento, caché, guardado)
 * es un eslabón desacoplado e intercambiable.
 */
public class DespuesMain {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   DESPUÉS DEL PATRÓN: Cadena de Responsabilidad (CoR)");
        System.out.println("============================================================");

        AuthService auth = new AuthService("token-valido-123");
        User nurseUser = new User("Enfermera_Elena", "NURSE");
        Sanitizer sanitizer = new Sanitizer();
        Cache cache = new Cache();
        Repo repo = new Repo();

        // Precargar un registro en caché para simular respuesta rápida
        cache.put("Control de signos vitales - Sala 204", "Respuesta desde CACHÉ (resultado previo)");

        AttentionService service = new AttentionService(auth, nurseUser, sanitizer, cache, repo);

        // Caso 1: Solicitud válida completa (recorre toda la cadena hasta AttentionHandler)
        System.out.println("\n--- Caso 1: Solicitud válida (Procesamiento completo por la cadena) ---");
        Request req1 = new Request("token-valido-123", "<xml>Registro de curación de herida profunda</xml>", nurseUser);
        try {
            service.processAttention(req1);
            System.out.println("  Resultado: OK");
            System.out.println("  Datos saneados: " + req1.getData());
            System.out.println("  Respuesta: " + req1.getRespuesta());
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Caso 2: Solicitud en Caché (CacheHandler responde y corta la cadena con return false)
        System.out.println("\n--- Caso 2: Solicitud en Caché (CacheHandler corta la cadena) ---");
        Request req2 = new Request("token-valido-123", "   Control de signos vitales - Sala 204   ", nurseUser);
        try {
            service.processAttention(req2);
            System.out.println("  Resultado: OK (Atendida por caché)");
            System.out.println("  Respuesta: " + req2.getRespuesta());
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Caso 3: Solicitud con Token inválido (AuthHandler lanza UnauthorizedException)
        System.out.println("\n--- Caso 3: Token inválido / ausente (Rechazado en AuthHandler) ---");
        Request req3 = new Request("token-invalido-xyz", "Administración de medicamento", nurseUser);
        try {
            service.processAttention(req3);
        } catch (UnauthorizedException e) {
            System.out.println("  Excepción capturada esperada: " + e.getMessage());
        }

        // Caso 4: Solicitud con Rol no autorizado (RoleHandler lanza ForbiddenException)
        System.out.println("\n--- Caso 4: Rol no autorizado (Rechazado en RoleHandler) ---");
        User patientUser = new User("Paciente_Carlos", "PATIENT");
        Request req4 = new Request("token-valido-123", "Consulta de historia clínica", patientUser);
        try {
            service.processAttention(req4);
        } catch (ForbiddenException e) {
            System.out.println("  Excepción capturada esperada: " + e.getMessage());
        }

        // Caso 5: Demostración de extensibilidad (OCP: añadir un nuevo eslabón de auditoría sin tocar los existentes)
        System.out.println("\n--- Caso 5: Demostración OCP (Nuevo eslabón AuditLogHandler insertado) ---");
        Handler auditoria = new Handler() {
            @Override
            protected boolean procesar(Request req) {
                System.out.println("  [AuditLogHandler] Registrando evento de auditoría para: " + req.getData());
                return true; // Continúa la cadena
            }
        };

        Handler cadenaExtendida = new AuthHandler(auth);
        cadenaExtendida.setNext(new RoleHandler(nurseUser))
                       .setNext(new SanitizeHandler(sanitizer))
                       .setNext(auditoria) // Eslabón inyectado dinámicamente
                       .setNext(new CacheHandler(cache))
                       .setNext(new AttentionHandler(repo));

        AttentionService serviceExtendido = new AttentionService(cadenaExtendida);
        Request req5 = new Request("token-valido-123", "Aplicación de vacuna contra influenza", nurseUser);
        serviceExtendido.processAttention(req5);
        System.out.println("  Resultado: OK (Procesado con eslabón nuevo de auditoría)");

        System.out.println("\n============================================================");
    }
}
