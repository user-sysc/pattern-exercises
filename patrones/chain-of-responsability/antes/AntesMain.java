package antes;

import antes.domain.Request;
import antes.domain.User;
import antes.exception.ForbiddenException;
import antes.exception.UnauthorizedException;
import antes.service.AttentionService;
import antes.service.AuthService;
import antes.service.Cache;
import antes.service.Repo;
import antes.service.Sanitizer;

/**
 * ANTES: Procesamiento de atenciones médicas sin Chain of Responsibility.
 * El servicio monolítico agrupa todas las validaciones e interacciones en un solo método.
 */
public class AntesMain {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   ANTES DEL PATRÓN: Monolito en AttentionService");
        System.out.println("============================================================");

        AuthService auth = new AuthService("token-valido-123");
        User nurseUser = new User("Enfermera_Elena", "NURSE");
        Sanitizer sanitizer = new Sanitizer();
        Cache cache = new Cache();
        Repo repo = new Repo();

        // Precargar un registro en caché para simular respuesta rápida
        cache.put("Control de signos vitales - Sala 204", "Respuesta desde CACHÉ (resultado previo)");

        AttentionService service = new AttentionService(auth, nurseUser, sanitizer, cache, repo);

        // Caso 1: Solicitud válida completa (Token válido, Rol NURSE, Saneamiento, Guarda en Repo)
        System.out.println("\n--- Caso 1: Solicitud válida (Procesamiento completo) ---");
        Request req1 = new Request("token-valido-123", "<xml>Registro de curación de herida profunda</xml>", nurseUser);
        try {
            service.processAttention(req1);
            System.out.println("  Resultado: OK");
            System.out.println("  Datos saneados: " + req1.getData());
            System.out.println("  Respuesta: " + req1.getRespuesta());
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Caso 2: Solicitud cuyos datos ya se encuentran en Caché
        System.out.println("\n--- Caso 2: Solicitud en Caché (Corta flujo sin ir a BD) ---");
        Request req2 = new Request("token-valido-123", "   Control de signos vitales - Sala 204   ", nurseUser);
        try {
            service.processAttention(req2);
            System.out.println("  Resultado: OK (Atendida por caché)");
            System.out.println("  Respuesta: " + req2.getRespuesta());
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Caso 3: Solicitud con Token inválido (401 Unauthorized)
        System.out.println("\n--- Caso 3: Token inválido / ausente ---");
        Request req3 = new Request("token-invalido-xyz", "Administración de medicamento", nurseUser);
        try {
            service.processAttention(req3);
        } catch (UnauthorizedException e) {
            System.out.println("  Excepción capturada esperada: " + e.getMessage());
        }

        // Caso 4: Solicitud con Rol no autorizado (403 Forbidden)
        System.out.println("\n--- Caso 4: Rol no autorizado (PATIENT en vez de NURSE) ---");
        User patientUser = new User("Paciente_Carlos", "PATIENT");
        Request req4 = new Request("token-valido-123", "Consulta de historia clínica", patientUser);
        try {
            service.processAttention(req4);
        } catch (ForbiddenException e) {
            System.out.println("  Excepción capturada esperada: " + e.getMessage());
        }

        System.out.println("\n============================================================");
    }
}
